package battleship.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import battleship.objects.Field.Fieldelements;

/**
 * Shot Battleship
 * @author Tom Ohme
 *
 */
public class Shot extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Color hit = new Color(228, 219, 16);
    private static final Color noHit = new Color(26, 71, 190);
    private int x;
    private int y;
    private Fieldelements fieldelements;
	
	public Shot(Graphics g, int x, int y, Fieldelements fieldelements) {
        super();
        this.x = x;
        this.y = y;
        this.fieldelements = fieldelements;
        this.paintComponent(g);
	}

	public void paintComponent(Graphics g) {
		if (fieldelements == Fieldelements.HIT) {
			g.setColor(hit);
			g.fillRect(x*25+23, y*25+23, 20, 20); //mouseClicked

		} else { 
			g.setColor(noHit);
			g.fillOval(x*25+23, y*25+23, 20, 20); //mouseClicked
		}
	}

}
