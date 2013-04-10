package battleship.objects;

import java.util.ArrayList;


/**
 * Spielfeld
 * @author H. Kaestner, T. Ohme
 *
 */
public class Field {
	private int sizeX = 10;
	private int sizeY = 10;
	/*
	 * possible integer values for shots:
	 *  0: no shot
	 *  1: shot
	 */
	private ArrayList <ArrayList<Integer>> shots = new ArrayList <ArrayList<Integer>> ();
	private ArrayList <Ship> ships = new ArrayList <Ship>();
	
	public Field(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		for(int i = 0; i < this.sizeX; i++) {
			this.shots.add(new ArrayList <Integer>());
			for(int j = 0; j < this.sizeY; j++) {
				this.shots.get(i).add(0);				
			}
		}
	}
	
	public boolean addShip(Ship ship) {
		// TODO check for collisions!
		this.ships.add(ship);
		return true; // false if ship could ne set
	}
	
	public boolean shoot(int x, int y) {
		// TODO shoot
		return true; // false if field is not available or has already been shot on
	}
}
