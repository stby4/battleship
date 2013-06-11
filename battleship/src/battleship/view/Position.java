package battleship.view;

import battleship.logic.Gameplay;
import battleship.logic.Instance;
import battleship.objects.Ship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Position Battleship
 *
 * @author Tom Ohme
 */
public class Position extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;
    private Field myOcean;
    private battleship.logic.Game game;

    JLabel label = new JLabel("Set Ship:");
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JButton next = new JButton("Next");
    JButton back = new JButton("Back");
    JPanel positionPanel = new JPanel();

    public Position(Application app) {
        this.app = app;
        Instance instance = Instance.getInstance();
        Gameplay gameplay = instance.getGameplay();
        game = gameplay.getGame();

        setTitle("Position Battleship");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 400) / 2;
        int left = (screenSize.width - 600) / 2;
        setSize(600, 400);
        setLocation(left, top);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(Color.lightGray);
        setResizable(false);

        label.setFont(new Font("Arial", Font.BOLD, 16));

        positionPanel.setLayout(null);

        label.setBounds(350, 48, 110, 25);
        label2.setBounds(400, 73, 110, 25);
        label3.setBounds(400, 93, 110, 25);
        back.setBounds(50, 325, 110, 25);

        label.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFocusable(false);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFocusable(false);

        //create Field
        myOcean = new Field();
        myOcean.setBounds(30, 28, 271, 271);
        positionPanel.add(myOcean);
    }

    public void setShips() {
        for (Ship ship : game.getShipTypes()) {
            if (!ship.isSet()) {
                label2.setText(ship.getName());
                label3.setText(ship.getLength() + " Felder");
                JPanel picture = new PicturePanel(ship.getImage(), 200, 120);
                picture.setBounds(350, 180, 200, 120);
                positionPanel.add(picture);
                positionPanel.setComponentZOrder(picture, 1);
                FieldMouseEvent event = new FieldMouseEvent();
                event.register(positionPanel);
                Thread mouse = new Thread(event);
                mouse.start();
                try {
                    mouse.wait();
                } catch (Exception e) {

                }
                // TODO Hinrich, get mouseclick coords and vertical/horizontal
                //ship.setPosition();
                game.placeShipUser(ship);
                // TODO Tom, display ship
            }
        }

        //if alle Schiff gesetzt next Button erstellt  --> Game
        //next.setBounds();


        positionPanel.add(label);
        positionPanel.add(label2);
        positionPanel.add(label3);
        positionPanel.add(back);
        positionPanel.add(next);

        positionPanel.setComponentZOrder(label, 0);
        positionPanel.setComponentZOrder(label2, 0);
        positionPanel.setComponentZOrder(label3, 0);
        positionPanel.setComponentZOrder(back, 0);
        positionPanel.setComponentZOrder(next, 0);
        positionPanel.setComponentZOrder(myOcean, 0);

        positionPanel.setBackground(Color.BLACK);

        getContentPane().add(positionPanel);

        actionPosition();
    }

    public void actionPosition() {
        next.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            //wird angezeigt wenn alle Schiff gesetzt sind noch Bild vlt. mit Schüssen
            app.game();
        } else if (e.getSource() == back) {
            app.menu();
        }
    }

}
