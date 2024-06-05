import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class ToDoListApp : JFrame() {
    private val todoListModel = DefaultListModel<String>()
    private val todoList = JList(todoListModel)

    init {
        createUI()
    }

    private fun createUI() {
        title = "To-Do List App"
        setSize(400, 300)
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = CardLayout()

        // Start screen
        val startPanel = JPanel()
        startPanel.layout = BoxLayout(startPanel, BoxLayout.Y_AXIS)
        val startLabel = JLabel("Welcome to the To-Do List App")
        startLabel.alignmentX = Component.CENTER_ALIGNMENT
        val startButton = JButton("Start")
        startButton.alignmentX = Component.CENTER_ALIGNMENT
        startPanel.add(Box.createVerticalGlue())
        startPanel.add(startLabel)
        startPanel.add(Box.createVerticalStrut(20))
        startPanel.add(startButton)
        startPanel.add(Box.createVerticalGlue())
        add(startPanel, "start")

        // To-Do List screen
        val mainPanel = JPanel()
        mainPanel.layout = BorderLayout()

        val inputPanel = JPanel()
        inputPanel.layout = FlowLayout()
        val inputField = JTextField(20)
        val addButton = JButton("Add Task")
        inputPanel.add(inputField)
        inputPanel.add(addButton)
        mainPanel.add(inputPanel, BorderLayout.NORTH)

        val scrollPane = JScrollPane(todoList)
        mainPanel.add(scrollPane, BorderLayout.CENTER)

        val removeButton = JButton("Remove Selected Task")
        mainPanel.add(removeButton, BorderLayout.SOUTH)
        add(mainPanel, "main")

        val cl = contentPane.layout as CardLayout
        startButton.addActionListener {
            cl.show(contentPane, "main")
        }

        addButton.addActionListener {
            val task = inputField.text
            if (task.isNotEmpty()) {
                todoListModel.addElement(task)
                inputField.text = ""
            }
        }

        removeButton.addActionListener {
            val selectedIndex = todoList.selectedIndex
            if (selectedIndex != -1) {
                todoListModel.remove(selectedIndex)
            }
        }
    }
}

fun main() {
    SwingUtilities.invokeLater {
        val app = ToDoListApp()
        app.isVisible = true
    }
}
