package battleship.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * PicturePanel Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * wird wieder gelöscht, wenn ich weiss wie mit einem PicturePanel die Auswahl geht!!!!
 * 
 */
public class PicturePanel2 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image ac_carrier;
	
	/*
	 * Picture for GUI
	 */
	public PicturePanel2() {
		//this.setPreferredSize(new Dimension(600, 337));
		try {
			ac_carrier = ImageIO.read(new File("graphics/ac_carrier.png")); //Gamepicture ac_carrier
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (ac_carrier != null) {
			g.drawImage(ac_carrier, 0, 0, 200, 120, null);
		}
	}
}
