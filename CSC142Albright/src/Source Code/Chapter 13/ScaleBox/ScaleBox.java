import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * This applet scales a box up or down when the up or down
 * arrow keys are pressed. 
 */

public class ScaleBox extends JApplet
{
   private int origSize = 10,    // Original size of the box
               origPoint = 194,  // Original X and Y, box's upper left
               x,                // Current X, box's upper left corner
               y,                // Current Y, box's upper left corner
               width,            // Width of the rectangle
               height;           // Height of the rectangle
               
	/**
	 * init method 
	 */
      
   public void init()
   {
      // Set the starting coordinates of the box's
      // upper left corner.
      x = origPoint;
      y = origPoint;
      
      // Set the box's starting width and height.
      width = origSize;
      height = origSize;
      
      // Add a key listener to this applet.
      addKeyListener(new MyKeyListener());
   }
   
	/**
	 * paint method 
	 */
      
   public void paint(Graphics g)
   {
      // Call the base class paint method.
      super.paint(g);
      
      // Set the drawing color to blue.
      g.setColor(Color.blue);
      
      // Draw the filled rectangle.
      g.fillRect(x, y, width, height);
   }

	/**
	 * Private inner class that handles key events.
	 */
   
   private class MyKeyListener extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         // Determine whether the up or down arrows were pressed.
         if (e.getKeyCode() == KeyEvent.VK_UP)
         {
            width *= 2;
            height *= 2;
            x = origPoint - (width / 2);
            y = origPoint - (height / 2);
         }
         else if (e.getKeyCode() == KeyEvent.VK_DOWN)
         {
            width /= 2;
            height /= 2;
            x = origPoint - (width / 2);
            y = origPoint - (height / 2);
         }
         
         // Force a call to the paint method.
         repaint();
      }
   }
}
