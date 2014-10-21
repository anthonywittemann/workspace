import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * This applet demonstrates how mouse events and mouse
 * motion events can be handled. It lets the user draw
 * boxes by dragging the mouse.
 */

public class DrawBoxes extends JApplet
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

   private class MyMouseListener implements MouseListener
   {
      public void mousePressed(MouseEvent e)
      {
         // Get the X and Y coordinates of the mouse cursor.
         currentX = e.getX();
         currentY = e.getY();
      }

   /**
    * The following methods are unused, but still
    * required by the MouseListener interface. 
    */

      public void mouseClicked(MouseEvent e)
      {
      }

      public void mouseReleased(MouseEvent e)
      {
      }

      public void mouseEntered(MouseEvent e)
      {
      }

      public void mouseExited(MouseEvent e)
      {
      }
   }
   
   /**
    * Mouse Motion listener class 
    */

   private class MyMouseMotionListener
                         implements MouseMotionListener
   {
      public void mouseDragged(MouseEvent e)
      {
         // Calculate the size of the rectangle.
         width = e.getX() - currentX;
         height = e.getY() - currentY;
         
         // Repaint the window.
         repaint();
      }
      
   /**
    * The following method is unused, but still
    * required by the MouseMotionListener interface.
    */
      
      public void mouseMoved(MouseEvent e)
      {
      }
   }
}