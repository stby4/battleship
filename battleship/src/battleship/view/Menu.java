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
 * Menu Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;
		
	JButton game = new JButton("Game");
	JButton loadGame = new JButton("Load Game");
	JButton highscore = new JButton("Highscore");
	JButton beenden	= new JButton("Beenden");
	JPanel menuPanel = new JPanel();
		
	public Menu(Application app) {
		this.app = app;
		
		setTitle("Menu Battleship");
		//Centering of the window
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int top = (screenSize.height - 400) / 2;
	    int left = (screenSize.width - 800) / 2;
	    setSize(800, 400);
	    setLocation(left, top);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.lightGray);
		setResizable(false);
		
//		game.setBounds(50, 15, 100, 20);
//		loadGame.setBounds(50, 15, 100, 20);
//		highscore.setBounds(50, 15, 100, 20);
//		beenden.setBounds(50, 15, 100, 20);
		
		//Aufruf für Bild in PicturePanel
		
		menuPanel.add(game);
		menuPanel.add(loadGame);
		menuPanel.add(highscore);
		menuPanel.add(beenden);
		
		getContentPane().add(menuPanel);
		
		actionmenu();
	}
	
	public void actionmenu() {
		game.addActionListener(this);
		loadGame.addActionListener(this);
		highscore.addActionListener(this);
		beenden.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == game) {
			//app.game Game start
		} else if (e.getSource() == loadGame) {
			//app.loadgame if game exist (read from text file)
		} else if (e.getSource() == highscore) {
			//app.highscore show highscore all users + Difference between vicoties and defeats (read from other text file)
		} else if (e.getSource() == beenden) {
			System.exit(0);
		}
	}
	
}
