package actionJavaPackage;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

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
        JLabel textLabel = new JLabel ("hello world", JLabel.CENTER);
//        newWindow.getContentPane().add (textLabel, JLabel.CENTER);
//        newWindow.setVisible(true);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(textLabel,"", false);
        toolWindow.getContentManager().addContent(content);
    }


}
