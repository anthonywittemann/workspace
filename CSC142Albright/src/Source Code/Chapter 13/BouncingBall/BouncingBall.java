import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * This applet uses a Timer object to animate a bouncing ball.
 */

public class BouncingBall extends JApplet
{
   private final int X = 109;          // Ball's X coordinate
   private final int WIDTH = 40;       // Ball's width
   private final int HEIGHT = 40;      // Ball's height
   private final int TIME_DELAY = 30;  // Time delay (milliseconds)
   private final int MOVE = 20;        // Pixels to move the ball
   private final int MINIMUM_Y = 50;   // Maximum height of ball
   private final int MAXIMUM_Y = 400;  // Minimum height of ball
   private int y = 400;                // Ball's Y coordinate
   private Timer timer;                // Timer object
   private boolean goingUp = true;     // Direction indicator
               
   /**
    * init method 
    */
      
   public void init()
   {
      // Create a Timer object and register an ActionListener.
      timer = new Timer(TIME_DELAY, new TimerListener());
      
      // Start the timer.
      timer.start();
   }
   
   /**
    * paint method
    */
      
   public void paint(Graphics g)
   {
      // Call the superclass paint method.
      super.paint(g);
      
      // Set the drawing color to red.
      g.setColor(Color.RED);
      
      // Draw the ball.
      g.fillOval(X, y, WIDTH, HEIGHT);
   }

   /**
    * The private inner class handles the Timer object's
    * action events.
    */
   
   private class TimerListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Update the ball's Y coordinate.
         if (goingUp)
         {
            if (y > MINIMUM_Y)
               y -= MOVE;
            else
               goingUp = false;
         }
         else
         {
            if (y < MAXIMUM_Y)
               y += MOVE;
            else
               goingUp = true;
         }
            
         // Force a call to the paint method.
         repaint();
      }
   }
}
