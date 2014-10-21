import javax.swing.*;
import java.awt.*;

/**
 * This class is an applet that demonstrates how lines
 * can be drawn.
 */

public class LineDemo extends JApplet
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
      // Call the base class paint method.
      super.paint(g);
      
      // Draw a red line from (20, 20) to (280, 280).
      g.setColor(Color.RED);
      g.drawLine(20, 20, 280, 280);
      
      // Draw a blue line from (280, 20) to (20, 280).
      g.setColor(Color.BLUE);
      g.drawLine(280, 20, 20, 280);
   }
}
