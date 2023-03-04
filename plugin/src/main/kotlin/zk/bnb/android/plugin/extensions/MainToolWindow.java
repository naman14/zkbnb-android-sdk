package zk.bnb.android.plugin.extensions;


import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class MainToolWindow {

    private JPanel toolWindowContent;

    public MainToolWindow(ToolWindow toolWindow) {

    }

    public JPanel getContent() {
        return toolWindowContent;
    }
}
