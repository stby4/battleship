package battleship.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import battleship.objects.*;
import battleship.objects.Field;
import battleship.objects.Field.Directionelements;

public class Ship extends JPanel {

	private static final long serialVersionUID = 1L;
	private battleship.objects.Ship ship;
    private Color shipColor = new Color(186, 237, 116);
	
	public Ship(battleship.objects.Ship ship, Graphics g, Field.Fieldelements status) {
        super();
        if(Field.Fieldelements.SUNK == status) {
            shipColor = new Color(225, 34, 41);
        }
		this.ship = ship;
        this.paintShip(g);
	}

    public void paintComponent(Graphics g) {

    }

	public void paintShip(Graphics g) {
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
