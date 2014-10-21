package Lab7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//TODO step 3 (Swapping buttons)

public class Lab7MainFrame extends JFrame{
	
	private JButton clickOnceButton;
	private JPanel mainPanel;
	private CustomPanel cPanel;
	private short clickCount;
	
	public Lab7MainFrame(){
		setSize(500,500);
		setResizable(false);
		clickOnceButton = new JButton("Click Once");
		mainPanel = new JPanel();
		cPanel = new CustomPanel();
		cPanel.add(clickOnceButton);
		clickCount = 0;
		
		clickOnceButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println("DEBUG: click count before: " + clickCount);
				
				if(clickCount == 0){
					//clickOnceButton.hide();
					clickOnceButton.setVisible(false);
					cPanel.remove(clickOnceButton);
					clickOnceButton = new CustomButton();
					clickOnceButton.setText("Don't Click Me");
//					clickOnceButton.enable();
					clickOnceButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null, "WHY DID YOU CLICK ME AGAIN?");
							System.exit(0);
							
						}
						
					});
					cPanel.add(clickOnceButton);
					//clickOnceButton.show();
					clickCount++;
				}
				else{
					JOptionPane.showOptionDialog(null, "WHY DID YOU CLICK ME AGAIN?", 
							"Message", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, 
							null, null, null);
					System.exit(0);
				}
				
				System.out.println("DEBUG: click count after: " + clickCount);
			}
		});
		
//		mainPanel.add(clickOnceButton);
//		add(mainPanel);
		
		add(cPanel);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	class CustomButton extends JButton{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.drawLine(0, 0, this.getWidth(), this.getHeight());
			g.drawLine(this.getWidth(), 0, 0, this.getHeight());
		}
	}
	
	
	class CustomPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponents(g);
			int[] x = {10, 20, 25, 25, 20, 10, 5, 5};
			int[] y = {10, 10, 20, 27, 37, 37, 27, 20};
			//get the center of the JButton and then subtract the scaled "radius" of the polygon
			int halfWay = 190; 
			for(int i = 0; i < x.length; i++){
				x[i] *= 4; //scale by 4;
				x[i] += halfWay; //center under JButton
				y[i] *= 4;
				y[i] += 20;
			}
			g.drawPolygon(x, y, x.length);
			g.setColor(Color.CYAN);
			g.fillPolygon(x, y, x.length);
		}
	}


	public static void main(String[] args) {
		new Lab7MainFrame();

	}

}
