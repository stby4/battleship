package battleship.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * picture Panel
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class PicturePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image image;
    private int x;
    private int y;

    /**
     * Picture for GUI
     *
     * @param file
     * @param x
     * @param y
     */
    public PicturePanel(String file, int x, int y) {
        this.x =x;
        this.y = y;
        //this.setPreferredSize(new Dimension(600, 337));
        try {
            image = ImageIO.read(new File("graphics/" +file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * print the Image to the right position
     *
     * @param g
     */
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this.x, this.y, null);
        }
    }
}
