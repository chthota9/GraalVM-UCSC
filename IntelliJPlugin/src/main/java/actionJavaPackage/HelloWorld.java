package actionJavaPackage;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.lang.reflect.Method;

class HelloWorld extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        String text = "";

        /*Class openClass;
        Method [] listMethods = openClass.getDeclaredMethods();
        for (Method m: listMethods){
            System.out.printf("%s\n", m);
        }*/

        text = editor.getSelectionModel().getSelectedText();
        JFrame newWindow = new JFrame ("Frame");
        newWindow.setSize(300,300);
        newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabel = new JLabel (text, JLabel.CENTER);
        newWindow.getContentPane().add (textLabel, JLabel.CENTER);

        newWindow.setVisible(true);
    }
}