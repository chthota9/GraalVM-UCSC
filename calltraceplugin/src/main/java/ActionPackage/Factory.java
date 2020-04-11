package ActionPackage;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
//import org.json.*;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.*;
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.wm.ToolWindow;
//import com.intellij.openapi.wm.ToolWindowEP;
//import com.intellij.openapi.wm.ToolWindowFactory;
//import com.intellij.ui.content.Content;
//import com.intellij.ui.content.ContentFactory;
import javax.swing.*;

class Factory implements ToolWindowFactory {

    public Factory() {

    }

    public void createToolWindowContent(Project project, ToolWindow toolWindow) {

        JFrame newWindow = new JFrame ("Frame");
        newWindow.setSize(300,300);
        newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSONParser jsonParser = new JSONParser();
        String filePath = "/Users/tonykhalilollahi/Documents/GraalVM-UCSC/substratevm/reports/call_tree_JSONhelloworld_20200309_202957.json"; //absolute path
        String found = "not found!";
        try {
            FileReader reader = new FileReader(filePath);
            Object obj = jsonParser.parse(reader);
            JSONArray array = (JSONArray) obj;
            JSONObject object = (JSONObject) array.get(0);
            found = (String) object.get("file");
        }catch (Exception e) {
            System.out.println(e);
        }


        JLabel textLabel = new JLabel (found, JLabel.CENTER);


        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(textLabel,"", false);
    }


}
