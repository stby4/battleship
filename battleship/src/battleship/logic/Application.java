package battleship.logic;

import java.io.FileWriter;
import java.io.IOException;

public class Application {

	private Login login;
	private Register register;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application app = new Application();
		app.createFile();
		app.init();
		app.start();
	}
	
	private void init() {
		login = new Login(this);
		register = new Register(this);
	}
	
	private void start() {
		login();
	}
	
	public void login() {
		register.setVisible(false);
		login.setVisible(true);
	}
	
	public void loginDone() {
		System.out.println("Jetzt kommt das Game");
	}
	
	public void register() {
		login.setVisible(false);
		register.setVisible(true);
	}
	
	public void registerDone() {
		login();
	}
	
	public void createFile() {
		try {
			FileWriter fw = new FileWriter("C:\\downloads\\battleshipUser.txt", true); // TODO this belongs in data abstraction!
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
