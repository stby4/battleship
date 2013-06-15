package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Highscore Battleship
 * @author Tom Ohme
 * 
 */
public class Highscore extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;
	private battleship.objects.User user;
	
	JButton back = new JButton("BACK");
	JButton print = new JButton("PRINT"); 
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
		
		back.setBounds(50, 325, 110, 25);
		print.setBounds(440, 325, 110, 25);
		
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setFocusable(false);
		print.setBackground(Color.BLACK);
		print.setForeground(Color.WHITE);
		print.setFocusable(false);
		
		highscorePanel.add(back);
		highscorePanel.add(print);
		
		highscorePanel.setBackground(Color.BLACK);
		
		getContentPane().add(highscorePanel);
		
		actionHighscore();
	}
	
	public void actionHighscore() {
		back.addActionListener(this);
		print.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			app.menu();
		} else if (e.getSource() == print) {
			
		}
	}
	
	public void showUser() {
		ArrayList<battleship.objects.User> showUser = 
	}
	
}
