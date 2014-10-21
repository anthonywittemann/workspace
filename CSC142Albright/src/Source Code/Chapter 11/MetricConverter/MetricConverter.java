import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  The MetricConverter class lets the user enter a distance
 *  in kilometers. Radio buttons can be selected to convert
 *  the kilometers to miles, feet, or inches.
 */

public class MetricConverter extends JFrame
{
   private JPanel panel;        // A panel to hold components
   private JLabel messageLabel; // A message to the user
   private JTextField kiloTextField;  // To hold user input
   private JRadioButton milesButton;  // Miles conversion button
   private JRadioButton feetButton;   // Feet conversion button
   private JRadioButton inchesButton; // Inches conversion button
   private ButtonGroup radioButtonGroup;  // To group radio buttons
   private final int WINDOW_WIDTH = 400;  // Window width
   private final int WINDOW_HEIGHT = 100; // Window height

   /**
    *  Constructor
    */

   public MetricConverter()
   {
      // Call the JFrame constructor.
      super("Metric Converter");

      // Set the size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify an action for the close button.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Build the panel.
      buildPanel();

      // Add the panel to the frame's content pane.
      add(panel);

      // Display the window.
      setVisible(true);
   }

   /**
    *  The buildPanel method adds a label, text field, and
    *  three radio buttons to a panel. 
    */

   private void buildPanel()
   {
      // Create the label, text field, and radio buttons.
      messageLabel = new JLabel("Enter a distance in kilometers");
      kiloTextField = new JTextField(10);
      milesButton = new JRadioButton("Convert to miles");
      feetButton = new JRadioButton("Convert to feet");
      inchesButton = new JRadioButton("Convert to inches");

      // Group the radio buttons.
      radioButtonGroup = new ButtonGroup();
      radioButtonGroup.add(milesButton);
      radioButtonGroup.add(feetButton);
      radioButtonGroup.add(inchesButton);

      // Add action listeners to the radio buttons.
      milesButton.addActionListener(new RadioButtonListener());
      feetButton.addActionListener(new RadioButtonListener());
      inchesButton.addActionListener(new RadioButtonListener());

      // Create a panel and add the components to it.
      panel = new JPanel();
      panel.add(messageLabel);
      panel.add(kiloTextField);
      panel.add(milesButton);
      panel.add(feetButton);
      panel.add(inchesButton);
   }

   /**
    *  Private inner class that handles the event when
    *  the user clicks one of the radio buttons.
    */

   private class RadioButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String input;          // To hold input
         String convertTo = ""; // What we are converting to
         double result = 0.0;   // To hold the conversion

         // Get input from the text field.
         input = kiloTextField.getText();
         
         // Determine the button that was clicked and
         // perform the selected conversion.
         if (e.getSource() == milesButton)
         {
            convertTo = " miles.";
            result = Double.parseDouble(input) * 0.6214;
         }
         else if (e.getSource() == feetButton)
         {
            convertTo = " feet.";
            result = Double.parseDouble(input) * 3281.0;
         }
         else if (e.getSource() == inchesButton)
         {
            convertTo = " inches.";
            result = Double.parseDouble(input) * 39370.0;
         }

         // Display the converted distance.
         JOptionPane.showMessageDialog(null, input + 
                 " kilometers is " + result + convertTo);
      }
   }

   /**
    *  The main method creates an instance of the
    *  MetricConverter class, causing it to display
    *  its window. 
    */

   public static void main(String[] args)
   {
      new MetricConverter();
   }
}