package battleship.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Game Battleship
 * @author Tom Ohme
 * 
 */
public class Game extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Application app;
	private Field myOcean;
	
	JLabel label = new JLabel("Set Ship:");
	JLabel label2 = new JLabel();
	JLabel label3 = new JLabel();
	JRadioButton verticalButton = new JRadioButton("vertical", true);
	JRadioButton horizontalButton = new JRadioButton("horizontal", false);
	JButton next = new JButton("Next");
	JButton back = new JButton("Back");
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
		
		label.setFont(new Font("Arial", Font.BOLD, 16));
		
		gamePanel.setLayout(null);
		
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(verticalButton);
		bgroup.add(horizontalButton);
		
		label.setBounds(350, 48, 110, 25);
		label2.setBounds(400, 73, 110, 25);
		label3.setBounds(400, 93, 110, 25);
		verticalButton.setBounds(400, 113, 110, 25);
		horizontalButton.setBounds(400, 133, 110, 25);
		back.setBounds(50, 325, 110, 25);
		
		label.setForeground(Color.WHITE);
		label2.setForeground(Color.WHITE);
		label3.setForeground(Color.WHITE);
		verticalButton.setForeground(Color.WHITE);
		verticalButton.setFocusable(false);
		horizontalButton.setForeground(Color.WHITE);
		horizontalButton.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setFocusable(false);
		
		//create Field
		myOcean = new Field();
		myOcean.setBounds(30, 28, 271, 271); //myOcean.setBounds(50, 48, 250, 250);
		gamePanel.add(myOcean);
		
		
		//if wenn noch kein Schiff gesetzt
			label2.setText("Flugzeugtr�ger");
			label3.setText("(5 Felder)");
			//create ac_carrier
			JPanel picture = new PicturePanel2();
			picture.setBounds(350, 180, 200, 120);
			gamePanel.add(picture);
//		//else if wenn Flugzeugtr�ger gesetzt 
//			label2.setText("Battleship");
//			label3.setText("(4 Felder)");
//			JPanel picture = new PicturePanel3();
//			picture.setBounds(350, 180, 200, 120);
//			gamePanel.add(picture);
//		//else if wenn Battleship und Flugzeugtr�ger gesetzt
//			label2.setText("Destroyer");
//			label3.setText("(3 Felder)");
//			JPanel picture = new PicturePanel4();
//			picture.setBounds(350, 180, 200, 120);
//			gamePanel.add(picture);
//		//else if wenn Destroyer... gesetzt
//			label2.setText("Submarine");
//			label3.setText("(3 Felder)");
//			JPanel picture = new PicturePanel5();
//			picture.setBounds(350, 180, 200, 120);
//			gamePanel.add(picture);
//		//else if wenn Submarine... gesetzt 
//			label2.setText("Frigate");
//			label3.setText("(2 Felder)");
//			JPanel picture = new PicturePanel6();
//			picture.setBounds(350, 180, 200, 120);
//			gamePanel.add(picture);
//		//else if wenn alle gesetzt sind
//			//vlt Bild
//			//next Button
//			next.setBounds(440, 325, 110, 25);
//			next.setBackground(Color.BLACK);
//			next.setForeground(Color.WHITE);
//			next.setFocusable(false);
//			gamePanel.add(next);
//			gamePanel.setComponentZOrder(next, 0);
			
		gamePanel.add(label);
		gamePanel.add(label2);
		gamePanel.add(label3);
		gamePanel.add(verticalButton);
		gamePanel.add(horizontalButton);
		gamePanel.add(back);
				
		gamePanel.setComponentZOrder(picture, 1);
		gamePanel.setComponentZOrder(label, 0);
		gamePanel.setComponentZOrder(label2, 0);
		gamePanel.setComponentZOrder(label3, 0);
		gamePanel.setComponentZOrder(verticalButton, 0);
		gamePanel.setComponentZOrder(horizontalButton, 0);
		gamePanel.setComponentZOrder(back, 0);
		gamePanel.setComponentZOrder(myOcean, 0);
		
		verticalButton.setBackground(Color.BLACK);
		horizontalButton.setBackground(Color.BLACK);
		gamePanel.setBackground(Color.BLACK);
		
		getContentPane().add(gamePanel);
		
		actiongame();
	}
	
	public void actiongame() {
		next.addActionListener(this);
		back.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			
		} else if (e.getSource() == back) {
			app.menu();
		}
	}

}
