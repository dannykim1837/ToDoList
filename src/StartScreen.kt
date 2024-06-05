import javax.swing.*
import java.awt.*

class StartScreen : JFrame() {
    init {
        title = "Welcome"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(350, 600)
        setLocationRelativeTo(null)

        val panel = ImagePanel("src/image/bg.png")
        panel.layout = GridBagLayout()
        val gbc = GridBagConstraints()

        val welcomeLabel = JLabel("Welcome to ToDo List", JLabel.CENTER)
        welcomeLabel.font = Font("Arial", Font.BOLD, 18)
        welcomeLabel.foreground = Color.WHITE
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.insets = Insets(10, 10, 10, 10)
        panel.add(welcomeLabel, gbc)

        val startButton = JButton("Start")
        gbc.gridx = 0
        gbc.gridy = 1
        panel.add(startButton, gbc)

        startButton.addActionListener {
            this.dispose()
            ToDoListApp().isVisible = true
        }

        add(panel)
    }
}
