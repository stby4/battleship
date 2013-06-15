package battleship.view;

import battleship.logic.Gameplay;
import battleship.logic.Instance;
import battleship.logic.Game.Playerelements;
import battleship.objects.Field.Fieldelements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Game Battleship
 *
 * @author Tom Ohme
 */
public class Game extends JFrame implements ActionListener, IFieldObserver {

    private static final long serialVersionUID = 1L;
    private Application app;
    private Gameplay gameplay;
    private battleship.logic.Game game;
    private Field myOcean;
    private Field enemyOcean;

    //Anzeigen wer dran ist (Spieler oder Computer)
    JPanel detailsPanel = new JPanel();
    JLabel username = new JLabel("You");
    JLabel userShotAnz = new JLabel();
    JLabel userShot = new JLabel("shots");
    JLabel userHitAnz = new JLabel();
    JLabel userHit = new JLabel("hit");
    JLabel opponentname = new JLabel("Opponent");
    JLabel opponentShotAnz = new JLabel();
    JLabel opponentShot = new JLabel("shots");
    JLabel opponentHitAnz = new JLabel();
    JLabel opponentHit = new JLabel("hit");

    JButton exit = new JButton("EXIT");
    JPanel gamePanel = new JPanel();

    public Game(Application app) {
        this.app = app;
        Instance instance = Instance.getInstance();
        gameplay = instance.getGameplay();

        setTitle("Game Battleship");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 450) / 2;
        int left = (screenSize.width - 650) / 2;
        setSize(650, 450);
        setLocation(left, top);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        myOcean.removeAll();
        drawShipUser(Playerelements.USER);
        gamePanel.add(myOcean);

        //create enemyOcean Field
        enemyOcean = new Field();
        enemyOcean.setBounds(325, 78, 271, 271);
        enemyOcean.register(this);
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
    
    public void drawShipUser(Playerelements player) {
    	ArrayList<battleship.objects.Ship> userShips = game.getField(player).getShips();
    	for (battleship.objects.Ship ship : userShips) {
			Ship userShip = new Ship(ship, myOcean.getGraphics());
			myOcean.add(userShip);
			myOcean.setVisible(true);
		}
    }

    public void drawSunkShips(Playerelements player) {
        Field ocean;
        if(Playerelements.USER == player) {
            ocean = myOcean;
        } else {
            ocean = enemyOcean;
        }
        ArrayList<battleship.objects.Ship> sunkShips = gameplay.getGame().getSunkShips(player);
        for(battleship.objects.Ship ship : sunkShips) {
            Ship sunkShip = new Ship(ship, ocean.getGraphics());
            sunkShip.setVisible(true);
            ocean.add(sunkShip);
        }
    }

    @Override
    public void fieldClicked(int x, int y) {
        //if (gameplay.getCurrentPlayer() == Playerelements.USER) {
        if (true) {
            System.out.println("X: " + x + " Y: " + y);
            Fieldelements desaster = gameplay.shoot(x, y);
            switch(desaster) {
                case SHOT:
                    Shot shot = new Shot(enemyOcean.getGraphics(), x, y, Fieldelements.SHOT);
                    enemyOcean.add(shot);
                    shot.setVisible(true);
                    break;
                case HIT:
                    Shot hit = new Shot(enemyOcean.getGraphics(), x, y, Fieldelements.HIT);
                    enemyOcean.add(hit);
                    break;
                case SUNK:
                    drawSunkShips(Playerelements.COMPUTER);
                    break;
            }
        }

        // prepare the next shot
        if(gameplay.getCurrentPlayer() == Playerelements.USER) {
            detailsPanel.setBackground(Color.GREEN);
        } else {
            detailsPanel.setBackground(Color.RED);
        }
    }
    
    public int shotOnField() {
    	int shotUser = 0;
    	int shotComputer = 0;
    	if (gameplay.getCurrentPlayer() == Playerelements.USER) {
    		shotUser++;
    		return shotUser;
    	} else {
    		shotComputer++;
    		return shotComputer;
    	}	
    }
    
    public int shotOnShip() {
    	int shotUser = 0;
    	int shotComputer = 0;
    	if ()
    }
}
