package zk.bnb.android.plugin.extensions

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import zk.bnb.android.plugin.extensions.MainToolWindow
import com.intellij.util.ui.JBUI
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class MainToolWindowFactory: ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MainToolWindow(toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.toolWindowContent, "", false)
        toolWindow.contentManager.addContent(content)

//        val html = "<html>" +
//                "<h1>ZkBNB</h1><br/>" +
//                "<h2>Scaling breakthrough for BNB Chain</h2><br/>" +
//                "<p>Click the following button to add the ZkBNB Android SDK to your project.</p>" +
//                "</html>"
//
//        // Create Jpanel
//        // Create Jpanel
//        val rootPanel = JPanel()
//        rootPanel.border = JBUI.Borders.empty(40)
//        rootPanel.layout = GridLayout(3, 1)
//
//        val mainLabel = JLabel(html)
//        rootPanel.add(mainLabel)
//
//        val buttonParent = JPanel()
//        buttonParent.layout = GridLayout(1, 1)
//        buttonParent.size = Dimension(100, 100)
//        val b = JButton("<html>Click Here</html>")
//        b.size = Dimension(100, 100)
//        buttonParent.add(b)
//        rootPanel.add(buttonParent)
//
//        val subLabel = JLabel("<html><br>HELLO HELLO<br></html>")
//        rootPanel.add(subLabel)
//
//        // Add Jpanel to toolWindow
//
//        // Add Jpanel to toolWindow
//        toolWindow.component.add(rootPanel)
    }
}