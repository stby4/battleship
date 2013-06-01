package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * Highscore Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Highscore extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;

	JButton back = new JButton("Back");
	JButton print = new JButton("Print"); //optional um Highscore zu drucken
	JPanel highscorePanel = new JPanel();

	public Highscore(Application app) {
		this.app = app;
		
		setTitle("Highscore Battleship");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int top = (screenSize.height - 400) / 2;
		int left = (screenSize.width - 600) / 2;
		setSize(600, 400);
		setLocation(left, top);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		highscorePanel.setLayout(null); 	

		JList themenAuswahl = new JList();
//		setForeground(Color.WHITE);
		//setBounds(50, 10, 300, 25);
		
		back.setBounds(50, 325, 110, 25);
		
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setFocusable(false);
		
		highscorePanel.add(back);
		
		highscorePanel.setBackground(Color.BLACK);
		
		getContentPane().add(highscorePanel);
		
		actionhighscore();
	}
	
	public void actionhighscore() {
		back.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			app.menu();
		}
	}

}
