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

    private Field.Fieldelements autoPlaceShot() {
        int sizeX = this.fieldUser.getSizeX();
        int sizeY = this.fieldUser.getSizeY();

        // probability that a field contains a ship (not in percent, 1000 is best)
        double[][] probability = new double[sizeX][sizeY];
        double sumProbability = 0;

        for (int posX = 0; posX < sizeX; posX++) {
            for (int posY = 0; posY < sizeY; posY++) {
                // set probability for each field to 1
                probability[sizeX][sizeY] = 1.;

                // check neighbouring fields for hits and set probability to 1000
                if (Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX, posY - 1) ||
                        Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX, posY + 1) ||
                        Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX - 1, posY) ||
                        Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX + 1, posY)) {
                    probability[posX][posY] = 1000;
                }

                // 0 points if all fields above and below are SUNK or SHOT
                if ((Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX, posY - 1) || Field.Fieldelements.WATER == fieldUser.getFieldStatus(posX, posY - 1)) &&
                        (Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX, posY + 1) || Field.Fieldelements.WATER == fieldUser.getFieldStatus(posX, posY + 1)) &&
                        (Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX - 1, posY) || Field.Fieldelements.WATER == fieldUser.getFieldStatus(posX - 1, posY)) &&
                        (Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX + 1, posY) || Field.Fieldelements.WATER == fieldUser.getFieldStatus(posX + 1, posY)) &&
                        ) {
                    probability[posX][posY] = 0;
                }

                // 0 points if a neighbouring field is SUNK
                if (Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX - 1, posY - 1) ||
                        Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX - 1, posY) ||
                        Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX - 1, posY + 1) ||
                        Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX, posY - 1) ||
                        Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX, posY + 1) ||
                        Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX + 1, posY - 1) ||
                        Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX + 1, posY) ||
                        Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX + 1, posY + 1)
                        ) {
                    probability[posX][posY] = 0;
                }

                // 0 points if top right, top left, bottom right or bottom left is HIT
                if (Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX - 1, posY - 1) ||
                        Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX - 1, posY + 1) ||
                        Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX + 1, posY - 1) ||
                        Field.Fieldelements.HIT == fieldUser.getFieldStatus(posX + 1, posY + 1)
                        ) {
                    probability[posX][posY] = 0;
                }

                // 0 points if a shot has already been placed
                if (Field.Fieldelements.WATER != fieldUser.getFieldStatus(posX, posY) &&
                        Field.Fieldelements.SHIP != fieldUser.getFieldStatus(posX, posY)) {
                    probability[posX][posY] = 0;
                }
            }
        }

        // calculate sum of all points
        // TODO check if probability must be smaller numbers
        for (int posX = 0; posX < sizeX; sizeX++) {
            for (int posY = 0; posY < sizeY; posY++) {
                sumProbability += probability[posX][posY];
            }
        }

        // random element
        Random random = new Random();
        sumProbability = random.nextInt((int)sumProbability--);
        sumProbability++; // must at least be 1
        int posY = -1;
        int posX = -1;
        while(posY<sizeY-1 && sumProbability >= 0) {
            posY++;
            posX = -1;
            while(posX<sizeX && sumProbability >= 0) {
                posX++;
                sumProbability -= probability[posX][posY];
            }
        }
        Field.Fieldelements result = fieldUser.shoot(posX, posY);
        return result;
    }

}
