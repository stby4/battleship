package battleship.objects;

import java.util.ArrayList;

/**
 * Ship
 *
 * @author H. Kaestner
 */
public class Ship {
    private boolean isset = false;
    private int length = 2;
    private int posX = 0; // start longitude
    private int posY = 0; // start latitude
    private Field.Directionelements direction = Field.Directionelements.HORIZONTAL;
    private boolean sunk = false;
    private String name = "";
    private String image = "";
    private ArrayList<Field.Fieldelements> shots = new ArrayList<Field.Fieldelements>();

    public Ship(int length, String name, String image) {
        this.length = length;
        this.name = name;
        this.image = image;

        // init ship fields with "SHIP" status (makes sense, doesn't it?)
        for (int i = 0; i < this.length; i++) {
            shots.add(Field.Fieldelements.SHIP);
        }
    }

    public void setPosition(int posX, int posY, Field.Directionelements direction) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
        this.isset = true;
    }

    public boolean isSet() {
        return isset;
    }

    public void setSet(boolean isset) {
        this.isset = isset;
    }

    public boolean getSunk() {
        return sunk;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Field.Directionelements getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public Field.Fieldelements shoot(int posX, int posY) {
        int posXHelper = this.posX;
        int posYHelper = this.posY;
        for (int i = 0; i < this.length; i++) {
            Field.Fieldelements statusHelper = this.shots.get(i);
            if (posXHelper == posX && posYHelper == posY) { // check that coordinates are actually part of the ship
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
}
