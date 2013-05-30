package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import battleship.data.User;

/**
 * Registrierung Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Register extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;
	private User user;
	
	JLabel bLabel = new JLabel("Username: ");
	JLabel pLabel = new JLabel("Password: ");
	JLabel pLabel2 = new JLabel("Password: ");
	JTextField bField = new JTextField();
	JPasswordField pField = new JPasswordField();
	JPasswordField pField2 = new JPasswordField();
	JButton weiter = new JButton("Weiter");
	JButton zurueck = new JButton("Zurück");
	JButton beenden = new JButton("Beenden");
	JPanel registerPanel = new JPanel();
	
	public Register(Application app) {
		this.app = app;
		
		setTitle("Registrierung Battleship");
		//Centering of the window
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
			String username = bField.getText();
			char[] charpassword = pField.getPassword();
			char[] charpassword2 = pField2.getPassword();
			String password = new String(charpassword);
			String password2 = new String(charpassword2);
			boolean isValide = user.checkUsernamePassword(username, password, password2); //geht noch nicht
			if (!isValide) {
				return;
			}
			isValide = user.checkPassword(username, password, password2); //geht noch nicht
			if (!isValide) {
				return;
			}
			isValide = user.comparePassword(password, password2); //geht noch nicht
			if (!isValide) {
				return;
			}
			boolean exist = app.getFile().checkExistUsername(username); 
			if (!exist) {
				app.getFile().registPlayer(username, password);
				JOptionPane.showMessageDialog(null, "Danke für die Registrierung bei Battleship");
				emptyFields();
				app.registerDone();
			} else {
				JOptionPane.showMessageDialog(null, "Benutzername schon vorhanden");
			}
		} else if (e.getSource() == zurueck) {
			emptyFields();
			app.login();
		} else if (e.getSource() == beenden) {
			System.exit(0);
		}		
	}


	//text fields empty
	public void emptyFields() {
		bField.setText("");
		pField.setText("");
		pField2.setText("");
	}
	
}
