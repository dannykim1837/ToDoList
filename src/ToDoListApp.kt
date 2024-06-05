import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class ToDoListApp : JFrame() {
    private val toDoPanel = JPanel()
    private val selectAllCheckBox = JCheckBox()

    init {
        title = "ToDo List"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(350, 600)
        setLocationRelativeTo(null)

        val panel = JPanel()
        panel.layout = BorderLayout()
        panel.background = Color(60, 63, 65)

        // Header
        val headerLabel = JLabel("Tasks", JLabel.CENTER)
        headerLabel.font = Font("Arial", Font.BOLD, 24)
        headerLabel.foreground = Color.WHITE

        selectAllCheckBox.background = Color(60, 63, 65)
        selectAllCheckBox.addActionListener {
            val allSelected = selectAllCheckBox.isSelected
            for (component in toDoPanel.components) {
                if (component is JPanel) {
                    val checkBox = component.getComponent(0) as JCheckBox
                    checkBox.isSelected = allSelected
                }
            }
        }

        val headerPanel = JPanel()
        headerPanel.layout = BorderLayout()
        headerPanel.background = Color(0, 0, 0, 0)

        val headerLeftPanel = JPanel()
        headerLeftPanel.layout = BoxLayout(headerLeftPanel, BoxLayout.X_AXIS)
        headerLeftPanel.background = Color(0, 0, 0, 0)
        headerLeftPanel.add(selectAllCheckBox)
        headerLeftPanel.add(Box.createRigidArea(Dimension(10, 0)))
        headerLeftPanel.add(headerLabel)

        headerPanel.add(headerLeftPanel, BorderLayout.WEST)
        panel.add(headerPanel, BorderLayout.NORTH)

        // ToDo List Panel
        toDoPanel.layout = BoxLayout(toDoPanel, BoxLayout.Y_AXIS)
        val scrollPane = JScrollPane(toDoPanel)
        scrollPane.verticalScrollBar.unitIncrement = 16
        panel.add(scrollPane, BorderLayout.CENTER)

        // Input Field and Add Button
        val inputPanel = JPanel()
        inputPanel.layout = BoxLayout(inputPanel, BoxLayout.X_AXIS)
        inputPanel.background = Color(0, 0, 0, 0)

        val inputField = PlaceholderTextField(20, "Typing here")
        inputField.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_ENTER) {
                    val task = inputField.text
                    if (task.isNotEmpty()) {
                        addToDoItem(task)
                        inputField.text = ""
                    }
                }
            }
        })
        inputPanel.add(inputField)

        val addButton = JButton("Add")
        addButton.addActionListener {
            val task = inputField.text
            if (task.isNotEmpty()) {
                addToDoItem(task)
                inputField.text = ""
            }
        }
        inputPanel.add(addButton)

        panel.add(inputPanel, BorderLayout.SOUTH)

        add(panel)
    }

    private fun addToDoItem(task: String) {
        val itemPanel = JPanel()
        itemPanel.layout = BoxLayout(itemPanel, BoxLayout.X_AXIS)
        itemPanel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        itemPanel.background = Color.LIGHT_GRAY

        val checkBox = JCheckBox()
        checkBox.background = Color.LIGHT_GRAY
        val taskLabel = JLabel(task)
        taskLabel.font = Font("Arial", Font.PLAIN, 16)

        val deleteButton = JButton("Delete")
        deleteButton.addActionListener {
            toDoPanel.remove(itemPanel)
            toDoPanel.revalidate()
            toDoPanel.repaint()
        }

        itemPanel.add(checkBox)
        itemPanel.add(Box.createRigidArea(Dimension(10, 0)))
        itemPanel.add(taskLabel)
        itemPanel.add(Box.createHorizontalGlue())
        itemPanel.add(deleteButton)

        toDoPanel.add(itemPanel)
        toDoPanel.revalidate()
        toDoPanel.repaint()
    }
}
