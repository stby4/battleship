package battleship.view;

import battleship.logic.Instance;
import battleship.logic.UserManagement;

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
    private GameView gameView;
    private Highscore highscore;
    private Instance instance;

    /**
     * Start the gameView
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
     * close the highscore, position, positionFinished and gameView Window and display the Menu Window
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
        if (gameView != null) {
            gameView.setVisible(false);
        }
        menu = new Menu(this);
        highscore = new Highscore(this);
        register.setVisible(false);
        menu.setVisible(true);
    }

    /**
     * make a instance of a UserManagement, close the menu Window and display the position Window
     */
    public void position() {
        UserManagement lUserManagement = UserManagement.getInstance();
        instance = Instance.getInstance();
        instance.newGame(lUserManagement.getUser());
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
     * close the gameView and positionFinished Window and display the gameView Window
     */
    public void game() {
        gameView = new GameView(this, false);
        positionFinished.setVisible(false);
        gameView.setVisible(true);
    }

    /**
     * after click the exit Button the gameView is saved or Error Message
     * close the gameView Window and display the menu Window
     */
    public void gameExit() {
        try {
            instance = Instance.getInstance();
            instance.storeGame();
        } catch(IOException ioe) {
            JPanel panel = new JPanel();
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(panel, "The GameFile can't be saved", "Error Message", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
        gameView.setVisible(false);
        menu.setVisible(true);
    }

    /**
     * close the menu Window and display the stated gameView Window
     */
    public void loadGame() {
        gameView = new GameView(this, true);
        menu.setVisible(false);
        gameView.setVisible(true);
    }

    /**
     * close the menu Window and display the highscore Window
     */
    public void highscore() {
        menu.setVisible(false);
        highscore.setVisible(true);
    }
}
