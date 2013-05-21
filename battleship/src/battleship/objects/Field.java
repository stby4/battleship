package battleship.objects;

import java.util.ArrayList;


/**
 * Field
 *
 * @author H. Kaestner, T. Ohme
 */
public class Field {
    public enum Fieldelements {
        WATER,
        SHIP,
        HIT,
        SHOT,
        SUNK,
        ERROR
    }

    private int sizeX = 10;
    private int sizeY = 10;
    /*
     * possible integer values for shots:
     *  0: no shot
     *  1: shot
     */
    private ArrayList<ArrayList<Fieldelements>> field = new ArrayList<ArrayList<Fieldelements>>();

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        for (int i = 0; i < this.sizeX; i++) {
            this.field.add(new ArrayList<Fieldelements>());
            for (int j = 0; j < this.sizeY; j++) {
                this.field.get(i).add(Fieldelements.WATER);
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Fieldelements getFieldStatus(int posX, int posY) {
        return this.field.get(posX).get(posY);
    }

    public Fieldelements setFieldStatus(int posX, int posY, Fieldelements status) {
        if (posX < 0 || posX >= this.sizeX || posY < 0 || posY >= this.sizeY) {
            return Fieldelements.ERROR;
        }
        Fieldelements place = this.getFieldStatus(posX, posY);

        // set to ship
        if (Fieldelements.SHIP == status) {
            if (Fieldelements.WATER == place) {
                field.get(posX).set(posY, status);
            } else {
                return Fieldelements.ERROR;
            }
        }

        // a shot
        if (Fieldelements.SHOT == status) {
            if (Fieldelements.WATER == place) {
                field.get(posX).set(posY, Fieldelements.SHOT);
            } else if (Fieldelements.SHIP == place) {
                field.get(posX).set(posY, Fieldelements.HIT);
            } else {
                return Fieldelements.ERROR;
            }
        }

        // sink it!
        if (Fieldelements.SUNK == status) {
            if (Fieldelements.HIT == place) {
                field.get(posX).set(posY, status);
            } else {
                return Fieldelements.ERROR;
            }
        }

        return this.getFieldStatus(posX, posY);
    }

    public boolean addShip(Ship ship) {
        int shipPosX = ship.getPosX();
        int shipPosY = ship.getPosY();
        int shipLength = ship.getLength();

        // check if ship is within field borders
        if ((0 == ship.getDirection() && shipPosX + shipLength > this.sizeX) || (1 == ship.getDirection() && shipPosY + shipLength > this.sizeY)) {
            return false;
        }

        // collision detection
        // horizontal ship
        if (0 == ship.getDirection()) {
            // check for a neighbouring ship above
            for (int i = -1; i <= shipLength; i++) {
                if (Fieldelements.SHIP == getFieldStatus(shipPosX + i, shipPosY - 1)) {
                    return false;
                }
            }
            // check for ship at the same place
            for (int i = -1; i <= shipLength; i++) {
                if (Fieldelements.SHIP == getFieldStatus(shipPosX + i, shipPosY)) {
                    return false;
                }
            }
            // check for neighbouring ship below
            for (int i = -1; i <= shipLength; i++) {
                if (Fieldelements.SHIP == getFieldStatus(shipPosX + i, shipPosY + 1)) {
                    return false;
                }
            }
            // set fields to SHIP
            for (int i = 0; i < shipLength; i++) {
                setFieldStatus(shipPosX + i, shipPosY, Fieldelements.SHIP);
                //this.ships.add(ship);
                return true;
            }
        }

        // vertical ship
        if (1 == ship.getDirection()) {
            // check for neighbouring ship on the left
            for (int i = -1; i <= shipLength; i++) {
                if (Fieldelements.SHIP == getFieldStatus(shipPosX - 1, shipPosY + i)) {
                    return false;
                }
            }
            // check for ship at the same place
            for (int i = -1; i <= shipLength; i++) {
                if (Fieldelements.SHIP == getFieldStatus(shipPosX, shipPosY + i)) {
                    return false;
                }
            }
            // check for neighbouring ship on the right
            for (int i = -1; i <= shipLength; i++) {
                if (Fieldelements.SHIP == getFieldStatus(shipPosX + 1, shipPosY + i)) {
                    return false;
                }
            }
            // set fields to SHIP
            for (int i = 0; i < shipLength; i++) {
                setFieldStatus(shipPosX, shipPosY + i, Fieldelements.SHIP);
                //this.ships.add(ship);
                return true;
            }
        }
        // just in case
        return false;
    }

    public boolean shoot(int x, int y) {
        // TODO shoot
        return true; // false if field is not available or has already been shot on
    }
}
