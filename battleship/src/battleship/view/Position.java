package battleship.view;

import battleship.logic.Game;
import battleship.logic.Gameplay;
import battleship.logic.Instance;
import battleship.objects.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * position Panel
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Position extends JFrame implements ActionListener, IFieldObserver {

    private static final long serialVersionUID = 1L;
    private Application app;
    private FieldView myOcean;
    private battleship.logic.Game game;

    private JLabel label = new JLabel("Set Ship:");
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JButton back = new JButton("BACK");
    private JPanel positionPanel = new JPanel();

    /**
     * show the FieldView for set the ships
     *
     * @param app
     */
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        //create FieldView
        myOcean = new FieldView(gameplay.getGame().getField(Game.Playerelements.USER));
        myOcean.setShowAllShips(true);
        myOcean.register(this);
        myOcean.setBounds(30, 28, 271, 271);
        positionPanel.add(myOcean);
        
        positionPanel.add(label);
        positionPanel.add(label2);
        positionPanel.add(label3);
        positionPanel.add(back);

        positionPanel.setComponentZOrder(label, 0);
        positionPanel.setComponentZOrder(label2, 0);
        positionPanel.setComponentZOrder(label3, 0);
        positionPanel.setComponentZOrder(back, 0);
        positionPanel.setComponentZOrder(myOcean, 0);

        positionPanel.setBackground(Color.BLACK);

        getContentPane().add(positionPanel);

        showShipDetails(game.getNextUnsetShip());

        actionPosition();
    }

    /**
     * add ActionListener on back
     */
    public void actionPosition() {
        back.addActionListener(this);
    }

    /**
     * back Button to get to the menu
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            app.menu();
        }
    }

    /**
     * shows the current to be set ship, name and length
     *
     * @param ship
     */
    private void showShipDetails(Ship ship) {
        label2.setText(ship.getName());
        label3.setText(ship.getLength() + " fields");
        JPanel picture = new PicturePanel(ship.getImage(), 200, 120);
        picture.setBounds(350, 180, 200, 120);
        positionPanel.add(picture);
        positionPanel.setComponentZOrder(picture, 1);
        picture.repaint();
    }

    /**
     * after click on the field a popup is display with the choice between horizontal and vertical
     * the position of the ship x, y and direction are saved
     * if the ship is set, the next ship will be displayed
     *
     * @param x
     * @param y
     */
    @Override
    public void fieldClicked(int x, int y) {
        battleship.objects.Field.Directionelements direction = null;
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Place the ship:");
        Object[] dirOptions = {"vertical", "horizontal"};
        int directionHelper = JOptionPane.showOptionDialog(panel, label, "Choose the direction", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, dirOptions, null);
        if (0 == directionHelper) {
            direction = battleship.objects.Field.Directionelements.VERTICAL;
        } else if (1 == directionHelper) {
            direction = battleship.objects.Field.Directionelements.HORIZONTAL;
        }

        Ship ship = game.getNextUnsetShip();
        if (null != ship && null != direction) {
            ship.setPosition(x, y, direction);
            if (!game.placeShipUser(ship)) {
                ship.unset();
            } else {
                //create Ships
                //battleship.view.Ship myShip = new battleship.view.Ship(ship, myOcean.getGraphics(), battleship.objects.FieldView.Fieldelements.SHIP);
                //myShip.setVisible(true);
                //myOcean.add(myShip);
            }
            ship = game.getNextUnsetShip();
            if (null != ship) {
                showShipDetails(ship);
            } else {
                app.positionFinished();
            }
            myOcean.repaint();
        }
    }
}
