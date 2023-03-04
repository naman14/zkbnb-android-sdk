package zk.bnb.android.plugin.extensions

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import javax.swing.JPanel

class MainToolWindowFactory: ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
//        val myToolWindow = MainToolWindow(toolWindow)
//        val contentFactory = ContentFactory.SERVICE.getInstance()
//        val content: Content = contentFactory.createContent(myToolWindow.content, "", false)
//        toolWindow.contentManager.addContent(content)

        // Create Jpanel
        // Create Jpanel
        val myPanel = JPanel()

        // Add Jpanel to toolWindow

        // Add Jpanel to toolWindow
        toolWindow.component.add(myPanel)
    }
}