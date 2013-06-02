package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Game Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Game extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Application app;
	private Field myOcean;
	
	JButton next = new JButton("Next");
	JButton logout = new JButton("Logout");
	JPanel gamePanel = new JPanel();
	
	public Game(Application app) {
		this.app = app;
		
		setTitle("Game Battleship");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - 400) / 2;
		int left = (screenSize.width - 600) / 2;
		setSize(600, 400);
		setLocation(left, top);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBackground(Color.lightGray);
		setResizable(false);
		
		gamePanel.setLayout(null); 	
		
		next.setBounds(440, 325, 110, 25);
		logout.setBounds(50, 325, 110, 25);
		
		next.setBackground(Color.BLACK);
		next.setForeground(Color.WHITE);
		next.setFocusable(false);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setFocusable(false);
		
		//create Field
		myOcean = new Field();
		myOcean.setBounds(50, 48, 251, 251); //myOcean.setBounds(50, 48, 250, 250);
		gamePanel.add(myOcean);
		
		//create ac_carrier
		JPanel picture = new PicturePanel2();
		picture.setBounds(350, 180, 200, 120);
		gamePanel.add(picture);
		
		gamePanel.add(next);
		gamePanel.add(logout);
				
		gamePanel.setComponentZOrder(picture, 1);
		gamePanel.setComponentZOrder(next, 0);
		gamePanel.setComponentZOrder(logout, 0);
		gamePanel.setComponentZOrder(myOcean, 0);
		
		gamePanel.setBackground(Color.BLACK);
		
		getContentPane().add(gamePanel);
		
		actiongame();
	}
	
	public void actiongame() {
		next.addActionListener(this);
		logout.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			//n�chstes Schiff setzen
		} else if (e.getSource() == logout) {
			app.login();
		}
	}

}
