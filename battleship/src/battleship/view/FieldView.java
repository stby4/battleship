package battleship.view;

import battleship.objects.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * playing field
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class FieldView extends JPanel implements ActionListener, MouseListener {

    private IFieldObserver observer;
    private static final long serialVersionUID = 1L;
    private boolean showAllShips = false;
    private battleship.objects.Field field;
    private Color shipColor = new Color(186, 237, 116);
    private Color shipSunkColor = new Color(225, 34, 41);
    private static final Color hit = new Color(228, 219, 16);
    private static final Color noHit = new Color(26, 71, 190);

    /**
     * add MouseListener
     */
    public FieldView(battleship.objects.Field field) {
        this.field = field;
        this.addMouseListener(this);
    }

    /**
     * draws the field(10x10), ships and shots
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 271, 271);
        g.setColor(Color.WHITE);
        //10 X 10 square
        for (int i = 0; i < 11; i++) {
            g.drawLine(20, 20 + i * 25, 271, 20 + i * 25);
        }
        for (int i = 0; i < 11; i++) {
            g.drawLine(20 + i * 25, 20, 20 + i * 25, 271);
        }

        char ch = '0';
        for (int i = 0; i < 10; i++) {                    //Spalten Koordinaten oben
            g.drawString(ch + " ", 30 + 25 * i, 13);
            ch++;
        }
        ch = 'A';
        for (int i = 0; i < 10; i++) {                    //Spalten Koordinaten links
            g.drawString(ch + " ", 5, 40 + 25 * i);
            ch++;
        }

        // draw ships
        for (Ship ship : field.getShips()) {
            if (ship.getSunk() || showAllShips) {
                paintShip(g, ship);
            }
        }

        // draw shots
        for (int x = 0; x < field.getSizeX(); x++) {
            for (int y = 0; y < field.getSizeY(); y++) {
                battleship.objects.Field.Fieldelements status = field.getFieldStatus(x, y);
                if (status == battleship.objects.Field.Fieldelements.HIT) {
                    paintHit(g, x, y);
                } else if (status == battleship.objects.Field.Fieldelements.SHOT) {
                    paintShot(g, x, y);
                }
            }
        }
    }

    /**
     * paint Shot on field when no hit (color blue)
     *
     * @param g
     * @param x
     * @param y
     */
    private void paintShot(Graphics g, int x, int y) {
        g.setColor(noHit);
        g.fillOval(x * 25 + 23, y * 25 + 23, 20, 20); //mouseClicked
    }

    /**
     * paint Shot on field when hit (color yellow)
     *
     * @param g
     * @param x
     * @param y
     */
    private void paintHit(Graphics g, int x, int y) {
        g.setColor(hit);
        g.fillRect(x * 25 + 23, y * 25 + 23, 20, 20); //mouseClicked
    }

    /**
     *  draws the ship vertical or horizontal starting point x and y coordinates
     *  when a ship sunk it is marked red
     *
     * @param g
     * @param ship
     */
    private void paintShip(Graphics g, Ship ship) {
        g.setColor(shipColor);
        if (ship.getSunk()) {
            g.setColor(shipSunkColor);
        }
        int l = ship.getLength();
        if (battleship.objects.Field.Directionelements.VERTICAL == ship.getDirection()) {
            g.fillRect(ship.getPosX() * 25 + 23, ship.getPosY() * 25 + 23, 20, 25 * l - 5);

        } else {
            g.fillRect(ship.getPosX() * 25 + 23, ship.getPosY() * 25 + 23, 25 * l - 5, 20);
        }
    }

    /**
     * set of the ships
     *
     * @param display
     */
    public void setShowAllShips(boolean display) {
        this.showAllShips = display;
    }

    /**
     * interface for mouse click
     *
     * @param observer
     */
    public void register(IFieldObserver observer) {
        this.observer = observer;
    }

    /**
     * set observer on null
     */
    public void unregisterAll() {
        this.observer = null;
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    /**
     * mouseClicked Event gives the x and y coordinates after click to the observer
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (null != this.observer) {
            int x = (int) (e.getPoint().getX() / 25) - 1;
            int y = (int) (e.getPoint().getY() / 25) - 1;
            if (0 <= x && 0 <= y && 10 > x && 10 > y) {
                this.observer.fieldClicked(x, y);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}