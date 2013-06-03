package battleship.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

/**
 * PlayingField Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Field extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	protected Point cursorLocation;
	private Rectangle gridRects[][] = new Rectangle[10][10];
//	private Image selection;
	
	public Field() {
//		selection = (new ImageIcon("graphics/selection.png")).getImage();
		for (int y=0; y<10; y++) {
			for (int x=0; x<10; x++) {
				gridRects[x][y] = new Rectangle(x*25,y*25,25,25);
			}
		}
		addMouseMotionListener(new MouseMovingHandler());
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
	    			g.setColor(Color.WHITE);                //water  
	    			g.fillOval(c*28+30, r*28+30, 10,10);   
	    		}   
	    	} 
	    }
	    
//		old Field2
//	    g.setColor(Color.BLACK);   
//	    g.fillRect(0, 0, 250, 250);         
//	    g.setColor(Color.WHITE);      
//	    //10 X 10 square    
//	    for (int i=0; i<11; i++) {
//	    	g.drawLine(i*25,0,i*25,250);   
//	    }
//	    for (int i=0; i<11; i++) {
//	    	g.drawLine(0,i*25,250,i*25);   
//	    }
//	    char ch='0';   
//	    for (int i=0; i<10; i++){                    //Spalten Koordinaten oben   
//	    	g.drawString(ch+" ",10+25*i,17);   
//	    	ch++;   
//	    }   
//	    ch='A';   
//	    for (int i=0; i<10; i++) {                   //Spalten Koordinaten links   
//	    	g.drawString(ch+" ",10,17+25*i);   
//	        ch++;   
//	    }   
//	         
//	    int sqStat=0;                                     
//	    for (int r=0; r<10; r++) {	                  
//	    	for (int c=0; c<10;c++){     
//	    		if (sqStat==2)  {   
//	    			g.setColor(Color.RED);                    //shot
//	    			g.fillOval(c*28+30, r*28+30, 10,10);   
//	    		} else if (sqStat==3)  {   
//	    			g.setColor(Color.WHITE);                  //water
//	    			g.fillOval(c*28+30, r*28+30, 10,10);   
//	    		}   
//	    	}   
//	    } 
		
		
	
	       
//		old Field3		
//		Graphics2D g2 = (Graphics2D)g;
//
//		GradientPaint gp = new GradientPaint(0.0f, 0.0f, new Color(0,0,255), 250.0f, 250.0f, new Color(255,255,255)); //farblicher Hintergrund von blau in weiss
//		g2.setPaint(gp);
//		g2.fillRect(0, 0, 250, 250); 
//
//		g2.setColor(new Color(0,0,0)); 
//		for (int i=1; i<10; i++) {
//			g2.drawLine(i*25,0,i*25,250);
//			g2.drawLine(0,i*25,250,i*25);
//		}
//		g2.setColor(Color.BLACK);
//		g2.draw3DRect(0,0,250,250,false);
//
//		g2.setColor(new Color(0,60,60));
		
		if (cursorLocation!=null) {
			g.drawImage(null, 25*(int)cursorLocation.getX(), 25*(int)cursorLocation.getY(), this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	private class MouseMovingHandler extends MouseMotionAdapter {
		private Rectangle lastSelected = new Rectangle();

		public void mouseMoved(MouseEvent e) {
			int x = (int)(e.getPoint().getX()/25);
			int y = (int)(e.getPoint().getY()/25);

			if(x<10 && y<10 && gridRects[x][y]!=lastSelected) {
				lastSelected = gridRects[x][y];
				cursorLocation = new Point(x,y);
				repaint();
			}
			System.out.println(x);
			System.out.println(y);
			//mouseClicked x und y in addShip Speichern und Textfile Feld anderst F�rben mit Feldl�nge des Schiffts
		}
	}
	
}
