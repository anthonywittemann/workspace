import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  The ColorCheckBoxWindow class demonstrates how check boxes
 *  can be used. 
 */

public class ColorCheckBoxWindow extends JFrame
{
   private JLabel messageLabel;      // A message
   private JCheckBox yellowCheckBox; // To select yellow
   private JCheckBox redCheckBox;    // To select red
   private final int WINDOW_WIDTH = 300;  // Width
   private final int WINDOW_HEIGHT = 100; // Height

   /**
    *  Constructor
    */

   public ColorCheckBoxWindow()
   {
      // Call the JFrame constructor.
      super("Color Check Boxes");

      // Set the size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

      // Specify an action for the close button.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create a label displaying a message to the user.
      messageLabel = new JLabel("Select the check boxes " +
                                "to change colors.");

      // Create the check boxes.
      yellowCheckBox = new JCheckBox("Yellow background");
      redCheckBox = new JCheckBox("Red foreground");

      // Add an item listener to the check boxes.
      yellowCheckBox.addItemListener(new CheckBoxListener());
      redCheckBox.addItemListener(new CheckBoxListener());

      // Add a FlowLayout manager to the content pane.
      setLayout(new FlowLayout());

      // Add the label and check boxes to the content pane.
      add(messageLabel);
      add(yellowCheckBox);
      add(redCheckBox);

      // Display the window.
      setVisible(true);
   }

   /**
    *  Private inner class that handles the event when
    *  the user clicks one of the check boxes. 
    */

   private class CheckBoxListener implements ItemListener
   {
      public void itemStateChanged(ItemEvent e)
      {
         // Determine whether yellowCheckBox was clicked.
         if (e.getSource() == yellowCheckBox)
         {
            // Is yellowCheckBox selected?
            if (yellowCheckBox.isSelected())
            {
               // Set the content pane background to yellow.
               getContentPane().setBackground(Color.YELLOW);
               // Set the yellowCheckBox background to yellow.
               yellowCheckBox.setBackground(Color.YELLOW);
               // Set the redCheckBox background to yellow.
               redCheckBox.setBackground(Color.YELLOW);
            }
            else
            {
               // Set the content pane background to gray.
               getContentPane().setBackground(Color.LIGHT_GRAY);
               // Set the yellowCheckBox background to gray.
               yellowCheckBox.setBackground(Color.LIGHT_GRAY);
               // Set the redCheckBox background to gray.
               redCheckBox.setBackground(Color.LIGHT_GRAY);
            }
         }
         // Determine whether redCheckBox was clicked.
         else if (e.getSource() == redCheckBox)
         {
            // Is redCheckBox selected?
            if (redCheckBox.isSelected())
            {
               // Set the label text to red.
               messageLabel.setForeground(Color.RED);
               // Set the yellowCheckBox text to red.
               yellowCheckBox.setForeground(Color.RED);
               // Set the redCheckBox text to red.
               redCheckBox.setForeground(Color.RED);
            }
            else
            {
               // Set the label text to black.
               messageLabel.setForeground(Color.BLACK);
               // Set the yellowCheckBox text to black.
               yellowCheckBox.setForeground(Color.BLACK);
               // Set the redCheckBox text to black.
               redCheckBox.setForeground(Color.BLACK);
            }
         }
      }
   }

   /**
    *  The main method creates an instance of the
    *  ColorCheckBoxWindow class, displaying its window.
    */

   public static void main(String[] args)
   {
      new ColorCheckBoxWindow();
   }
}