package battleship.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldMouseEvent implements MouseListener, Runnable {
    private int x;
    private int y;

    public void register(JPanel jPanel) {
        jPanel.addMouseListener(this);
    }

    public void run() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = (int) (e.getPoint().getX() / 25);
        y = (int) (e.getPoint().getY() / 25);


        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Schiff setzen:");
        JButton vertical = new JButton("vertical");
        JButton horizontal = new JButton("horizontal");
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(vertical);
        bgroup.add(horizontal);
        label.setForeground(Color.WHITE);
        vertical.setForeground(Color.WHITE);
        vertical.setFocusable(false);
        horizontal.setForeground(Color.WHITE);
        horizontal.setFocusable(false);
        Object[] direction = {vertical, horizontal};
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        vertical.setBackground(Color.BLACK);
        horizontal.setBackground(Color.BLACK);
        JOptionPane.showOptionDialog(panel, label, "Select a Option", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, direction, null);

        if (e.getSource() == vertical || e.getSource() == horizontal) {
            System.exit(0);
        }

        System.out.println(x); //zum testen
        System.out.println(y); //zum testen

        notify();


        //ship.setPosition(x, y, direction)
        //Feld weiss einf�rben +Felder horizontal oder vertical f�r Schiff //+Felder speichern

    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
