package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Menu Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;
	
	JLabel label = new JLabel();
	JButton game = new JButton("New Game");
	JButton loadGame = new JButton("Load Game");
	JButton highscore = new JButton("Highscore");
	JButton logout	= new JButton("Logout");
	JPanel menuPanel = new JPanel();
		
	public Menu(Application app) {
		this.app = app;
		
		setTitle("Menu Battleship");
		//Centering of the window
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int top = (screenSize.height - 400) / 2;
	    int left = (screenSize.width - 600) / 2;
	    setSize(600, 400);
	    setLocation(left, top);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //setBackground(Color.BLACK);
		setResizable(false);
		
		menuPanel.setLayout(null); 
		
		label.setText("Angemeldet als: " + app.getUser().getUsername());
		label.setForeground(Color.WHITE);
		
		label.setBounds(50, 10, 300, 25);
		game.setBounds(50, 325, 110, 25);
		loadGame.setBounds(180, 325, 110, 25);
		highscore.setBounds(310, 325, 110, 25);
		logout.setBounds(440, 325, 110, 25);
		
		game.setBackground(Color.BLACK);
		game.setForeground(Color.WHITE);
		game.setFocusable(false);
		loadGame.setBackground(Color.BLACK);
		loadGame.setForeground(Color.WHITE);
		loadGame.setFocusable(false);
		highscore.setBackground(Color.BLACK);
		highscore.setForeground(Color.WHITE);
		highscore.setFocusable(false);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setFocusable(false);
		
		JPanel picture = new PicturePanel();
		picture.setBounds(0, 20, 600, 337);
		menuPanel.add(picture);
		menuPanel.add(label);
		menuPanel.add(game);
		menuPanel.add(loadGame);
		menuPanel.add(highscore);
		menuPanel.add(logout);
		
		menuPanel.setComponentZOrder(picture, 1);
		menuPanel.setComponentZOrder(label, 0);
		menuPanel.setComponentZOrder(game, 0);
		menuPanel.setComponentZOrder(loadGame, 0);
		menuPanel.setComponentZOrder(highscore, 0);
		menuPanel.setComponentZOrder(logout, 0);
	
		menuPanel.setBackground(Color.BLACK);
	
		getContentPane().add(menuPanel);
		
		actionmenu();
	}
	
	public void actionmenu() {
		game.addActionListener(this);
		loadGame.addActionListener(this);
		highscore.addActionListener(this);
		logout.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == game) {
			app.game();
		} else if (e.getSource() == loadGame) {
			//app.loadgame if game exist (read from text file) überspringen des Schiffe setzen
		} else if (e.getSource() == highscore) {
			app.highscore(); //show highscore all users + Difference between vicoties and defeats (read from other text file)
		} else if (e.getSource() == logout) {
			app.login();
		}
	}
	
}
