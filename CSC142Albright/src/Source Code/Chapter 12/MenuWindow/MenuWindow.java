import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  The MenuWindow class demonstrates a menu system.
 */

public class MenuWindow extends JFrame
{
   private JLabel messageLabel;            // To display a message
   private final int LABEL_WIDTH = 400;    // The label's width
   private final int LABEL_HEIGHT = 200;   // The label's height
   
   // The following variables will reference menu components.
   private JMenuBar menuBar;   // The menu bar
   private JMenu fileMenu;     // The File menu
   private JMenu textMenu;     // The Text menu
   private JMenuItem exitItem; // An item to exit the application
   private JRadioButtonMenuItem blackItem; // To make the text black
   private JRadioButtonMenuItem redItem;   // To make the text red
   private JRadioButtonMenuItem blueItem;  // To make the text blue
   private JCheckBoxMenuItem visibleItem;  // To toggle visibility
   
   /**
    *  Constructor
    */

   public MenuWindow()
   {
      // Call the JFrame constructor.
      super("Example Menu System");
    
      // Specify an action for the close button.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create the message label and set its size and color.
      messageLabel = new JLabel("Use the Text menu to " +
                 "change my color and make me invisible.",
                 SwingConstants.CENTER);
      messageLabel.setPreferredSize(
                 new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
      messageLabel.setForeground(Color.BLACK);

      // Add the label to the content pane.
      add(messageLabel);
    
      // Build the menu bar.
      buildMenuBar();
    
      // Pack and display the window.
      pack();
      setVisible(true);
   }

   /**
    *  The buildMenuBar method builds the menu bar.
    */

   private void buildMenuBar()
   {
      // Create the menu bar.
      menuBar = new JMenuBar();
      
      // Create the file and text menus.
      buildFileMenu();
      buildTextMenu();
      
      // Add the file and text menus to the menu bar.
      menuBar.add(fileMenu);
      menuBar.add(textMenu);
      
      // Set the window's menu bar.
      setJMenuBar(menuBar);
   }
   
   /**
    *  The buildFileMenu method builds the File menu
    *  and returns a reference to its JMenu object.
    */

   private void buildFileMenu()
   {
      // Create an Exit menu item.
      exitItem = new JMenuItem("Exit");
      exitItem.setMnemonic(KeyEvent.VK_X);
      exitItem.addActionListener(new ExitListener());

      // Create a JMenu object for the File menu.
      fileMenu = new JMenu("File");
      fileMenu.setMnemonic(KeyEvent.VK_F);
      
      // Add the Exit menu item to the File menu.
      fileMenu.add(exitItem);
   }
   
   /**
    * The buildTextMenu method builds the Text menu
    * and returns a reference to its JMenu object.
    */

   private void buildTextMenu()
   {
      // Create the radio button menu items to change the color
      // of the text. Add an action listener to each one.
      blackItem = new JRadioButtonMenuItem("Black", true);
      blackItem.setMnemonic(KeyEvent.VK_B);
      blackItem.addActionListener(new ColorListener());

      redItem = new JRadioButtonMenuItem("Red");
      redItem.setMnemonic(KeyEvent.VK_R);
      redItem.addActionListener(new ColorListener());
      
      blueItem = new JRadioButtonMenuItem("Blue");
      blueItem.setMnemonic(KeyEvent.VK_U);
      blueItem.addActionListener(new ColorListener());
      
      // Create a button group for the radio button items.
      ButtonGroup group = new ButtonGroup();
      group.add(blackItem);
      group.add(redItem);
      group.add(blueItem);
      
      // Create a check box menu item to make the text
      // visible or invisible.
      visibleItem = new JCheckBoxMenuItem("Visible", true);
      visibleItem.setMnemonic(KeyEvent.VK_V);
      visibleItem.addActionListener(new VisibleListener());

      // Create a JMenu object for the Text menu.
      textMenu = new JMenu("Text");
      textMenu.setMnemonic(KeyEvent.VK_T);

      // Add the menu items to the Text menu.
      textMenu.add(blackItem);
      textMenu.add(redItem);
      textMenu.add(blueItem);
      textMenu.addSeparator();   // Add a separator bar.
      textMenu.add(visibleItem);
   }

   /**
    * Private inner class that handles the event that
    * is generated when the user selects Exit from
    * the File menu.
    */
   
    private class ExitListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
    }
   
   /**
    * Private inner class that handles the event that
    * is generated when the user selects a color from
    * the Text menu.
    */
   
    private class ColorListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
         // Determine which color was selected and
         // act accordingly.
         if (blackItem.isSelected())
            messageLabel.setForeground(Color.BLACK);
         else if (redItem.isSelected())
            messageLabel.setForeground(Color.RED);
         else if (blueItem.isSelected())
            messageLabel.setForeground(Color.BLUE);
      }
    }
    
   /**
    * Private inner class that handles the event that
    * is generated when the user selects Visible from
    * the Text menu.
    */
   
   private class VisibleListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Determine whether Visible is selected and
         // act accordingly.
         if (visibleItem.isSelected())
            messageLabel.setVisible(true);
         else
            messageLabel.setVisible(false);
      }
   }

   /**
    * The main method creates an instance of the MenuWindow
    * class, which causes it to display its window.
    */

   public static void main(String[] args)
   {
      new MenuWindow();
   }
}