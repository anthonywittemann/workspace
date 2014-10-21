import javax.swing.*;  // Needed for Swing classes

/**
 *  This program displays a simple window with a title. The
 *  application exits when the user clicks the close button.
 */

public class SimpleWindow
{
   public static void main(String[] args)
   {
      final int WINDOW_WIDTH = 350,  // Window width in pixels
                WINDOW_HEIGHT = 250; // Window height in pixels

      // Create a window with a title.
      JFrame window = new JFrame("A Simple Window");

      // Set the size of the window.
      window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify what happens when the close button is clicked.
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Display the window.
      window.setVisible(true);
   }
}
