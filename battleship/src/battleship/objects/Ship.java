package battleship.objects;

import java.util.ArrayList;

/**
 * Schiff
 * @author H. Kaestner, T. Ohme
 *
 */
public class Ship {
	private int length = 2;
	private int posX = 0;
	private int posY = 0;
	/*
	 * poosible character values for direction:
	 *  h: horizontal
	 *  v: vertical
	 */
	private char direction = 'h';
	private boolean sunk = false;
	/*
	 * possible integer values for shots:
	 *  0: no shot
	 *  1: shot
	 */
	private ArrayList <Integer> shots = new ArrayList<Integer>();
	
	public Ship(int length) {
		this.length = length;
	
		for(int i = 0; i < this.length; i++) {
			shots.add(0);
		}
	}
	
	public void setPosition(int posX, int posY, char direction) {
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
	}
	
	public boolean shoot(int position) {
		// TODO shoot on ship
		return false; // true of ship was sunk
	}
}
