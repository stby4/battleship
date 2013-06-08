package battleship.view;

import battleship.data.User;
import battleship.data.binaryFile;

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
	private Highscore highscore;
	private binaryFile file;
	
	
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
		menu = new Menu(this);
		position = new Position(this);
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
		if (highscore != null) {
			highscore.setVisible(false);
		}
		if (position != null) {
			position.setVisible(false);
		}
		register.setVisible(false);
		menu.setVisible(true);
	}
	
	public void game() {
		menu.setVisible(false);
		position.setVisible(true);
	}
	
	public void loadGame() {
		//kommt noch �berspringen des Schiffe setzen
	}
	
	public void highscore() {
		menu.setVisible(false);
		highscore.setVisible(true);
	}
}
