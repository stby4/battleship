package battleship.view;

import battleship.logic.Instance;

import javax.swing.*;
import java.io.IOException;

/**
 * switch between the GUI classes
 *
 * @author Hinrich Kaestner, Tom Ohme
 */
public class Application {
    private Login login;
    private Register register;
    private Menu menu;
    private Position position;
    private PositionFinished positionFinished;
    private Game game;
    private Highscore highscore;
    private Instance instance;

    /**
     * Start the game
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Application app = new Application();
                app.init();
                app.start();
            }
        });
    }

    /**
     * Initializing the classes Login and Register
     */
    private void init() {
        login = new Login(this);
        register = new Register(this);
    }

    /**
     * call up of the method login
     */
    private void start() {
        login();
    }

    /**
     * close the register and the menu Window and display the login Window
     */
    public void login() {
        if (menu != null) {
            menu.setVisible(false);
        }
        register.setVisible(false);
        login.setVisible(true);
    }

    /**
     * close the login Window
     */
    public void loginDone() {
        login.setVisible(false);
        menu();
    }

    /**
     * close the login Window and display the Register Window
     */
    public void register() {
        login.setVisible(false);
        register.setVisible(true);
    }

    /*
    public void registerDone() {
		login();
	}
	*/

    /**
     * close the highscore, position, positionFinished and game Window and display the Menu Window
     */
    public void menu() {

        positionFinished = new PositionFinished(this);
        if (highscore != null) {
            highscore.setVisible(false);
        }
        if (position != null) {
            position.setVisible(false);
        }
        if (positionFinished != null) {
            positionFinished.setVisible(false);
        }
        if (game != null) {
            game.setVisible(false);
        }
        menu = new Menu(this);
        highscore = new Highscore(this);
        register.setVisible(false);
        menu.setVisible(true);
    }

    /**
     * make a instance of a User, close the menu Window and display the position Window
     */
    public void position() {
        battleship.logic.User lUser = battleship.logic.User.getInstance();
        instance = Instance.getInstance();
        instance.newGame(lUser.getUser());
        position = new Position(this);
        menu.setVisible(false);
        position.setVisible(true);
    }

    /**
     * close the position Window and display the positionFinished Window
     */
    public void positionFinished() {
        position.setVisible(false);
        positionFinished.setVisible(true);
    }

    /**
     * close the game and positionFinished Window and display the game Window
     */
    public void game() {
        game = new Game(this, false);
        positionFinished.setVisible(false);
        game.setVisible(true);
    }

    /**
     * after click the exit Button the game is saved or Error Message
     * close the game Window and display the menu Window
     */
    public void gameExit() {
        try {
            instance = Instance.getInstance();
            instance.storeGame();
        } catch(IOException ioe) {
            JPanel panel = new JPanel();
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(panel, "The Game can't be saved", "Error Message", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
        game.setVisible(false);
        menu.setVisible(true);
    }

    /**
     * close the menu Window and display the stated game Window
     */
    public void loadGame() {
        game = new Game(this, true);
        menu.setVisible(false);
        game.setVisible(true);
    }

    /**
     * close the menu Window and display the highscore Window
     */
    public void highscore() {
        menu.setVisible(false);
        highscore.setVisible(true);
    }
}
