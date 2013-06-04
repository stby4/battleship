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
 * 
 */
public class PicturePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image image;
	
	/*
	 * Picture for GUI
	 */
	public PicturePanel() {
		//this.setPreferredSize(new Dimension(600, 337));
		try {
			image = ImageIO.read(new File("../graphics/wallpaper.jpg")); //Menupicture
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		if (image != null) {
			g.drawImage(image, 0, 0, 600, 337, null);
		}
	}
}
