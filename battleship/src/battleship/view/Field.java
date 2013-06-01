package battleship.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * PlayingField Battleship
 * @author Hinrich Kaestner, Tom Ohme
 * 
 */
public class Field extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected Point cursorLocation;
	private Rectangle gridRects[][] = new Rectangle[10][10];
	private Image selection;
	
	public Field() {
		selection = (new ImageIcon("graphics/selection.jpg")).getImage();
		for (int y=0; y<10; y++) {
			for (int x=0; x<10; x++) {
				gridRects[x][y] = new Rectangle(x*25,y*25,25,25);
			}
		}
		addMouseMotionListener(new MouseMovingHandler());
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		GradientPaint gp = new GradientPaint(0.0f, 0.0f, new Color(0,0,255), 250.0f, 250.0f, new Color(255,255,255)); //farblicher Hintergrund von blau in weiss
		g2.setPaint(gp);
		g2.fillRect(0, 0, 250, 250); 

		g2.setColor(new Color(0,0,0)); 
		for (int i=1; i<10; i++) {
			g2.drawLine(i*25,0,i*25,250);
			g2.drawLine(0,i*25,250,i*25);
		}
		g2.setColor(Color.BLACK);
		g2.draw3DRect(0,0,250,250,false);

		g2.setColor(new Color(0,60,60));
		
		if (cursorLocation!=null) {
			g2.drawImage(selection, 25*(int)cursorLocation.getX(), 25*(int)cursorLocation.getY(), this);
		}
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
		}
	}

}
