import java.awt.*;      // Needed for the FlowLayout manager
import javax.swing.*;

/**
 * This class demonstrates how to use a FlowLayout manager
 * with a JFrame object's content pane.
 */

public class FlowWindowWithAlignment extends JFrame
{
   private JButton button1, button2, button3; // Some buttons
   private final int WINDOW_WIDTH = 200;  // Window width
   private final int WINDOW_HEIGHT = 105; // Window height

	/**
	 * Constructor
	 */

   public FlowWindowWithAlignment()
   {
      // Set the title bar text.
      super("Right");

      // Set the size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify what happens when the close button is clicked.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // add a FlowLayout manager to the content pane.
		// Specify right alignment.
      setLayout(new FlowLayout(FlowLayout.RIGHT));
          
      // Create three buttons.
      button1 = new JButton("Button 1");
      button2 = new JButton("Button 2");
      button3 = new JButton("Button 3");

      // Add the three buttons to the content pane.
      add(button1);
      add(button2);
      add(button3);

      // Display the window.
      setVisible(true);
   }

	/**
	 * The main method creates an instance of the
     * FlowWindow class, causing it to display its window.
	 */
   
   public static void main(String[] args)
   {
      FlowWindowWithAlignment fw =
		                   new FlowWindowWithAlignment();
   }
}

