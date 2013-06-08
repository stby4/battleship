package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Game Battleship
 * @author Tom Ohme
 *
 */
public class Game extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;
    private Field myOcean;
    private Field enemyOcean;

    //Anzeigen wer dran ist (Spieler oder Computer)
    JLabel label = new JLabel();
    //Anzeigen der Treffer/versenkten Schiffe/Schüsse des Spieler und Computer mit GridLayout
    GridLayout grid = new GridLayout(3,1);
    JLabel label2 = new JLabel();
    //noch mehr Labels
    JButton exit = new JButton("EXIT");
    JPanel gamePanel = new JPanel();

    public Game(Application app) {
        this.app = app;

        setTitle("Game Battleship");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 400) / 2;
        int left = (screenSize.width - 600) / 2;
        setSize(600, 400);
        setLocation(left, top);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(Color.lightGray);
        setResizable(false);

        gamePanel.setLayout(null);

        //definieren der Bounds für Labels und GridLayout
        exit.setBounds(50, 325, 110, 25);

        //setForeground White setBackground black
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setFocusable(false);

        //create myOcean Field
        myOcean = new Field();
        myOcean.setBounds(30, 28, 271, 271); //myOcean.setBounds(50, 48, 250, 250);
        gamePanel.add(myOcean);

        //create enemyOcean Field
        enemyOcean = new Field();
//		enemyOcean.setBounds(x, y, 271, 271);
        gamePanel.add(enemyOcean);

        gamePanel.add(label);
        gamePanel.add(exit);

        //setComponentZOrder
        gamePanel.setComponentZOrder(exit, 0);
        gamePanel.setComponentZOrder(myOcean, 0);
        gamePanel.setComponentZOrder(enemyOcean, 0);

        gamePanel.setBackground(Color.BLACK);

        getContentPane().add(gamePanel);

        actiongame();
    }

    public void actiongame() {
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            //instead store game
            app.menu();
        }
    }



}
