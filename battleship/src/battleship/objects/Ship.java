package battleship.objects;

import java.util.ArrayList;

/**
 * Ship object
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Ship implements java.io.Serializable, Cloneable {
    private boolean isset = false;
    private int length = 2;
    private int posX = -1; // start longitude
    private int posY = -1; // start latitude
    private Field.Directionelements direction = Field.Directionelements.HORIZONTAL;
    private boolean sunk = false;
    private String name = "";
    private String image = "";
    private ArrayList<Field.Fieldelements> shots = new ArrayList<Field.Fieldelements>();

    /**
     * Create a new ship.
     *
     * @param length ship length (field units)
     * @param name a descriptive name
     * @param image image file with a picture of this ship
     */
    public Ship(int length, String name, String image) {
        this.length = length;
        this.name = name;
        this.image = image;

        // init ship fields with "SHIP" status (makes sense, doesn't it?)
        for (int i = 0; i < this.length; i++) {
            shots.add(Field.Fieldelements.SHIP);
        }
    }

    /**
     * Set the ships position
     *
     * @param posX X value
     * @param posY Y value
     * @param direction direction (vertical or horizontal)
     */
    public void setPosition(int posX, int posY, Field.Directionelements direction) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
        this.isset = true;
    }

    /**
     * Check if this ship has already been positioned.
     *
     * @return true if positioned, false if not
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isSet() {
        return isset;
    }

    /**
     * Delete the previously set position.
     */
    public void unset() {
        this.posX = -1;
        this.posY = -1;
        this.isset = false;
    }

    /**
     * Check if this ship is still floating.
     *
     * @return true if sunk, false if not
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean getSunk() {
        return sunk;
    }

    /**
     * Get the ships name.
     *
     * @return ship name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the ships image.
     *
     * @return ship image (filename)
     */
    public String getImage() {
        return image;
    }

    /**
     * get the ships position, X value
     *
     * @return positions X value
     */
    public int getPosX() {
        return posX;
    }

    /**
     * get the ships position, Y value
     *
     * @return positions Y value
     */
    public int getPosY() {
        return posY;
    }

    /**
     * get the ships direction (vertical or horizontal)
     *
     * @return ship direction
     */
    public Field.Directionelements getDirection() {
        return direction;
    }

    /**
     * Get the ships length.
     *
     * @return ship length
     */
    public int getLength() {
        return length;
    }

    /**
     * Check if this ship is hit by a shot and update the ship status.
     *
     * @param posX shots X value
     * @param posY shots Y value
     * @return Error when the ship is not hit or all fields have already been shot at, the new field status if not
     */
    public Field.Fieldelements shoot(int posX, int posY) {
        int posXHelper = this.posX;
        int posYHelper = this.posY;
        for (int i = 0; i < this.length; i++) {
            if (posXHelper == posX && posYHelper == posY) { // check that coordinates are actually part of the ship
                Field.Fieldelements statusHelper = this.shots.get(i);
                if (Field.Fieldelements.SUNK == statusHelper || Field.Fieldelements.HIT == statusHelper) { // "occupied"
                    return Field.Fieldelements.ERROR;
                }
                if (Field.Fieldelements.SHIP == statusHelper) {
                    this.shots.set(i, Field.Fieldelements.HIT);
                    // check if all other fields are also HIT and it's time to sink to the ground of the deep, dark and freezing cold ocean
                    boolean sunkHelper = true;
                    for (int j = 0; j < this.length; j++) {
                        if (Field.Fieldelements.HIT != this.shots.get(j)) {
                            sunkHelper = false;
                            break;
                        }
                    }
                    this.sunk = sunkHelper;
                    if (sunkHelper) {
                        // sink the ship
                        for (int j = 0; j < this.length; j++) {
                            this.shots.set(j, Field.Fieldelements.SUNK);
                        }
                        return Field.Fieldelements.SUNK;
                    } else {
                        return Field.Fieldelements.HIT;
                    }
                }
            }
            if (Field.Directionelements.HORIZONTAL == this.direction) {
                posXHelper++;
            } else {
                posYHelper++;
            }
        }
        return Field.Fieldelements.ERROR; // just in case
    }

    /**
     * Clone the ship object.
     *
     * @return cloned ship
     */
    public Ship clone() {
        try {
            return (Ship) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
