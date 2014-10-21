import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

/**
 *  This class demonstrates the List Component with a
 *  scroll pane.
 */

public class ListWindowWithScroll extends JFrame
{
   private JPanel monthPanel;         // To hold components
   private JPanel selectedMonthPanel; // To hold components
   private JList monthList;           // A list of months
   private JTextField selectedMonth;  // The selected month
   private JLabel label;              // To display a message

   // The following array holds the values that will be
   // displayed in the monthList list component.
   private String[] months = { "January", "February", "March",
             "April", "May", "June", "July", "August",
             "September", "October", "November", "December" };

   /**
    *  Constructor
    */

   public ListWindowWithScroll()
   {
      // Call the JFrame constructor.
      super("List Demo");

      // Specify an action for the close button.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create a BorderLayout manager for the content pane.
      setLayout(new BorderLayout());

      // Build the month and selectedMonth panels.
      buildMonthPanel();
      buildSelectedMonthPanel();

      // Add the panels to the content pane.
      add(monthPanel, BorderLayout.CENTER);
      add(selectedMonthPanel, BorderLayout.SOUTH);

      // Pack and display the window.
      pack();
      setVisible(true);
   }

   /**
    *  The buildMonth Panel method adds a list containing
    *  the names of the months to a panel. 
    */

   private void buildMonthPanel()
   {
      // Create a panel to hold the list.
      monthPanel = new JPanel();

      // Create the list.
      monthList = new JList(months);

      // Set the selection mode to single selection.
      monthList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // Register the list selection listener.
      monthList.addListSelectionListener(new ListListener());
   
      // Set the number of visible rows to 6.
      monthList.setVisibleRowCount(6);

      // Add the list to a scroll pane.
      JScrollPane scrollPane = new JScrollPane(monthList);

      // Add the scroll pane to the panel.
      monthPanel.add(scrollPane);
   }

   /**
    *  The buildSelectedMonth Panel method adds an uneditable
    *  test field to a panel.
    */

   private void buildSelectedMonthPanel()
   {
      // Create a panel to hold the text field.
      selectedMonthPanel = new JPanel();

      // Create the label.
      label = new JLabel("You selected: ");

      // Create the text field.
      selectedMonth = new JTextField(10);

      // Make the text field uneditable.
      selectedMonth.setEditable(false);

      // Add the label and text field to the panel.
      selectedMonthPanel.add(label);
      selectedMonthPanel.add(selectedMonth);
   }

   /**
    *  Private inner class that handles the event when
    *  the user selects an item from the list.
    */

   private class ListListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         // Get the selected string from the list.
         String selection = (String) monthList.getSelectedValue();
         
         // Store the selected string in the text field.
         selectedMonth.setText(selection);
      }
   }

   /**
    *  The main method creates an instance of the class,
    *  which causes it to display its window.
    */
   
   public static void main(String[] args)
   {
      new ListWindowWithScroll();
   }
}