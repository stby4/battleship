package battleship.view;

import battleship.logic.Instance;
import battleship.objects.User;

/**
 * Application Battleship
 * @author Tom Ohme
 *
 */
public class Application {
    private Login login;
    private Register register;
    private Menu menu;
    private Position position;
    private Game game;
    private Highscore highscore;
    private Instance instance;

    /**
     * @param args
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.init();
        app.start();
    }

    private void init() {
        login = new Login(this);
        register = new Register(this);
        //file = new binaryFile();
        //file.createFile(); TODO use appropriate way to save data (see UML)
    }

    private void start() {
        login();
    }

    public void login() {
        if (menu != null) {
            menu.setVisible(false);
        }
        register.setVisible(false);
        login.setVisible(true);
    }

    public void loginDone() {
        highscore = new Highscore(this);
        menu();
    }

    public void register() {
        login.setVisible(false);
        register.setVisible(true);
    }

    /*
	public void registerDone() {
		login();
	}
	*/

    public void menu() {
        game = new Game(this);
        if (highscore != null) {
            highscore.setVisible(false);
        }
        if (position != null) {
            position.setVisible(false);
        }
        if (game != null) {
            game.setVisible(false);
        }
        menu = new Menu(this);
        register.setVisible(false);
        menu.setVisible(true);
    }

    public void position() {
        battleship.logic.User lUser = battleship.logic.User.getInstance();
        instance = Instance.getInstance();
        instance.newGame(lUser.getUser());
        position = new Position(this);
        menu.setVisible(false);
        position.setVisible(true);
    }

    public void game() {
        position.setVisible(false);
        game.setVisible(true);
    }

    public void loadGame() {
        //kommt noch überspringen des Schiffe setzen
    }

    public void highscore() {
        menu.setVisible(false);
        highscore.setVisible(true);
    }
}
