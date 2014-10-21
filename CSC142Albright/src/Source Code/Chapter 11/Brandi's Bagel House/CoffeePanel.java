import java.awt.*;
import javax.swing.*;

/**
 *  The CoffeePanel class allows the user to select coffee
 */

public class CoffeePanel extends JPanel
{
   // The following constants are used to indicate
   // the cost of coffee.
   public final double NO_COFFEE = 0.0;
   public final double REGULAR_COFFEE = 1.25;
   public final double DECAF_COFFEE = 1.25;
   public final double CAPPUCCINO = 2.00;

   // Radio buttons for the available coffees.
   private JRadioButton noCoffee;      // No coffee
   private JRadioButton regularCoffee; // Regular coffee
   private JRadioButton decafCoffee;   // Decaf
   private JRadioButton cappuccino;    // Cappuccino

   // The following variable will reference a
   // ButtonGroup object to group the radio buttons.
   private ButtonGroup bg;

   /**
    *  Constructor
    */

   public CoffeePanel()
   {
      // Create a GridLayout manager with 
      // four rows and one column.
      setLayout(new GridLayout(4, 1));

      // Create the radio buttons.
      noCoffee = new JRadioButton("None");
      regularCoffee = new JRadioButton("Regular coffee", true);
      decafCoffee = new JRadioButton("Decaf coffee");
      cappuccino = new JRadioButton("Cappuccino");

      // Group the radio buttons and add them to this panel.
      bg = new ButtonGroup();
      bg.add(noCoffee);
      bg.add(regularCoffee);
      bg.add(decafCoffee);
      bg.add(cappuccino);

      // Add a border around the panel.
      setBorder(BorderFactory.createTitledBorder("Coffee"));

      // Add the radio buttons to this panel.
      add(noCoffee);
      add(regularCoffee);
      add(decafCoffee);
      add(cappuccino);
   }

   /**
    *  The getCoffeeCost method returns the cost of
    *  the selected coffee.
    */

   public double getCoffeeCost()
   {
      // The following variable will hold the cost
      // of the selected coffee.
      double coffeeCost = 0.0;

      // Determine which coffee is selected.
      if (noCoffee.isSelected())
         coffeeCost = NO_COFFEE;
      else if (regularCoffee.isSelected())
         coffeeCost = REGULAR_COFFEE;
      else if (decafCoffee.isSelected())
         coffeeCost = DECAF_COFFEE;
      else if (cappuccino.isSelected())
         coffeeCost = CAPPUCCINO;

      // Return the coffee cost.
      return coffeeCost;
   }
}