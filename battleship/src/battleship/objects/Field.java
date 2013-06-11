package battleship.objects;

import java.util.ArrayList;


/**
 * Field
 *
 * @author H. Kaestner
 */
public class Field implements java.io.Serializable {
    static public enum Fieldelements {
        WATER, // virgin field
        SHIP,  // ship in perfect condition
        HIT,   // ship with a hit
        SHOT,  // a shot in the water
        SUNK,  // sunk ship
        ERROR  // everything else
    }

    static public enum Directionelements {
        HORIZONTAL,
        VERTICAL
    }

    private int sizeX = 10;
    private int sizeY = 10;
    private ArrayList<ArrayList<Fieldelements>> field = new ArrayList<ArrayList<Fieldelements>>();

    private ArrayList<Ship> ships = new ArrayList<Ship>();

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

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

    /**
     *
     * @return boolean true if all ships are sunk, false if not
     */
    public boolean getAllShipsSunk() {
        for(Ship ship : this.ships) {
            if(!ship.getSunk()) {
                return false;
            }
        }
        return true;
    }

    public Fieldelements getFieldStatus(int posX, int posY) {
        if (posX < 0 || posX >= this.sizeX || posY < 0 || posY >= this.sizeY) {
            return Fieldelements.ERROR;
        }
        return this.field.get(posX).get(posY);
    }

    public Fieldelements setFieldStatus(int posX, int posY, Fieldelements status) {
        if (posX < 0 || posX >= this.sizeX || posY < 0 || posY >= this.sizeY) {
            return Fieldelements.ERROR;
        }
        Fieldelements place = this.getFieldStatus(posX, posY);

        // set to ship
        switch (status) {
            case SHIP:
                if (Fieldelements.WATER == place) {
                    field.get(posX).set(posY, status);
                } else {
                    return Fieldelements.ERROR;
                }
                break;
            case SHOT:
                if (Fieldelements.WATER == place) {
                    field.get(posX).set(posY, Fieldelements.SHOT);
                } else if (Fieldelements.SHIP == place) {
                    field.get(posX).set(posY, Fieldelements.HIT);
                } else {
                    return Fieldelements.ERROR;
                }
                break;
            case SUNK:
                if (Fieldelements.HIT == place) {
                    field.get(posX).set(posY, status);
                } else {
                    return Fieldelements.ERROR;
                }
                break;
        }
        return this.getFieldStatus(posX, posY);
    }

    public boolean addShip(Ship ship) {
        int shipPosX = ship.getPosX();
        int shipPosY = ship.getPosY();
        int shipLength = ship.getLength();

        // check if ship is within field borders
        if ((Directionelements.HORIZONTAL == ship.getDirection() && shipPosX + shipLength > this.sizeX) || (Directionelements.VERTICAL == ship.getDirection() && shipPosY + shipLength > this.sizeY)) {
            return false;
        }

        // collision detection
        // horizontal ship
        if (Directionelements.HORIZONTAL == ship.getDirection()) {
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
            }
            this.ships.add(ship);
            return true;
        }

        // vertical ship
        if (Directionelements.VERTICAL == ship.getDirection()) {
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
            }
            this.ships.add(ship);
            return true;
        }
        // just in case
        return false;
    }

    public Fieldelements shoot(int posX, int posY) {
        switch (setFieldStatus(posX, posY, Fieldelements.SHOT)) {
            case HIT:
                Fieldelements shipStatus;
                for(Ship ship : this.ships) {
                    shipStatus = ship.shoot(posX, posY);
                    if(Fieldelements.SUNK == shipStatus) {
                        int posXHelper = posX;
                        int posYHelper = posY;
                        for(int i=0; i<ship.getLength(); i++) {
                            setFieldStatus(posXHelper, posYHelper, Fieldelements.SUNK);
                            if(Directionelements.HORIZONTAL == ship.getDirection()) {
                                posXHelper++;
                            } else {
                                posYHelper++;
                            }
                        }
                        break;
                    }
                    if(Fieldelements.ERROR != shipStatus) {
                        break;
                    }
                }
            default:
                return getFieldStatus(posX, posY);
        }
    }
}
