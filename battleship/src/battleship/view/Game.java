package battleship.view;

import battleship.logic.Gameplay;
import battleship.logic.Instance;
import battleship.logic.Game.Playerelements;
import battleship.objects.Field.Fieldelements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
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
    private Field myOcean;
    private Field enemyOcean;
    private int userShots = 0;
    private int userHits = 0;
    private int opponentShots = 0;
    private int opponentHits = 0;

    private JPanel detailsPanel = new JPanel();
    private Color detailsPanelGreen = new Color(87, 174, 32);
    private Color detailsPanelRed = new Color(200, 41, 47);
    private JLabel username = new JLabel("You");
    private JLabel userShotAnz = new JLabel("0");
    private JLabel userShot = new JLabel("shots");
    private JLabel userHitAnz = new JLabel("0");
    private JLabel userHit = new JLabel("hit");
    private JLabel opponentName = new JLabel("Opponent");
    private JLabel opponentShotAnz = new JLabel("0");
    private JLabel opponentShot = new JLabel("shots");
    private JLabel opponentHitAnz = new JLabel("0");
    private JLabel opponentHit = new JLabel("hit");

    private JButton exit = new JButton("EXIT");
    private JPanel gamePanel = new JPanel();

    public Game(Application app, boolean rebuild) {
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
        userShotAnz.setBounds(140, 25, 40, 25);
        userShot.setBounds(155, 25, 70, 25);
        userHitAnz.setBounds(140, 40, 70, 25);
        userHit.setBounds(155, 40, 110, 25);
        opponentName.setBounds(420, 10, 110, 25);
        opponentShotAnz.setBounds(420, 25, 110, 25);
        opponentShot.setBounds(435, 25, 110, 25);
        opponentHitAnz.setBounds(420, 40, 110, 25);
        opponentHit.setBounds(435, 40, 110, 25);
        detailsPanel.add(username);
        detailsPanel.add(userShotAnz);
        detailsPanel.add(userShot);
        detailsPanel.add(userHitAnz);
        detailsPanel.add(userHit);
        detailsPanel.add(opponentName);
        detailsPanel.add(opponentShotAnz);
        detailsPanel.add(opponentShot);
        detailsPanel.add(opponentHitAnz);
        detailsPanel.add(opponentHit);
        detailsPanel.setSize(650, 70);

        exit.setBounds(50, 375, 110, 25);

        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.setFocusable(false);

        //create myOcean Field
        myOcean = new Field(gameplay.getGame().getField(Playerelements.USER));
        myOcean.setShowAllShips(true);
        myOcean.setBounds(30, 78, 271, 271);
        myOcean.removeAll();
        gamePanel.add(myOcean);

        //create enemyOcean Field
        enemyOcean = new Field(gameplay.getGame().getField(Playerelements.COMPUTER));
        enemyOcean.setBounds(325, 78, 271, 271);
        enemyOcean.register(this);
        gamePanel.add(enemyOcean);

        detailsPanel.setBackground(detailsPanelGreen);

        gamePanel.add(detailsPanel);
        gamePanel.add(exit);

        gamePanel.setComponentZOrder(detailsPanel, 0);
        gamePanel.setComponentZOrder(exit, 0);
        gamePanel.setComponentZOrder(myOcean, 0);
        gamePanel.setComponentZOrder(enemyOcean, 0);

        gamePanel.setBackground(Color.BLACK);

        getContentPane().add(gamePanel);

        actionGame();

        if (rebuild) {
            rebuildShots();
        }

        progressGameplay();
    }

    public void actionGame() {
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            //instead store game
            app.gameExit();
        }
    }

    /**
     * display all shots on the user field, placed by the computer, in a previous game
     */
    private void rebuildShots() {
        for (int i = 0; i < 2; i++) {
            battleship.objects.Field field;
            Field ocean;
            if (0 == i) {
                field = gameplay.getGame().getField(Playerelements.USER);
                ocean = myOcean;
            } else {
                field = gameplay.getGame().getField(Playerelements.COMPUTER);
                ocean = enemyOcean;
            }

            for (int x = 0; x < field.getSizeX(); x++) {
                for (int y = 0; y < field.getSizeY(); y++) {
                    switch (field.getFieldStatus(x, y)) {
                        case SHOT:
                            //Shot shot = new Shot(ocean.getGraphics(), x, y, Fieldelements.SHOT);
                            //ocean.add(shot);
                            ocean.repaint();
                            break;
                        case HIT:
                            //Shot hit = new Shot(ocean.getGraphics(), x, y, Fieldelements.HIT);
                            //ocean.add(hit);
                            ocean.repaint();
                            if(0 == i) {
                                opponentHits++;
                            } else {
                                userHits++;
                            }
                            break;
                    }
                    if(0 == i) {
                        opponentShots++;
                    } else {
                        userShots++;
                    }
                }
            }
        }
        //drawSunkShips(Playerelements.COMPUTER);
        //drawSunkShips(Playerelements.USER);
        updateStatistics();
    }

    private void shootComputer() {
        detailsPanel.setBackground(Color.RED);
/*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {
        }
*/
        Fieldelements lastShot;
        battleship.logic.Game game = gameplay.getGame();

        lastShot = gameplay.shoot();
        int x = game.getLastShotComputerX();
        int y = game.getLastShotComputerY();
        switch (lastShot) {
            case SHOT:
                //Shot shot = new Shot(myOcean.getGraphics(), x, y, Fieldelements.SHOT);
                //myOcean.add(shot);
                break;
            case HIT:
                //Shot hit = new Shot(myOcean.getGraphics(), x, y, Fieldelements.HIT);
                //myOcean.add(hit);
                opponentHits++;
                break;
            case SUNK:
                //drawSunkShips(Playerelements.COMPUTER);
                opponentHits++;
                break;
        }
        myOcean.repaint();
        opponentShots++;

        // prepare the next shot
        progressGameplay();
    }

    @Override
    public void fieldClicked(int x, int y) {
        if (gameplay.getCurrentPlayer() == Playerelements.USER) {
            Fieldelements disaster = gameplay.shoot(x, y);
            switch (disaster) {
                case SHOT:
                    //Shot shot = new Shot(enemyOcean.getGraphics(), x, y, Fieldelements.SHOT);
                    //enemyOcean.add(shot);
                    break;
                case HIT:
                    //Shot hit = new Shot(enemyOcean.getGraphics(), x, y, Fieldelements.HIT);
                    //enemyOcean.add(hit);
                    userHits++;
                    break;
                case SUNK:
                    //drawSunkShips(Playerelements.USER);
                    userHits++;
                    break;
                case ERROR:
                    userShots--;
                    break;
            }
            enemyOcean.repaint();
            userShots++;
            // prepare the next shot
            progressGameplay();
        }
    }

    private void progressGameplay() {
        // check for the end of the game
        Playerelements winner = gameplay.getGame().getWinner();
        if (Playerelements.COMPUTER == winner) {
            gameplay.getUser().addDefeat();
            JPanel panel = new JPanel();
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(panel, "You lose", "Message", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            app.menu();
            return;
        } else if (Playerelements.USER == winner) {
            gameplay.getUser().addVictory();
            JPanel panel = new JPanel();
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(panel, "You win", "Message", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            app.menu();
            return;
        }

        updateStatistics();

        // prepare the next round
        if (gameplay.getCurrentPlayer() == Playerelements.USER) {
            detailsPanel.setBackground(detailsPanelGreen);
            enemyOcean.register(this);
        } else {
            enemyOcean.unregisterAll();
            detailsPanel.setBackground(detailsPanelRed);
            shootComputer();
        }
    }

    private void updateStatistics() {
        userHitAnz.setText("" + userHits);
        userShotAnz.setText("" + userShots);
        opponentHitAnz.setText("" + opponentHits);
        opponentShotAnz.setText("" + opponentShots);
    }
}
