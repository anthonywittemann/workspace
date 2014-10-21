import javax.swing.*;

/**
 *  This class defines a GUI window and has its own
 *  main method.
 */

public class EmbeddedMain extends JFrame
{
   private JLabel message;       // To display a message
   private final int WINDOW_WIDTH = 220; // Window width
   private final int WINDOW_HEIGHT = 70; // Window height
   
   /**
    *  Constructor 
    */
  
   public EmbeddedMain()
   {
      // Call the superclass constructor.
      super("A Simple Window");

      // Set the size of this window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify what happens when the close button is clicked.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create a label to display a message.
      message = new JLabel("This class has its own main method.");
      
      // Add the label to the content pane.
      add(message);

      // Display the window.
      setVisible(true);
   }
   
   /**
    *  The main method creates an instance of the
    *  EmbeddedMain class, which causes it to display
    *  its window.
    */

   public static void main(String[] args)
   {
      EmbeddedMain em = new EmbeddedMain();
   }
}