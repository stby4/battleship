package battleship.objects;

import java.util.ArrayList;

/**
 * Ship
 *
 * @author H. Kaestner, T. Ohme
 */
public class Ship {
    private int length = 2;
    private int posX = 0;
    private int posY = 0;
    /*
     * possible integer values for direction:
     *  0: horizontal
     *  1: vertical
     */
    private int direction = 0;
    private boolean sunk = false;
    /*
     * possible integer values for shots:
     *  0: no shot
     *  1: shot
     */
    private ArrayList<Integer> shots = new ArrayList<Integer>();

    public Ship(int length) {
        this.length = length;

        for (int i = 0; i < this.length; i++) {
            shots.add(0);
        }
    }

    public void setPosition(int posX, int posY, int direction) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public boolean shoot(int position) {
        // TODO shoot on ship
        return false; // true of ship was sunk
    }
}
