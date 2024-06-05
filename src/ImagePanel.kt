import javax.imageio.ImageIO
import javax.swing.JPanel
import java.awt.Graphics
import java.io.File

class ImagePanel(private val imagePath: String) : JPanel() {
    private val backgroundImage = ImageIO.read(File(imagePath))

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(backgroundImage, 0, 0, width, height, this)
    }
}
