package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Document;

import battleship.objects.User;

/**
 * Highscore Battleship
 * @author Tom Ohme
 * 
 */
public class Highscore extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;
	
	JLabel showUsername = new JLabel("Username");
	JLabel showGames = new JLabel("Games");
	JLabel showVictories = new JLabel("Victories");
	JLabel showDefeats = new JLabel("Defeats");
	JLabel showDifference = new JLabel("Difference");
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
		
		showUsername.setFont(new Font("Arial", Font.BOLD, 14));
		showGames.setFont(new Font("Arial", Font.BOLD, 14));
		showVictories.setFont(new Font("Arial", Font.BOLD, 14));
		showDefeats.setFont(new Font("Arial", Font.BOLD, 14));
		showDifference.setFont(new Font("Arial", Font.BOLD, 14));
		
		highscorePanel.setLayout(null);
		
		showUsername.setBounds(50, 30, 110, 25);
		showGames.setBounds(160, 30, 110, 25);
		showVictories.setBounds(255, 30, 110, 25);
		showDefeats.setBounds(365, 30, 110, 25);
		showDifference.setBounds(475, 30, 110, 25);
		back.setBounds(50, 325, 110, 25);
		print.setBounds(440, 325, 110, 25);
		
		showUsername.setForeground(Color.ORANGE);
		showGames.setForeground(Color.ORANGE);
		showVictories.setForeground(Color.ORANGE);
		showDefeats.setForeground(Color.ORANGE);
		showDifference.setForeground(Color.ORANGE);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setFocusable(false);
		print.setBackground(Color.BLACK);
		print.setForeground(Color.WHITE);
		print.setFocusable(false);
		
		highscorePanel.add(showUsername);
		highscorePanel.add(showGames);
		highscorePanel.add(showVictories);
		highscorePanel.add(showDefeats);
		highscorePanel.add(showDifference);
		highscorePanel.add(back);
		highscorePanel.add(print);
		
		showUser();
		
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
		List<User> userList = new battleship.data.User().readAll();
		for (int i=0; i<userList.size()-1; i++) {
			compare(userList.get(i), userList.get(i+1));
		}
		int bestUser = 0;
		int y = 60;
		for (User user : userList) {
			if (user.getGamesNr() != 0) {
				if (bestUser < 8) {
					JLabel username = new JLabel(user.getName());
					username.setBounds(50, y, 110, 25);
					username.setForeground(Color.WHITE);
					highscorePanel.add(username);
					String strGames = String.valueOf(user.getGamesNr());
					JLabel games = new JLabel(strGames);
					games.setBounds(160, y, 110, 25);
					games.setForeground(Color.WHITE);
					highscorePanel.add(games);
					String strVictories = String.valueOf(user.getVictories());
					JLabel victories = new JLabel(strVictories);
					victories.setBounds(255, y, 110, 25);
					victories.setForeground(Color.WHITE);
					highscorePanel.add(victories);
					String strDefeats = String.valueOf(user.getDefeats());
					JLabel defeats = new JLabel(strDefeats);
					defeats.setBounds(365, y, 110, 25);
					defeats.setForeground(Color.WHITE);
					highscorePanel.add(defeats);
					String strDifference = String.valueOf(user.getDifference());
					JLabel difference = new JLabel(strDifference);
					difference.setBounds(475, y, 110, 25);
					difference.setForeground(Color.WHITE);
					highscorePanel.add(difference);
					y=y+30;
				} else {
					JLabel empty = new JLabel("keine Einträge vorhanden");
					empty.setFont(new Font("Arial", Font.BOLD, 16));
					empty.setBounds(200, 175, 200, 25);
					empty.setForeground(Color.WHITE);
					highscorePanel.add(empty);
				}
				bestUser++;
			}
		}
		
	}

	public int compare(User inUserLeft, User inUserRight) {
		int difference = inUserLeft.getDifference() - inUserRight.getDifference();
		if (difference == 0) {
			if (inUserLeft.getDifference() > inUserRight.getDifference()) {
				return 1;
			} else if (inUserLeft.getDifference() == inUserRight.getDifference()) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return difference;
		}
	}
	
}
