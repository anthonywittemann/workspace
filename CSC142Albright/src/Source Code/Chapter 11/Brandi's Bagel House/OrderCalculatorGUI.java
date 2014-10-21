import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;

/**
 *  The OrderCalculatorGUI class creates the GUI for the
 *  Brandi's Bagel House application.
 */

public class OrderCalculatorGUI extends JFrame
{
   // The following constant is for the sales tax rate.
   private final double TAX_RATE = 0.06;

   // The following variables will reference the
   // custom panel objects.
   private BagelPanel bagels;     // Bagel panel
   private ToppingPanel toppings; // Topping panel
   private CoffeePanel coffee;    // Coffee panel
   private GreetingPanel banner;  // To display a greeting

   // The following variables will reference objects
   // needed to add the Calculate and Exit buttons.
   private JPanel buttonPanel;    // To hold the buttons
   private JButton calcButton;    // To calculate the cost
   private JButton exitButton;    // To exit the application


   /**
    *  Constructor
    */

   public OrderCalculatorGUI()
   {
      // Display a title.
      super("Order Calculator");

      // Specify an action for the close button.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create a BorderLayout manager for
      // the content pane.
      setLayout(new BorderLayout());

      // Create the custom panels.
      banner = new GreetingPanel();
      bagels = new BagelPanel();
      toppings = new ToppingPanel();
      coffee = new CoffeePanel();

      // Call the buildButtonPanel method to
      // create the button panel.
      buildButtonPanel();

      // Add the components to the content pane.
      add(banner, BorderLayout.NORTH);
      add(bagels, BorderLayout.WEST);
      add(toppings, BorderLayout.CENTER);
      add(coffee, BorderLayout.EAST);
      add(buttonPanel, BorderLayout.SOUTH);

      // Pack the contents of the window and display it.
      pack();
      setVisible(true);
   }

   /**
    *  The buildButtonPanel method builds the button panel.
    */

   private void buildButtonPanel()
   {
      // Create a panel for the buttons.
      buttonPanel = new JPanel();

      // Create the buttons.
      calcButton = new JButton("Calculate");
      exitButton = new JButton("Exit");

      // Register the action listeners.
      calcButton.addActionListener(new CalcButtonListener());
      exitButton.addActionListener(new ExitButtonListener());

      // Add the buttons to the button panel.
      buttonPanel.add(calcButton);
      buttonPanel.add(exitButton);
   }

   /**
    *  Private inner class that handles the event when
    *  the user clicks the Calculate button.
    */

   private class CalcButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         double subtotal;  // The order subtotal
         double tax;       // The amount of sales tax
         double total;     // The order total

         // Calculate the subtotal.
         subtotal = bagels.getBagelCost() + 
                    toppings.getToppingCost() +
                    coffee.getCoffeeCost();

         // Calculate the sales tax.
         tax = subtotal * TAX_RATE;

         // Calculate the total.
         total = subtotal + tax;

         // Create a DecimalFormat object to format
         // the total as a dollar amount.
         DecimalFormat dollar = new DecimalFormat("0.00");

         // Display the charges.
         JOptionPane.showMessageDialog(null, "Subtotal: $" +
                      dollar.format(subtotal) + "\n" +
                      "Tax: $" + dollar.format(tax) + "\n" +
                      "Total: $" + dollar.format(total));
      }
   }

   /**
    *  Private inner class that handles the event when
    *  the user clicks the Exit button. 
    */

   private class ExitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Exit the application.
          System.exit(0);
      }
   }
}