package battleship.view;

import battleship.objects.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Field Battleship
 *
 * @author Tom Ohme
 */
public class Field extends JPanel implements ActionListener, MouseListener {

    private IFieldObserver observer;
    private static final long serialVersionUID = 1L;

    public Field() {
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
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
    }

    public void register(IFieldObserver observer) {
        this.observer = observer;
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

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