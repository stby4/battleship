package battleship.view;

import battleship.data.User;
import battleship.logic.FileDAO;

/**
 * Application Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Application {

	private User user;
	private Login login;
	private Register register;
	private Menu menu;
	private Game game;
//	private Highscore highscore;
	private FileDAO file;
	
	
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
		file = new FileDAO();
		file.createFile();
	}
	
	private void start() {
		login();
	}
	
	public void login() {
		if (game != null) {
			game.setVisible(false);
		}
		if (menu != null) {
			menu.setVisible(false);
		}
		register.setVisible(false);
		login.setVisible(true);
	}
	
	public void loginDone(User user) {
		//System.out.println("Jetzt kommt das Game");
		this.user = user;
		menu = new Menu(this);
		game = new Game(this);
		menu();
	}
	
	public void register() {
		login.setVisible(false);
		register.setVisible(true);
	}
	
	public void registerDone() {
		login();
	}
	
	public void menu() {
		login.setVisible(false);
		menu.setVisible(true);
	}
	
	public void game() {
		menu.setVisible(false);
		game.setVisible(true);
	}
	
	public void loadGame() {
		//kommt noch überspringen des Schiffe setzen
	}
	
//	public void highscore() {
//		menu.setVisible(false);
//		highscore.setVisible(true);
//	}
	
	public FileDAO getFile() {
		return file;
	}
	
	public User getUser() {
		return user;
	}

}
