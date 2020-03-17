package ActionPackage;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;

class OpenSourceCode extends AnAction {
    public void actionPerformed(AnActionEvent e) {
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
