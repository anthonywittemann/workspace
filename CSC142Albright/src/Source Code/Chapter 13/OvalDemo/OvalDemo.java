import javax.swing.*;
import java.awt.*;

/**
 * This class is an applet that demonstrates how
 * ovals can be drawn.
 */

public class OvalDemo extends JApplet
{
   /**
    * init method
    */
   
   public void init()
   {
      // Set the background color to white.
      getContentPane().setBackground(Color.WHITE);
   }
   
   /**
    * paint method
    */
   
   public void paint(Graphics g)
   {
      // Call the superclass paint method.
      super.paint(g);
      
      // Draw a black unfilled oval.
      g.setColor(Color.BLACK);
      g.drawOval(20, 20, 120, 75);
      
      // Draw a green-filled rectangle.
      g.setColor(Color.GREEN);
      g.fillOval(80, 160, 180, 75);
   }
}
