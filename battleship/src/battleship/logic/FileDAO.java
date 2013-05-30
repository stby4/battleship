package battleship.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import battleship.data.User;

/**
 * FileDAO Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class FileDAO {
	
	/*
	 * create File
	 */
	public void createFile() {
		try {
			FileWriter fw = new FileWriter("battleshipUser.txt", true);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * read User from text file
	 */
	public User readUser(String username) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("battleshipUser.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (username.equals(parts[0])) {
					User user = new User();
					user.setUsername(parts[0]);
					user.setPassword(parts[1]);
					return user;
				} 
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/*
	 * write User in text file
	 */
	public void registPlayer(String username, String password) {
		try {
			FileWriter fw = new FileWriter("battleshipUser.txt", true);
			BufferedWriter ausgabe = new BufferedWriter(fw);
			ausgabe.write(username);
			ausgabe.write(";");
			ausgabe.write(password);
			ausgabe.newLine();
			ausgabe.flush();
			ausgabe.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	/*
	 * check User from text file
	 */
	public boolean checkExistUsername(String username) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("battleshipUser.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (username.equals(parts[0])) {
					return true;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return false;
	}
	
}
