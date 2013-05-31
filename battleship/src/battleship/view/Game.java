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
		setBackground(Color.lightGray);
		setResizable(false);
		
		gamePanel.setLayout(null); 	
		
		next.setBounds(50, 15, 100, 20);
		logout.setBounds(50, 15, 100, 20);
		
		gamePanel.add(next);
		gamePanel.add(logout);
		
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
			//nächstes Schiff setzen
		} else if (e.getSource() == logout) {
			app.login();
		}
	}

}
