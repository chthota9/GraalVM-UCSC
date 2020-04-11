package ActionPackage;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.*;
import javax.swing.*;
import javax.swing.JLabel;
import com.intellij.openapi.wm.ToolWindow;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import java.io.FileReader;

class MethodCheck extends AnAction {
    public void actionPerformed(AnActionEvent e) {

        String text = "";
        Project project = getEventProject(e);
        ToolWindowManager twm = ToolWindowManager.getInstance(project);
        ToolWindow tw = twm.getToolWindow("Call Tree Visualization");
        tw.getContentManager().removeAllContents(true);
        PsiFile psiFile = e.getData(PlatformDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
        if (project == null || editor == null || psiFile == null || element == null) {
            JOptionPane.showMessageDialog(null, "No method call found", "GraalVM", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //check if method call
        text = element.getText();
        PsiElement uncleElement = element.getParent().getNextSibling();
        if (uncleElement == null) {
            JOptionPane.showMessageDialog(null, "No method call found", "GraalVM", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else {
            if (uncleElement.getText().trim().length() == 0) {
                uncleElement = uncleElement.getNextSibling();
            }
            if (uncleElement.getFirstChild() != null && uncleElement.getFirstChild().getText().equals("(")) {

                //code for json reader

                JSONParser jsonParser = new JSONParser();
                String filePath = "/Users/tonykhalilollahi/Documents/GraalVM-UCSC/substratevm/reports/call_tree_JSONhelloworld_20200309_202957.json"; //absolute path
                //Strings outputted to Toolbar
                String filename = "JSON not found!";
                String linenum = "--";
                String args = "No Arguments";
                String calleesString = "No Callees";
                try {//extract info from jsonObject
                    FileReader reader = new FileReader(filePath);
                    Object obj = jsonParser.parse(reader);
                    JSONArray array = (JSONArray) obj;
                    JSONObject jsonOb = findObject(array, text);
                    filename = (String) jsonOb.get("file");
                    long x = (long) jsonOb.get("line");
                    linenum = Long.toString(x);
                    args = writeArguments (jsonOb);
                    calleesString = writeInvokes(jsonOb);

                }catch (Exception er) {
                    System.out.println(e);
                }

                //end code for json reader

                
                JLabel label = new JLabel ("<html> File: " + filename + "<br><br> Line Number: " + linenum + "<br><br> Arguments: <br>" + args + "<br><br>Invokes: " + calleesString + "</html>", JLabel.CENTER);
                ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
                Content content = contentFactory.createContent(label,"", false);

                tw.getContentManager().addContent(content);


                tw.show(null);


            }
            else {
                JOptionPane.showMessageDialog(null, "No method call found", "GraalVM", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

    }
    private JSONObject findObject (JSONArray array, String text){
        for(int i = 0; i < array.size(); i++){
            JSONObject jsonOb = (JSONObject) array.get(i);
            String name = (String) jsonOb.get("name");
            if(name.equals(text)){
                return jsonOb;
            }
        }
        return null;
    }
    private String writeArguments (JSONObject jsonOb){
        String args = " ";
        JSONArray arguments = (JSONArray) jsonOb.get ("arguments");
        for (int i = 0; i < arguments.size(); i++){
            args += (arguments.get(i).toString() + "<br>");
        }
        return args;
    }

    private String writeInvokes (JSONObject jsonOb){
        String calleesString = " ";
        JSONArray invokes = (JSONArray) jsonOb.get ("invokes");
        for (int i = 0; i < invokes.size(); i++){
            JSONObject invoked = (JSONObject) invokes.get(i);
            calleesString += ("<br>   Line: " + (long) invoked.get ("line"));
            JSONArray callees = (JSONArray) invoked.get("callees");
            calleesString += ("<br>   Callees: <br>");
            for (int j = 0; j < callees.size(); j++){
                calleesString += ((long)callees.get(j) + "<br>    ");
            }
        }
        return calleesString;
    }
}