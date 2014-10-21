import javax.swing.*;

/**
 *  The KiloConverterWindow class lets the user enter a
 *  distance in kilometers. When the Calculate button is
 *  clicked, a dialog box is displayed with the distance
 *  converted to miles.
 */

public class KiloConverterWindow extends JFrame
{
   private JPanel panel;             // A panel container
   private JLabel messageLabel;      // A message to display
   private JTextField kiloTextField; // To hold user input
   private JButton calcButton;       // Performs calculation
   private final int WINDOW_WIDTH = 320;  // Window width
   private final int WINDOW_HEIGHT = 100; // Window height

	/**
	 *  Constructor
	 */

   public KiloConverterWindow()
   {
      // Call the JFrame constructor.
      super("Kilometer Converter");

      // Set the size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify what happens when the close
      // button is clicked.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Build the panel and add it to the frame.
      buildPanel();

      // Add the panel to the frame's content pane.
      add(panel);

      // Display the window.
      setVisible(true);
   }

	/**
	 *  The buildPanel method adds a label, text field, and
    *  a button to a panel.
	 */

   private void buildPanel()
   {
      // Create the label, text field, and button components.
      messageLabel = new JLabel("Enter a distance in kilometers");
      kiloTextField = new JTextField(10);
      calcButton = new JButton("Calculate");

      // Create a panel to hold the components.
      panel = new JPanel();
      
      // Add the label, text field, and button to the panel.
      panel.add(messageLabel);
      panel.add(kiloTextField);
      panel.add(calcButton);
   }
}