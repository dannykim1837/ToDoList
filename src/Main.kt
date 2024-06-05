import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

fun main() {
    SwingUtilities.invokeLater {
        StartScreen().isVisible = true
    }
}

class StartScreen : JFrame() {
    init {
        title = "Welcome"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(350, 600)
        setLocationRelativeTo(null)
        layout = GridBagLayout()
        val gbc = GridBagConstraints()

        val welcomeLabel = JLabel("Welcome to ToDo List", JLabel.CENTER)
        welcomeLabel.font = Font("Arial", Font.BOLD, 18)
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.insets = Insets(10, 10, 10, 10)
        add(welcomeLabel, gbc)

        val startButton = JButton("Start")
        gbc.gridx = 0
        gbc.gridy = 1
        add(startButton, gbc)

        startButton.addActionListener {
            this.dispose()  // Close the start screen
            MobileLikeToDoListApp().isVisible = true  // Open the ToDo list app
        }
    }
}

class MobileLikeToDoListApp : JFrame() {
    private val toDoModel = DefaultListModel<String>()
    private val toDoList = JList(toDoModel)
    private val inputField = JTextField(20)
    private val addButton = JButton("Add")
    private val removeButton = JButton("Remove")

    init {
        title = "ToDo List"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(350, 600)
        setLocationRelativeTo(null)
        layout = GridBagLayout()
        val gbc = GridBagConstraints()

        // Header
        val headerLabel = JLabel("ToDo List", JLabel.CENTER)
        headerLabel.font = Font("Arial", Font.BOLD, 24)
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.gridwidth = 2
        gbc.insets = Insets(10, 10, 10, 10)
        add(headerLabel, gbc)

        // ToDo List
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.gridwidth = 2
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.BOTH
        add(JScrollPane(toDoList), gbc)

        // Input Field
        gbc.gridx = 0
        gbc.gridy = 2
        gbc.gridwidth = 1
        gbc.weightx = 0.8
        gbc.weighty = 0.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        add(inputField, gbc)

        // Add Button
        gbc.gridx = 1
        gbc.gridy = 2
        gbc.weightx = 0.2
        addButton.icon = UIManager.getIcon("FileView.fileIcon")
        add(addButton, gbc)

        // Remove Button
        gbc.gridx = 0
        gbc.gridy = 3
        gbc.gridwidth = 2
        gbc.insets = Insets(10, 10, 10, 10)
        removeButton.icon = UIManager.getIcon("FileView.computerIcon")
        add(removeButton, gbc)

        // Action Listeners
        addButton.addActionListener(AddButtonListener())
        removeButton.addActionListener(RemoveButtonListener())
    }

    private inner class AddButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val task = inputField.text
            if (task.isNotEmpty()) {
                toDoModel.addElement(task)
                inputField.text = ""
            }
        }
    }

    private inner class RemoveButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val selectedIndex = toDoList.selectedIndex
            if (selectedIndex != -1) {
                toDoModel.remove(selectedIndex)
            }
        }
    }
}
