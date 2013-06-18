package battleship.logic;

import battleship.objects.Ship;
import battleship.objects.Field;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles the placement of ships, and shots.
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Game implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    public static enum Playerelements {
        USER,
        COMPUTER,
        ERROR
    }

    private Field fieldUser;
    private Field fieldComputer;
    private ArrayList<Ship> shipTypes;
    private ArrayList<Ship> shipsUserHelper;
    private ArrayList<Ship> shipsComputerHelper;
    private int lastShotComputerX;
    private int lastShotComputerY;


    /* comments to hide this constructor from the code analyser
    public Game(int sizeX, int sizeY, ArrayList<Ship> ships) {
        this.fieldUser = new Field(sizeX, sizeY);
        this.fieldComputer = new Field(sizeX, sizeY);
        this.shipTypes = ships;

        this.placeShipsComputer();
    }
    */

    /**
     * Sets up a new game with predefined field sizes (10x10 fields) and ships
     */
    public Game() {
        this.shipTypes = new ArrayList<Ship>();
        this.fieldUser = new Field(10, 10);
        this.fieldComputer = new Field(10, 10);

        Ship ac_carrier = new Ship(5, "Aircraft carrier", "ac_carrier.png");
        shipTypes.add(ac_carrier);
        Ship cruiser = new Ship(4, "Cruiser", "battleship.jpg");
        shipTypes.add(cruiser);
        Ship destroyer = new Ship(3, "Destroyer", "destroyer.png");
        shipTypes.add(destroyer);
        Ship submarine = new Ship(3, "Submarine", "submarine.png");
        shipTypes.add(submarine);
        Ship frigate = new Ship(2, "Frigate", "frigate.png");
        shipTypes.add(frigate);

        this.shipsComputerHelper = clone(shipTypes);
        this.shipsUserHelper = clone(shipTypes);

        this.placeShipsComputer();
    }

    private ArrayList<Ship> clone(ArrayList<Ship> original) {
        ArrayList<Ship> clone = new ArrayList<Ship>(original.size());
        for (Ship ship : original) {
            clone.add(ship.clone());
        }
        return clone;
    }

    /**
     * Returns the first ship in the list that has not yet been set.
     *
     * @return first unset Ship, or null if all ships are set
     */
    public Ship getNextUnsetShip() {
        for (Ship ship : shipsUserHelper) {
            if (!ship.isSet()) {
                return ship;
            }
        }
        return null;
    }

    /**
     * Returns a list of all sunken ships.
     *
     * @param player The player whose sunken ships will be searched.
     * @return A list of all sunken ships.
     */
    public ArrayList<Ship> getSunkShips(Playerelements player) {
        Field field;
        if (Playerelements.COMPUTER == player) {
            field = fieldUser;
        } else {
            field = fieldComputer;
        }

        ArrayList<Ship> sunkShips = new ArrayList<Ship>();
        for (Ship ship : field.getShips()) {
            if (ship.getSunk()) {
                sunkShips.add(ship);
            }
        }
        return sunkShips;
    }

    /**
     * adds a ship to the user field
     *
     * @param ship Ship
     * @return true if ship was placed in the user field, false if not
     */
    public boolean placeShipUser(Ship ship) {
        return fieldUser.addShip(ship);
    }

    /**
     * Places a shot from the user on the computers field.
     *
     * @param posX x coordinate of the shot
     * @param posY y coordinate of the shot
     * @return The new status of the field where the shot was aimed at.
     */
    public Field.Fieldelements placeShotUser(int posX, int posY) {
        return fieldComputer.shoot(posX, posY);
    }

    /**
     * Automatically places all ships on the computers field.
     */
    private void placeShipsComputer() {
        Random random = new Random();
        int sizeX = this.fieldComputer.getSizeX();
        int sizeY = this.fieldComputer.getSizeY();

        boolean isSet;
        for (Ship ship : shipsComputerHelper) {
            do {
                int posX = random.nextInt(sizeX + 1);
                int posY = random.nextInt(sizeY + 1);
                int pick = random.nextInt(Field.Directionelements.values().length);
                ship.setPosition(posX, posY, Field.Directionelements.values()[pick]);
                isSet = fieldComputer.addShip(ship);
                if (!isSet) {
                    ship.unset();
                }
            } while (!isSet);
        }
    }

    /**
     * Automatically places a shot on the gamers field.
     * This algorithm is based on an idea by someone at the TU Munich, Germany, who didn't write his name in the
     * comments of his/her C code.
     *
     * @return The new status of the field where the shot was aimed at.
     */
    public Field.Fieldelements placeShotComputer() {
        int sizeX = this.fieldUser.getSizeX();
        int sizeY = this.fieldUser.getSizeY();

        // probability that a field contains a ship (not in percent, 1000 is best)
        int[][] probability = new int[sizeX][sizeY];
        int sumProbability = 0;

        for (int posX = 0; posX < sizeX; posX++) {
            for (int posY = 0; posY < sizeY; posY++) {
                // set probability for each field to 1
                probability[posX][posY] = 1;

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
                        (Field.Fieldelements.SUNK == fieldUser.getFieldStatus(posX + 1, posY) || Field.Fieldelements.WATER == fieldUser.getFieldStatus(posX + 1, posY))
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
        for (int posX = 0; posX < sizeX; posX++) {
            for (int posY = 0; posY < sizeY; posY++) {
                sumProbability += probability[posX][posY];
            }
        }

        // random element
        sumProbability = Math.abs(sumProbability);
        sumProbability++;
        Random random = new Random();
        sumProbability = random.nextInt(sumProbability);
        int posY = -1;
        int posX = -1;
        while (posY < sizeY - 1 && sumProbability >= 0) {
            posY++;
            posX = -1;
            while (posX < sizeX - 1 && sumProbability >= 0) {
                posX++;
                sumProbability = sumProbability - probability[posX][posY];
            }
        }
        lastShotComputerX = posX;
        lastShotComputerY = posY;
        return fieldUser.shoot(posX, posY);
    }

    /**
     * Determines the winner of a game. The game is over as soon as this method returns a valid player.
     *
     * @return COMPUTER if the computer won, USER if the user won, ERROR if no one has won so far
     */
    public Playerelements getWinner() {
        if (this.fieldComputer.getAllShipsSunk()) {
            return Playerelements.COMPUTER;
        }
        if (this.fieldUser.getAllShipsSunk()) {
            return Playerelements.USER;
        }
        return Playerelements.ERROR;
    }

    /**
     * Returns the user or computer field.
     *
     * @param player The owner of the field.
     * @return the owners field
     */
    public Field getField(Playerelements player) {
        if (Playerelements.USER == player) {
            return fieldUser;
        } else {
            return fieldComputer;
        }
    }

    /**
     * Get the X position of the computers last shot.
     *
     * @return x position of last shot
     */
    public int getLastShotComputerX() {
        return lastShotComputerX;
    }

    /**
     * Get the Y position of the computers last shot.
     *
     * @return y position of last shot
     */
    public int getLastShotComputerY() {
        return lastShotComputerY;
    }
}
