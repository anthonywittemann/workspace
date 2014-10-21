import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class demonstrates an editable combo box.
 */

public class EditableComboBoxWindow extends JFrame
{
     private JPanel coffeePanel,          // To hold components
                    selectedCoffeePanel;  // To hold components
     private JComboBox coffeeBox;         // Holds a list of coffees
     private JLabel label;                // To display a message.
     private JTextField selectedCoffee;   // To display the selected coffee
     private Container contentPane;       // To reference the content pane

     // The following array holds the values that will be displayed
     // in the coffeeBox combo box.
     private String[] coffee = { "Regular Coffee", "Dark Roast",
                                 "Cappuccino", "Espresso", "Decaf"};

	/**
	 * Constructor
	 */

     public EditableComboBoxWindow()
     {
          // Call the JFrame constructor.
          super("Combo Box Demo");

          // Specify what happens when the close button is clicked.
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          // Get a reference to the content pane.
          contentPane = getContentPane();

          // Create a BorderLayout manager for the content pane.
          contentPane.setLayout(new BorderLayout());

          // Build the panels.
          buildCoffeePanel();
          buildSelectedCoffeePanel();

          // Add the panels to the content pane.
          contentPane.add(coffeePanel, BorderLayout.CENTER);
          contentPane.add(selectedCoffeePanel, BorderLayout.SOUTH);

          // Pack and display the window.
          pack();
          setVisible(true);
     }

	/**
	 * The buildCoffeePanel method adds a combo box with the
     * types of coffee to a panel.
	 */

     private void buildCoffeePanel()
     {
          // Create a panel to hold the list.
          coffeePanel = new JPanel();

          // Create the combo box and make it editable.
          coffeeBox = new JComboBox(coffee);
          coffeeBox.setEditable(true);

          // Register an action listener.
          coffeeBox.addActionListener(new ComboBoxListener());

          // Add the combo box to the panel.
          coffeePanel.add(coffeeBox);
     }

	/**
	 * The buildSelectedCoffeePanel method adds a read-only
     * text field to a panel.
	 */

     private void buildSelectedCoffeePanel()
     {
          // Create a panel to hold the list.
          selectedCoffeePanel = new JPanel();

          // Create the label.
          label = new JLabel("You selected: ");

          // Create the uneditable text field.
          selectedCoffee = new JTextField(10);
          selectedCoffee.setEditable(false);

          // Add the label and text field to the panel.
          selectedCoffeePanel.add(label);
          selectedCoffeePanel.add(selectedCoffee);
     }

	/**
	 * Private inner class that handles the event when
     * the user selects an item from the combo box.
	 */

     private class ComboBoxListener implements ActionListener
     {
          public void actionPerformed(ActionEvent e)
          {
               String selection = (String) coffeeBox.getSelectedItem();
               selectedCoffee.setText(selection);
          }
     }
}
