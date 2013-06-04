package battleship.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * Highscore Battleship
 * @author Tom Ohme
 * 
 */
public class Highscore extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Application app;

	JTable table = new JTable();
	JLabel headline = new JLabel("Hightscore Liste");
	String columnNames[] = {"Username", "Victories", "Defeats", "Difference"};
	JLabel[][] data;
	JPanel dataPanel = new JPanel();
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
		
		highscorePanel.setLayout(new BorderLayout()); 	

//		//�bergeben aus User(Data) sortUser() Array oder Objekt muss noch Logik dazwischen
//		data = new JLabel[user.size(), 3];
//		dataPanel.setLayout(new GridLayout(user.size(),3));
//		
//		fillDataPanel();
//		
//		add(headline, BorderLayout.NORTH);
//		add(table, BorderLayout.CENTER);
//		pack(); //Fensterg�sse anpassen
		
		
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
	
//	private void fillDataPanel() {
//        int countRows = user.size();
//        String rowData[] = new String[3];
//        for (int i=0; i<countRows; i++) {
//        	rowData = user.getRowData(i);
//        } 
//        table = new JTable(rowData, columnNames)
//        table.setBackground(Color.BLACK);
//        table.setForeground(Color.WHITE);
//	}
	
}
