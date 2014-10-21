import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class demonstrates how a mouse motion listener
 * can track the location of the mouse pointer.
 */

public class FollowMe extends JApplet
{
	private int xCoord = 100, yCoord = 100;
	
	/**
	 * init method 
	 */
	
	public void init()
	{
		setBackground(Color.white);
		addMouseMotionListener(new MyMouseMotionListener());
	}
	
	/**
	 * paint method
	 */

	public void paint(Graphics g)
	{
		// Call the base class paint method.
		super.paint(g);
		
		// Draw the string at the current mouse location.
		g.drawString("Hello", xCoord, yCoord);
	}
	
	/**
	 * Private inner class that handles mouse
	 * motion events.
	 */
	
	private class MyMouseMotionListener implements MouseMotionListener
	{
		/**
		 * mouseMoved method 
		 */
	
		public void mouseMoved(MouseEvent e)
		{
			// Get the mouse pointer's X and Y coordinates.
			xCoord = e.getX();
			yCoord = e.getY();
			
			// Force the paint method to execute.
			repaint();
		}

		/**
		 * Unused mouseDragged method 
		 */

		public void mouseDragged(MouseEvent e)
		{
		}
	}
}
