import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class demonstrates the List Component in
 * single interval selection mode. 
 */

public class SingleIntervalSelection extends JFrame
{
     private JPanel monthPanel,         // To hold components
                    selectedMonthPanel, // To hold components
                    buttonPanel;        // To hold the button
     private JList monthList,           // To hold the months
                   selectedMonthList;   // To hold the selected months
     private JScrollPane scrollPane1,   // A scroll pane for the first list
                         scrollPane2;   // A scroll pane for the second list
     private JButton button;            // To get the selected items
     private Container contentPane;     // To reference the content pane

     // The following array holds the values that will be displayed
     // in the monthList list component.
     private String[] months = { "January", "February", "March",
                  "April", "May", "June", "July", "August",
                  "September", "October", "November", "December" };

	/**
	 * Constructor
	 */

     public SingleIntervalSelection()
     {
          // Call the JFrame constructor.
          super("List Demo");

          // Specify what happens when the close button is clicked.
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          // Get a reference to the content pane.
          contentPane = getContentPane();

          // Create a BorderLayout manager for the content pane.
          contentPane.setLayout(new BorderLayout());

          // Build the panels.
          buildMonthPanel();
          buildSelectedMonthsPanel();
          buildButtonPanel();

          // Add the panels to the content pane.
          contentPane.add(monthPanel, BorderLayout.NORTH);
          contentPane.add(selectedMonthPanel, BorderLayout.CENTER);
          contentPane.add(buttonPanel, BorderLayout.SOUTH);

          // Pack and display the window.
          pack();
          setVisible(true);
     }

	/**
	 * The buildMonthPanel method adds a list containing the
     * names of the months to a panel.
	 */

     private void buildMonthPanel()
     {
          // Create a panel to hold the list.
          monthPanel = new JPanel();

          // Create the list.
          monthList = new JList(months);

          // Set the selection mode to single interval selection.
          monthList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

          // Set the number of visible rows to 6.
          monthList.setVisibleRowCount(6);

          // Add the list to a scroll pane.
          scrollPane1 = new JScrollPane(monthList);

          // Add the scroll pane to the panel.
          monthPanel.add(scrollPane1);
     }

	/**
	 * The buildSelectedMonthsPanel method adds a list to
     * a panel. This will hold the selected months.
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
          scrollPane2 = new JScrollPane(selectedMonthList);

          // Add the scroll pane to the panel.
          selectedMonthPanel.add(scrollPane2);
     }

	/**
	 * The buildButtonPanel method adds a button to a panel.
	 */

     private void buildButtonPanel()
     {
          // Create a panel to hold the list.
          buttonPanel = new JPanel();

          // Create the button.
          button = new JButton("Get Selections");

          // Add an action listener to the button.
          button.addActionListener(new ButtonListener());

          // Add the button to the panel.
          buttonPanel.add(button);
     }

	/**
	 * Private inner class that handles the event when
     * the user clicks the calcbutton.
	 */

     private class ButtonListener implements ActionListener
     {
          public void actionPerformed(ActionEvent e)
          {
               Object[] selections = monthList.getSelectedValues();
               selectedMonthList.setListData(selections);
          }
     }
}
