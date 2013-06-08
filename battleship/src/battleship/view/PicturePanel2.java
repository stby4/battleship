package battleship.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * PicturePanel Battleship
 * @author Tom Ohme
 * wird wieder gel�scht, wenn ich weiss wie mit einem PicturePanel die Auswahl geht!!!!
 * 
 */
public class PicturePanel2 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image shipImage;
	
	/*
	 * Picture for GUI
	 */
	public PicturePanel2(String file) {
		//this.setPreferredSize(new Dimension(600, 337));
		try {
			shipImage = ImageIO.read(new File("graphics/" +file)); //Gamepicture ac_carrier
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (shipImage != null) {
			g.drawImage(shipImage, 0, 0, 200, 120, null);
		}
	}
}
