package actionJavaPackage;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
//import com.intellij.openapi.project.ev
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowEP;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.lang.reflect.Method;

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


        // get the project
        Project project = getEventProject(e);

// get File editor Manager Ex
        final FileEditorManagerEx fileEditorManagerEx =
                FileEditorManagerEx.getInstanceEx(project);

// get the editorWindow from File Editor Manager Ex
        EditorWindow currentWindow = fileEditorManagerEx.getCurrentWindow();

// create a split
        fileEditorManagerEx.createSplitter(0, currentWindow);
    }
}