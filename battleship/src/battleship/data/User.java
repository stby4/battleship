package battleship.data;

import javax.swing.JOptionPane;

/**
 * User Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class User {
	
	private String username;
	private String password;
	
	/*
	 * call in Login
	 * check if password equals password in file
	 */
	public boolean checkPassword(String password) {
		if (password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	/*
	 * call in Register
	 * check if username and password entered
	 */
	public boolean checkUsernamePassword(String username, String password, String password2) {
		if(username.equals("") && password.equals("") && password2.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Benutzername und ein Passwort ein");
			return false;
		} else if (username.equals("") && !password.equals("") && !password2.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Bentzername ein");
			return false;
		} else if (username.equals("") && !password.equals("") && password2.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Benutzername ein und das Passwort nochmals");
			return false;
		} else if (username.equals("") && password.equals("") && !password2.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Benutzername ein und das Passwort nochmals");
			return false;
		} else if (!username.equals("") && password.equals("") && password2.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie ein Passwort ein");
			return false;
		}
		return true; 
	}
	
	/*
	 * call in Register
	 * check if password entered 2 times
	 */
	public boolean checkPassword(String username, String password, String password2) {
		if (!username.equals("") && !password.equals("") && password2.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie das Passwort nochmals ein");
			return false;
		} else if (!username.equals("") && password.equals("") && !password2.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie das Passwort nochmals ein");
			return false;
		} 
		return true;
	}
	
	/*
	 * call in Register
	 * check if password fields match
	 */
	public boolean comparePassword(String password, String password2) {
		if (!password.equals("") && !password.equals("") && !password2.equals("") && !password.equals(password2)) {
			JOptionPane.showMessageDialog(null, "Passwort stimmt nicht überein");
			return false;
		}	
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
