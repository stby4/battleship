package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
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
    JPanel detailsPanel = new JPanel();
    JLabel username = new JLabel("You");
    JLabel userShot = new JLabel("0" + " shots");
    JLabel userHit = new JLabel("0" + " hit");
    JLabel opponentname = new JLabel("Opponent");
    JLabel opponentShot = new JLabel("0" + " shots");
    JLabel opponentHit = new JLabel("0" + " hit");    
    
    JButton exit = new JButton("EXIT");
    JPanel gamePanel = new JPanel();

    public Game(Application app) {
        this.app = app;

        setTitle("Game Battleship");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 450) / 2;
        int left = (screenSize.width - 650) / 2;
        setSize(650, 450);
        setLocation(left, top);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(Color.lightGray);
        setResizable(false);

        detailsPanel.setLayout(null);
        gamePanel.setLayout(null);
        
        username.setBounds(140, 10, 110, 25);
        userShot.setBounds(140, 25, 110, 25);
        userHit.setBounds(140, 40, 110, 25);
        opponentname.setBounds(420, 10, 110, 25);
        opponentShot.setBounds(420, 25, 110, 25);
        opponentHit.setBounds(420, 40, 110, 25);
        detailsPanel.add(username);
        detailsPanel.add(userShot);
        detailsPanel.add(userHit);
        detailsPanel.add(opponentname);
        detailsPanel.add(opponentShot);
        detailsPanel.add(opponentHit);
        detailsPanel.setSize(650, 70);
        
        exit.setBounds(50, 375, 110, 25);

        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setFocusable(false);

        //create myOcean Field
        myOcean = new Field();
        myOcean.setBounds(30, 78, 271, 271); 
        gamePanel.add(myOcean);

        //create enemyOcean Field
        enemyOcean = new Field();
		enemyOcean.setBounds(325, 78, 271, 271);
        gamePanel.add(enemyOcean);

        //wird dynamisch
        detailsPanel.setBackground(Color.GREEN);
        
        gamePanel.add(detailsPanel);
        gamePanel.add(exit);

        gamePanel.setComponentZOrder(detailsPanel, 0);
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
            app.gameExit();
        }
    }


}
