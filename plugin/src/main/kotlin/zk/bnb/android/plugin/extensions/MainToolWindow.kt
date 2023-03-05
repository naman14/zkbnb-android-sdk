package zk.bnb.android.plugin.extensions

import com.intellij.openapi.wm.ToolWindow
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextArea

class MainToolWindow(toolWindow: ToolWindow?) {
    var toolWindowContent: JPanel? = null
    var clickHereButton: JButton? = null
    var textArea1: JTextArea? = null
    var textArea2: JTextArea? = null
    var textArea3: JTextArea? = null
    var attachingALifecycleToTextArea: JTextArea? = null
    var textArea4: JTextArea? = null
}