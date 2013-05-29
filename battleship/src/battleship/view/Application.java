package battleship.view;

/**
 * Application Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Application {

	private Login login;
	private Register register;
	private Menu menu;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application app = new Application();
		//createFile Methode aufrufen (vielleicht in Gameplay)
		app.init();
		app.start();
	}
	
	private void init() {
		login = new Login(this);
		register = new Register(this);
		menu = new Menu(this);
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
	
}
