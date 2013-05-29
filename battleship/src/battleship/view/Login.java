package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Login Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;

	JLabel bLabel = new JLabel("Username: ");
	JLabel pLabel = new JLabel("Password: ");
	JTextField bField = new JTextField();
	JPasswordField pField = new JPasswordField();
	JButton anmelden = new JButton("Anmelden");
	JButton registrieren = new JButton("Registrieren");
	JButton beenden = new JButton("Beenden");
	JPanel loginPanel = new JPanel();
	
	Login(Application app) {
		this.app = app;
		
		setTitle("Login Autentification Battleship");
		//Zentrierung des Fenster
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int top = (screenSize.height - 200) / 2;
	    int left = (screenSize.width - 400) / 2;
	    setSize(400, 200);
	    setLocation(left, top);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.lightGray);
		setResizable(false);
		
		loginPanel.setLayout(null); 	
		
		bLabel.setBounds(50, 30, 100, 20);
		bField.setBounds(150, 30, 180, 20);
		pLabel.setBounds(50, 60, 100, 20);
		pField.setBounds(150, 60, 180, 20);
		anmelden.setBounds(200, 110, 128, 20);
		registrieren.setBounds(50, 110, 128, 20);
		beenden.setBounds(200, 140, 128, 20);
		
		loginPanel.add(bLabel);
		loginPanel.add(bField);
		loginPanel.add(pLabel);
		loginPanel.add(pField);
		loginPanel.add(anmelden);
		loginPanel.add(registrieren);
		loginPanel.add(beenden);
		
		getContentPane().add(loginPanel);
		
		actionlogin();
	}
	
	public void actionlogin() {
		anmelden.addActionListener(this);
		registrieren.addActionListener(this);
		beenden.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == anmelden) {
			String username = bField.getText();
			char[] charpassword = pField.getPassword();
			String password = new String(charpassword);
			boolean isValide = checkUsernamePassword(username, password);			
			if (isValide == true) {
				JOptionPane.showMessageDialog(null, "Sie wurden erfolgreich bei Battleship angemeldet");
				app.loginDone();
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Benutzername oder Passwort falsch");
			}
		} else if (e.getSource() == registrieren) {
			app.register();
		} else if (e.getSource() == beenden) {
			System.exit(0);
		}
	}
	
	public boolean checkUsernamePassword(String username, String password) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("C:\\downloads\\battleshipUser.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (username.equals(parts[0]) && password.equals(parts[1])) {
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