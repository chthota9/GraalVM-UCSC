package actionPackage

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import javax.swing.JFrame
import javax.swing.JLabel


class HelloWorld : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        var editor: Editor = e.getData(PlatformDataKeys.EDITOR)!!
        var text: String = editor.getSelectionModel().getSelectedText()!!
        var newWindow: JFrame = JFrame(text);
        newWindow.setSize(300,300);
        //Messages.showMessageDialog(e.project, "Hello World\n"+text, "MyIdeaDemo", Messages.getInformationIcon());
        newWindow.defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
        var textLabel: JLabel = JLabel (text, JLabel.CENTER);
        newWindow.contentPane.add (textLabel, JLabel.CENTER);
        newWindow.setVisible(true);
    }
}