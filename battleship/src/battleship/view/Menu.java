package battleship.view;

import battleship.logic.Instance;
import battleship.logic.UserManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * menu Panel
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Menu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Application app;

    JLabel label = new JLabel();
    JButton game = new JButton("NEW GAME");
    JButton loadGame = new JButton("LOAD GAME");
    JButton highscore = new JButton("HIGHSCORE");
    JButton logout = new JButton("LOGOUT");
    JPanel menuPanel = new JPanel();

    /**
     * menu panel with 4 Buttons
     *
     * @param app
     */
    public Menu(Application app) {
        this.app = app;

        setTitle("Menu Battleship");
        //Centering of the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top = (screenSize.height - 400) / 2;
        int left = (screenSize.width - 600) / 2;
        setSize(600, 400);
        setLocation(left, top);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(Color.BLACK);
        setResizable(false);

        menuPanel.setLayout(null);

        //Show the username in Menu from the actual Login player
        //label.setText("Angemeldet als: " + app.getUser().getUsername());
        label.setForeground(Color.WHITE);

        label.setBounds(50, 10, 300, 25);
        game.setBounds(50, 325, 110, 25);
        loadGame.setBounds(180, 325, 110, 25);
        highscore.setBounds(310, 325, 110, 25);
        logout.setBounds(440, 325, 110, 25);

        game.setBackground(Color.BLACK);
        game.setForeground(Color.WHITE);
        game.setFocusable(false);
        loadGame.setBackground(Color.BLACK);
        loadGame.setForeground(Color.WHITE);
        loadGame.setFocusable(false);
        highscore.setBackground(Color.BLACK);
        highscore.setForeground(Color.WHITE);
        highscore.setFocusable(false);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.setFocusable(false);

        JPanel picture = new PicturePanel("wallpaper.jpg", 600, 337);
        picture.setBounds(0, 20, 600, 337);
        menuPanel.add(picture);
        menuPanel.add(label);
        menuPanel.add(game);
        menuPanel.add(loadGame);
        menuPanel.add(highscore);
        menuPanel.add(logout);

        menuPanel.setComponentZOrder(picture, 1);
        menuPanel.setComponentZOrder(label, 0);
        menuPanel.setComponentZOrder(game, 0);
        menuPanel.setComponentZOrder(loadGame, 0);
        menuPanel.setComponentZOrder(highscore, 0);
        menuPanel.setComponentZOrder(logout, 0);

        menuPanel.setBackground(Color.BLACK);

        getContentPane().add(menuPanel);

        actionMenu();
    }

    /**
     * add ActionListener on game, loadGame, highscore and logout
     */
    public void actionMenu() {
        game.addActionListener(this);
        loadGame.addActionListener(this);
        highscore.addActionListener(this);
        logout.addActionListener(this);
    }

    /**
     * game Button for start a new game
     * loadGame Button for load a beginning game
     * highscore Button show the 7 best users
     * logout Button the user is logged out
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == game) {
            app.position();
        } else if (e.getSource() == loadGame) {
            Instance instance = Instance.getInstance();
            String lastGame = UserManagement.getInstance().getUser().getLastGame();
            if (null != lastGame) {
                instance.loadGame(lastGame);
                app.loadGame();
            }
        } else if (e.getSource() == highscore) {
            app.highscore();
        } else if (e.getSource() == logout) {
            app.login();
        }
    }

}
