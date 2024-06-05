import javax.swing.JTextField
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Color
import java.awt.event.FocusEvent
import java.awt.event.FocusListener

class PlaceholderTextField(columns: Int, private val placeholder: String) : JTextField(columns) {
    init {
        addFocusListener(object : FocusListener {
            override fun focusGained(e: FocusEvent) {
                repaint()
            }

            override fun focusLost(e: FocusEvent) {
                repaint()
            }
        })
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (text.isEmpty() && !hasFocus()) {
            val g2 = g as Graphics2D
            g2.color = Color.GRAY
            g2.drawString(placeholder, insets.left, g.fontMetrics.maxAscent + insets.top)
        }
    }
}
