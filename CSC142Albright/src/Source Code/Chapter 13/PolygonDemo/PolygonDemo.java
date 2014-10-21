import javax.swing.*;
import java.awt.*;

/**
 * This class is an applet that demonstrates how a
 * polygon can be drawn.
 */

public class PolygonDemo extends JApplet
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
      int[] xCoords = {60, 100, 140, 140, 100, 60, 20, 20};
      int[] yCoords = {20, 20, 60, 100, 140, 140, 100, 60};
      
      // Call the superclass paint method.
      super.paint(g);
      
      // Set the drawing color.
      g.setColor(Color.RED);
      
      // Draw the polygon.
      g.fillPolygon(xCoords, yCoords, 8);
   }
}