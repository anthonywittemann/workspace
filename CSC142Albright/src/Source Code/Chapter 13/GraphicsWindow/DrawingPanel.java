import javax.swing.*;
import java.awt.*;

/**
 * This class creates a panel that example shapes are
 * drawn on.
 */

public class DrawingPanel extends JPanel
{
   private JCheckBox[] checkBoxArray; // Check box array
   
   /**
    * Constructor
    */
      
   public DrawingPanel(JCheckBox[] cbArray)
   {
      // Reference the check box array.
      checkBoxArray = cbArray;
      
      // Set this panel's background color to white.
      setBackground(Color.WHITE);
      
      // Set the preferred size of the panel.
      setPreferredSize(new Dimension(300, 200));
   }
   
   /**
    * paintComponent method
    */
   
   public void paintComponent(Graphics g)
   {
      // Call the superclass paintComponent method.
      super.paintComponent(g);
      
      // Draw the selected shapes.
      if (checkBoxArray[0].isSelected())
      {
         g.setColor(Color.BLACK);
         g.drawLine(10, 10, 290, 190);
      }
      if (checkBoxArray[1].isSelected())
      {
         g.setColor(Color.BLACK);
         g.drawRect(20, 20, 50, 50);
      }
      if (checkBoxArray[2].isSelected())
      {
         g.setColor(Color.RED);
         g.fillRect(50, 30, 120, 120);
      }
      if (checkBoxArray[3].isSelected())
      {
         g.setColor(Color.BLACK);
         g.drawOval(40, 155, 75, 50);
      }  
      if (checkBoxArray[4].isSelected())
      {
         g.setColor(Color.BLUE);
         g.fillOval(200, 125, 75, 50);
      }
      if (checkBoxArray[5].isSelected())
      {
         g.setColor(Color.BLACK);
         g.drawArc(200, 40, 75, 50, 0, 90);
      }
      if (checkBoxArray[6].isSelected())
      {
         g.setColor(Color.GREEN);
         g.fillArc(100, 155, 75, 50, 0, 90);
      }
   }
}
