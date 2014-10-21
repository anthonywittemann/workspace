import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  This class demonstrates the List component in
 *  multiple interval selection mode.
 */

public class MultipleIntervalSelection extends JFrame
{
   private JList monthList;           // List of months
   private JList selectedMonthList;   // Selected months
   private JButton button;            // To get selected items
   private JPanel monthPanel;         // To hold components
   private JPanel selectedMonthPanel; // To hold components
   private JPanel buttonPanel;        // To hold the button


   // The following array holds the values that will be
   // displayed in the monthList list component.
   private String[] months = { "January", "February", "March",
            "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

   /**
    *  Constructor
    */

   public MultipleIntervalSelection()
   {
      // Call the JFrame constructor.
      super("List Demo");

      // Specify an action for the close button.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create a BorderLayout manager for the content pane.
      setLayout(new BorderLayout());

      // Build the panels.
      buildMonthPanel();
      buildSelectedMonthsPanel();
      buildButtonPanel();

      // Add the panels to the content pane.
      add(monthPanel, BorderLayout.NORTH);
      add(selectedMonthPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);

      // Pack and display the window.
      pack();
      setVisible(true);
   }

   /**
    *  The buildMonthPanel method adds a list containing the
    *  names of the months to a panel.
    */

   private void buildMonthPanel()
   {
      // Create a panel to hold the list.
      monthPanel = new JPanel();

      // Create the list.
      monthList = new JList(months);

      // Set the list to multiple interval selection mode.
      monthList.setSelectionMode(
          ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

      // Set the number of visible rows to 6.
      monthList.setVisibleRowCount(6);

      // Add the list to a scroll pane.
      JScrollPane monthListScrollPane = 
                         new JScrollPane(monthList);

      // Add the scroll pane to the panel.
      monthPanel.add(monthListScrollPane);
   }

   /**
    *  The buildSelectedMonthsPanel method adds a list to
    *  a panel. This will hold the selected months.
    */

   private void buildSelectedMonthsPanel()
   {
      // Create a panel to hold the list.
      selectedMonthPanel = new JPanel();

      // Create the list.
      selectedMonthList = new JList();

      // Set the number of visible rows to 6.
      selectedMonthList.setVisibleRowCount(6);

      // Add the list to a scroll pane.
      JScrollPane selectedMonthScrollPane =
                     new JScrollPane(selectedMonthList);

      // Add the scroll pane to the panel.
      selectedMonthPanel.add(selectedMonthScrollPane);
   }

   /**
    *  The buildButtonPanel method adds a button to a panel.
    */

   private void buildButtonPanel()
   {
      // Create a panel to hold the button.
      buttonPanel = new JPanel();

      // Create the button.
      button = new JButton("Get Selections");

      // Add an action listener to the button.
      button.addActionListener(new ButtonListener());

      // Add the button to the panel.
      buttonPanel.add(button);
   }

   /**
    *  Private inner class that handles the event when
    *  the user clicks the "Get Selections" button.
    */

   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Get all the items that were selected. 
         Object[] selections = monthList.getSelectedValues();
         
         // Display the items in selectedMonthList.
         selectedMonthList.setListData(selections);
      }
   }

   /**
    *  The main method creates an instance of the class,
    *  which causes it to display its window.
    */

   public static void main(String[] args)
   {
      new MultipleIntervalSelection();
   }
}