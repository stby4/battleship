package battleship.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * PicturePanel Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class PicturePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image image;
	
	public PicturePanel() {
		image = (new ImageIcon("graphics/MenuBattleship.jpg")).getImage();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (image != null) {
			g.drawImage(image, 10, 10, this);
		}
	}

}
