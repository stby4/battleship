package battleship.objects;

public class User {
	private String name = ""; // name must be unique, hence it can be used as primary key
	private String password = "";
	private int victories = 0;
	private int defeats = 0;
	
	public User(String name, String password) {
		this.setName(name);
		this.setPassword(password);
	}
	
	public boolean setName(String name) {
		this.name = name;
		/*
		 * TODO check if name is valid
		 */
		return true;
	}
	
	/*
	 * @param String password
	 * @return boolean password successful set
	 */
	public boolean setPassword(String password) {
		this.password = password;
		/*
		 * TODO check if password is valid
		 */
		return true;
	}
	
	/*
	 * writes user data in file
	 * @return boolean successful registration
	 */
	public boolean registrate() {
		// TODO check if user is not existant, write name and pw in file
		return true;
	}
	
	/*
	 * login user
	 * @return boolean successful login
	 */
	public boolean login() {
		// TODO check if user is existant and if password is correct
		// TODO get highscore and possibly the users latest game
		return true;
	}
	
	

}
