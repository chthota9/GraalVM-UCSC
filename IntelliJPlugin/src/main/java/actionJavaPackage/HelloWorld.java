package ActionPackage;

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
import com.intellij.openapi.wm.ToolWindowManager;
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
        text = editor.getSelectionModel().getSelectedText();
        JFrame newWindow = new JFrame ("Frame");
        newWindow.setSize(300,300);
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel textLabel = new JLabel (text, JLabel.CENTER);
        newWindow.getContentPane().add (textLabel, JLabel.CENTER);

        newWindow.setVisible(true);

        Project project = getEventProject(e);
        ToolWindowManager.getInstance(project).getToolWindow("Call Tree Visualization").show(null);

    }
}
