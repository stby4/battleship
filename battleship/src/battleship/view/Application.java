package battleship.view;

import battleship.logic.FileDAO;

/**
 * Application Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Application {

	private Login login;
	private Register register;
	private Menu menu;
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
		menu = new Menu(this);
		file = new FileDAO();
		file.createFile();
	}
	
	private void start() {
		login();
	}
	
	public void login() {
		register.setVisible(false);
		login.setVisible(true);
	}
	
	public void loginDone() {
		//System.out.println("Jetzt kommt das Game");
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
	
	public FileDAO getFile() {
		return file;
	}

}
