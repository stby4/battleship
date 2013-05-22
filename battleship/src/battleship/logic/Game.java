package battleship.logic;

import battleship.objects.Ship;
import battleship.objects.Field;

import java.util.ArrayList;
import java.util.Random;

/**
 * most important class!!!!!
 *
 * @author H. Kaestner, T. Ohme
 */
public class Game {
    private Field fieldUser;
    private Field fieldComputer;
    private ArrayList<Ship> ships;

	/*
	 * TODO
	 * - start new game
	 * - let user place ships (maybe in Field)
	 * - automatically place the computers ships
	 * - let user and computer shoot at each other (maybe in Field)
	 * - log scores
	 * - determine the winner
	 * - write scores in user data
	 */

    public Game(int sizeX, int sizeY, ArrayList<Ship> ships) {
        this.fieldUser = new Field(sizeX, sizeY);
        this.fieldComputer = new Field(sizeX, sizeY);

        this.ships = ships;
    }

    private void autoPlaceShips() {
        Random random = new Random();
        int sizeX = this.fieldComputer.getSizeX();
        int sizeY = this.fieldComputer.getSizeY();

        for (Ship ship : this.ships) {
            do {
                int posX = random.nextInt(sizeX + 1);
                int posY = random.nextInt(sizeY + 1);
                int direction = random.nextInt(2);
                ship.setPosition(posX, posY, direction);
            } while (fieldComputer.addShip(ship));
        }
    }

    private void autoPlaceShot() {
        // TODO artificial intelligence (Hinrich)

    }

}
