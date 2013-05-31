package battleship.data;

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
 * TODO switch from text to binary files
 * TODO allow operations for battleshipGame.bin (?)
 * some work left, Tom....
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
				if (username.equals(parts[1])) {
					User user = new User();
					user.setUsername(parts[1]);
					user.setPassword(parts[2]);
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
			ausgabe.write(countUser());
			ausgabe.write(";");
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
				if (username.equals(parts[1])) {
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
	
	/*
	 * count user in text file for id
	 */
	public int countUser() {
		BufferedReader br = null;
		int users = 0;
		try {
			br = new BufferedReader(new FileReader(new File("battleshipUser.txt")));
			int rows = 0;
			while ((br.readLine()) != null) {
				rows++;
			}
			users = rows + 1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return users;
	}
	
}
