/**
 * 
 */
package aw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author anthonywittemann
 *
 */
public class FactoryFrame extends JFrame{
	
	private JFileChooser fileChooser;
	private File factoryRCPFile;
	
	public FactoryFrame(){
		super("Factory");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ****** MENUBAR & OPENING XML FILE ***/
		JMenuBar menuBar = new JMenuBar();
		JMenuItem openFileItem = new JMenuItem("Open Folder...");


		openFileItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				fileChooser = new JFileChooser();
				fileChooser.setVisible(true);
				FileNameExtensionFilter factoryRCPFilter = new FileNameExtensionFilter("Factory or RCP files", "factory", "rcp"); //only .factory, .rcp
				fileChooser.setFileFilter(factoryRCPFilter);
				int returnVal = fileChooser.showOpenDialog(FactoryFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					factoryRCPFile = fileChooser.getSelectedFile();
//					System.out.println("You chose to open this file: " + factoryRCPFile.getName());
				}

			}

		});
		menuBar.add(openFileItem);
		setJMenuBar(menuBar);
		
		//pack();
		setVisible(true);
	}


	public static void main(String[] args) {
		new FactoryFrame();

	}

}
