import java.awt.*;      // Needed for the FlowLayout manager
import javax.swing.*;

/**
 *  This class demonstrates how to use a FlowLayout manager
 *  with a JFrame object's content pane.
 */

public class FlowWindow extends JFrame
{
   private final int WINDOW_WIDTH = 200;  // Window width
   private final int WINDOW_HEIGHT = 105; // Window height

   /**
    *  Constructor 
    */

   public FlowWindow()
   {
      // Set the title bar text.
      super("Flow Layout");

      // Set the size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify what happens when the close button is clicked.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Add a FlowLayout manager to the content pane.
      setLayout(new FlowLayout());
          
      // Create three buttons.
      JButton button1 = new JButton("Button 1");
      JButton button2 = new JButton("Button 2");
      JButton button3 = new JButton("Button 3");

      // Add the three buttons to the content pane.
      add(button1);
      add(button2);
      add(button3);

      // Display the window.
      setVisible(true);
   }

   /**
    *  The main method creates an instance of the
    *  FlowWindow class, causing it to display its window.
    */
   
   public static void main(String[] args)
   {
      new FlowWindow();
   }
}