import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * @author anthonywittemann
 * Chapter 11 Pr 8 Latin Translator
 * HW Due 5/8/14
 */
public class AWLatinTranslator extends JFrame{
	private JButton sinisterB;
	private JButton dexterB;
	private JButton mediumB;
	private JLabel translationLbl;
	private JPanel masterPanel;
	private JPanel bPanel; //contains buttons
	private JPanel lblPanel; //contains label

	public AWLatinTranslator(){
		super("Latin Translator AW");
		setSize(300, 120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		sinisterB = new JButton("Sinister");
		sinisterB.addActionListener(new SinBListener());
		dexterB = new JButton("Dexter");
		dexterB.addActionListener(new DexBListener());
		mediumB = new JButton("Medium");
		mediumB.addActionListener(new MedBListener());
		translationLbl = new JLabel("                     English Translation: ");
		
		
		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		bPanel = new JPanel();
		bPanel.add(sinisterB);
		bPanel.add(dexterB);
		bPanel.add(mediumB);
		lblPanel = new JPanel();
		lblPanel.setLayout(new BorderLayout());
		lblPanel.add(translationLbl, BorderLayout.CENTER);
		masterPanel.add(lblPanel, BorderLayout.NORTH);
		masterPanel.add(bPanel, BorderLayout.SOUTH);
		
		
		add(masterPanel);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class SinBListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			translationLbl.setText("                  English Translation: Left");
		}
	}
	
	private class DexBListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			translationLbl.setText("                  English Translation: Right");
		}
	}
	
	private class MedBListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			translationLbl.setText("                  English Translation: Center");
		}
	}
	
	public static void main(String[] args){
		new AWLatinTranslator();
	}
	
}
