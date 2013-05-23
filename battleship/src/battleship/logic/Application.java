package battleship.logic;

public class Application {

	private Login login;
	private Register register;
	
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

}
