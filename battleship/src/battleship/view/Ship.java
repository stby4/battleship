package battleship.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import battleship.objects.Field.Directionelements;

public class Ship extends JPanel {

	private static final long serialVersionUID = 1L;
	private battleship.objects.Ship ship;
	
	public Ship(battleship.objects.Ship ship) {
		this.ship = ship;
	}

	public void paintComponent(Graphics g) {
		int l = ship.getLength();
		if (Directionelements.VERTICAL == ship.getDirection()) {
			g.setColor(Color.WHITE); 
			g.fillRect(ship.getPosX()*25, ship.getPosY()*25, 25, 25*l);
		
		} else {
			g.setColor(Color.WHITE); 
			g.fillRect(ship.getPosX()*25, ship.getPosY()*25, 25*l,25);
		}
	}

}
