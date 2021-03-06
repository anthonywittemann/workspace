import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class CookPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTextArea cookTA;
	
	public CookPanel(){
		this.setPreferredSize(new Dimension(300,500));
		this.cookTA = new JTextArea("", 300, 500);
		this.scrollPane = new JScrollPane(this.cookTA);
		this.add(this.scrollPane);
	}

	public void addCookMessage(String string) {
		cookTA.setText(cookTA.getText() + "\n" + string);
		//System.out.println("Message Added");
	}

}
