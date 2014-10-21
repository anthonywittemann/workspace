import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * This applet demonstrates how the mouse adapter
 * classes can be used. 
 */

public class DrawBoxes2 extends JApplet
{
   private int currentX = 0; // Current X coordinate
   private int currentY = 0; // Current Y coordinate
   private int width = 0;    // Rectangle width
   private int height = 0;   // Rectangle height

   /**
    * init method
    */
   
   public void init()
   {
      // Add a mouse listener and a mouse motion listener.
      addMouseListener(new MyMouseListener());
      addMouseMotionListener(new MyMouseMotionListener());
   }
   
   /**
    * paint method
    */
   
   public void paint(Graphics g)
   {
      // Call the superclass's paint method.
      super.paint(g);
      
      // Draw a rectangle.
      g.drawRect(currentX, currentY, width, height);
   }
   
   /**
    * Mouse listener class
    */

   private class MyMouseListener extends MouseAdapter
   {
      public void mousePressed(MouseEvent e)
      {
         // Get the mouse cursor's X and Y coordinates.
         currentX = e.getX();
         currentY = e.getY();
      }
   }
   
   /**
    * Mouse Motion listener class 
    */

   private class MyMouseMotionListener
                              extends MouseMotionAdapter
   {
      public void mouseDragged(MouseEvent e)
      {
         // Calculate the size of the rectangle.
         width = e.getX() - currentX;
         height = e.getY() - currentY;
         
         // Repaint the window.
         repaint();
      }
   }
}