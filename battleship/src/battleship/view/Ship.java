package battleship.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import battleship.objects.Field.Directionelements;

public class Ship extends JPanel {

	private static final long serialVersionUID = 1L;
	private battleship.objects.Ship ship;
    private static final Color shipColor = Color.ORANGE;
	
	public Ship(battleship.objects.Ship ship, Graphics g) {
        super();
		this.ship = ship;
        this.paintComponent(g);
	}

	public void paintComponent(Graphics g) {
        //super.paintComponent(g);
		int l = ship.getLength();
		if (Directionelements.VERTICAL == ship.getDirection()) {
			g.setColor(shipColor);
			g.fillRect(ship.getPosX()*25+23, ship.getPosY()*25+23, 20, 25*l-5);

		} else {
			g.setColor(shipColor);
			g.fillRect(ship.getPosX()*25+23, ship.getPosY()*25+23, 25*l-5,20);
		}
	}

}
