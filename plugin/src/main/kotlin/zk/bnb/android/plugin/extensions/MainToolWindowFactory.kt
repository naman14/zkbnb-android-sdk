package zk.bnb.android.plugin.extensions

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.util.ui.JBUI
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class MainToolWindowFactory: ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
//        val myToolWindow = MainToolWindow(toolWindow)
//        val contentFactory = ContentFactory.SERVICE.getInstance()
//        val content: Content = contentFactory.createContent(myToolWindow.content, "", false)
//        toolWindow.contentManager.addContent(content)

        val html = "<html>" +
                "<h1>ZkBNB</h1><br/>" +
                "<h2>Scaling breakthrough for BNB Chain</h2><br/>" +
                "<p>Click the following button to add the ZkBNB Android SDK to your project.</p>" +
                "</html>"

        // Create Jpanel
        // Create Jpanel
        val myPanel = JPanel()
        myPanel.border = JBUI.Borders.empty(40, 40)
        val mainLabel = JLabel(html)
        myPanel.add(mainLabel)

        val b = JButton("Click here")
        myPanel.add(b)

//        val subLabel = JLabel("<html><br></html>")
//        myPanel.add(subLabel)

        // Add Jpanel to toolWindow

        // Add Jpanel to toolWindow
        toolWindow.component.add(myPanel)
    }
}