package battleship.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import battleship.objects.Ship;

/**
 * Field Battleship
 * @author Tom Ohme
 * 
 */
public class Field extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	public Field() {
		addMouseListener(new MouseEventDemo());
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);   
		g.fillRect(0,0,271,271);    
		g.setColor(Color.WHITE);   
		//10 X 10 square
		for (int i=0; i<11; i++) {  
	    	g.drawLine(20,20+i*25,271,20+i*25);   
	    }
		for (int i=0; i<11; i++) {
			g.drawLine(20+i*25,20,20+i*25,271);   
	    }
		
	    char ch='0';   
	    for (int i=0; i<10; i++) {                    //Spalten Koordinaten oben   
	        g.drawString(ch+" ",30+25*i,13);   
	        ch++;   
	    }   
	    ch='A';   
	    for (int i=0; i<10; i++) {                    //Spalten Koordinaten links    
	    	g.drawString(ch+" ",10,40+25*i);   
	    	ch++;   
	    }   
	         
	    int sqStat=0;                                     
	    for (int r=0; r<10; r++) {                     
	    	for (int c=0; c<10;c++) {   
	    		if (sqStat==2) {   
	    			g.setColor(Color.RED);                  //hit
	    			g.fillOval(c*28+30, r*28+30, 10,10);   
	    		} else if (sqStat==3) {   
	    			g.setColor(Color.BLUE);                 //water  
	    			g.fillOval(c*28+30, r*28+30, 10,10);   
	    		}   
	    	} 
	    }
	    
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	private class MouseEventDemo implements MouseListener {
		private Ship ship;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = (int)(e.getPoint().getX()/25);
			int y = (int)(e.getPoint().getY()/25);
			//ship.setPosition(x, y, direction) //?????????????????????????????????????????????????????????????????????????????
			System.out.println(x); //zum testen
			System.out.println(y); //zum testen
			//Feld weiss einf�rben +Felder horizontal oder vertical f�r Schiff //+Felder speichern

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
