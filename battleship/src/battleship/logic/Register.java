package battleship.logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Registrierung Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Register extends JFrame implements ActionListener {
    // Tom changed somtehing

	private static final long serialVersionUID = 1L;
	
	JLabel bLabel = new JLabel("Username: ");
	JLabel pLabel = new JLabel("Password: ");
	JLabel pLabel2 = new JLabel("Password: ");
	JTextField bField = new JTextField();
	JPasswordField pField = new JPasswordField();
	JPasswordField pField2 = new JPasswordField();
	JButton weiter = new JButton("Weiter");
	JButton zurueck = new JButton("Zur�ck");
	JButton beenden = new JButton("Beenden");
	JPanel registerPanel = new JPanel();
	
	Register() {
		setTitle("Registrierung Battleship");
		//Zentrierung des Fenster
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int top = (screenSize.height - 200) / 2;
	    int left = (screenSize.width - 400) / 2;
	    setSize(400, 200);
	    setLocation(left, top);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.lightGray);
		setResizable(false);
		
		registerPanel.setLayout(null); 	
		
		bLabel.setBounds(50, 15, 100, 20);
		bField.setBounds(150, 15, 180, 20);
		pLabel.setBounds(50, 45, 100, 20);
		pField.setBounds(150, 45, 180, 20);
		pLabel2.setBounds(50, 75, 100, 20);
		pField2.setBounds(150, 75, 180, 20);
		weiter.setBounds(200, 110, 128, 20);
		zurueck.setBounds(50, 110, 128, 20);
		beenden.setBounds(200, 140, 128, 20);
		
		registerPanel.add(bLabel);
		registerPanel.add(bField);
		registerPanel.add(pLabel);
		registerPanel.add(pField);
		registerPanel.add(pLabel2);
		registerPanel.add(pField2);
		registerPanel.add(weiter);
		registerPanel.add(zurueck);
		registerPanel.add(beenden);
		
		getContentPane().add(registerPanel);
		
		setVisible(true);
		
		actionregister();
	}
	
	public void actionregister() {
		weiter.addActionListener(this);
		zurueck.addActionListener(this);
		beenden.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == weiter) {
			boolean usernameIsEmpty = bField.getText().isEmpty();
			boolean passwordIsEmpty = pField.getPassword().isEmpty();
			boolean passwordIsEmpty2 = pField2.getPassword().isEmpty();
			String username = bField.getText();
			char[] password = pField.getPassword();
			char[] password2 = pField.getPassword();
			//Schauen ob Bentzername vorhanden
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(new File("c:/Test/Test.txt")));
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(";");
					if (bLabel.getText() == parts[0]) {
						JOptionPane.showMessageDialog(null, "Benutzername schon vorhanden");
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
			//Logindaten in Textdatei schreiben
			try {
				FileWriter fw = new FileWriter("c:/Test/Test.txt", true);
				BufferedWriter ausgabe = new BufferedWriter(fw);
				ausgabe.write(username);
				ausgabe.write(";");
				ausgabe.write(password);
				ausgabe.newLine();
				ausgabe.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			//�berpr�fung ob Passwortfelder �bereinstimmen
			if (!password.equals(password2)) {
				JOptionPane.showMessageDialog(null, "Passwort stimmt nicht �berein");
			}
			//�berpr�fung ob Benutzername und Passwort eingegeben
			if (usernameIsEmpty || passwordIsEmpty || passwordIsEmpty2) {
				JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Benutzername und ein Passwort ein");
			}
			dispose();
		} else if (e.getSource() == zurueck) {
			Login login = new Login();
			dispose();
		} else if (e.getSource() == beenden) {
			System.exit(0);
		}
	}
	
	public void checkUsername() {
		
	}
	
	

}
