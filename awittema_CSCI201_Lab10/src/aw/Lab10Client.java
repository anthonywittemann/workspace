package aw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class Lab10Client extends JFrame implements Runnable{

	private PrintWriter pw;
	private BufferedReader br;
	private AttackThread attackThread;
	
	private JLabel p1HealthLbl;
	private JLabel p2HealthLbl;
	private JButton swordB;
	private JButton magicB;
	
	//TODO figure out a way to call attack method on other threads, but not on own
	//figure out which thread the client maps to
	
//	public Lab10Client(String hostname, int port, AttackThread attackThread){
	public Lab10Client(String hostname, int port){
		super("FF7");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200,200);
		setLayout(new GridBagLayout());
		
		/*** Initialize components *** Initialize components ***  Initialize components *** Initialize components ***/
		p1HealthLbl = new JLabel("10/10 Health");
		p2HealthLbl = new JLabel("Waiting for other player");
		swordB = new JButton("Sword");
		swordB.setEnabled(false);
		magicB = new JButton("Magic");
		magicB.setEnabled(false);
		
		
		
		swordB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//attackThread.attackOther(true);
				// TODO decrement other players health
				//update JLabel
				//wait 3 seconds until either button can be used again
				
			}
			
		});
		
		magicB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//attackThread.attackOther(false);
				// TODO decrement other players health
				//update JLabel
				//wait 3 seconds until either button can be used again
				
			}
			
		});
		
		
		
		/*** adding components to GUI *** adding components to GUI *** adding components to GUI *** adding components to GUI ***/
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0; gbc.ipady = 10;
		add(p1HealthLbl, gbc);
		gbc.gridy = 1;
		add(p2HealthLbl, gbc);
		gbc.gridy = 2;
		add(swordB, gbc);
		gbc.gridy = 3;
		add(magicB, gbc);
		
		
		pack();
		setVisible(true);
		
		
		try {
			Socket s = new Socket(hostname, port);
			this.pw = new PrintWriter(s.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String answer = this.br.readLine();
			System.out.println(answer);
//			while(true){
//				String line = scan.nextLine(); //TODO get this input from button and then pass it on to server
//				pw.println(line);
//				pw.flush(); //FLUSH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void run(){
		try {
			while(true){
				//TODO modify to listen to buttons
				String line = br.readLine();
				System.out.println(line);
			}
		} catch (IOException ioe) {
			System.out.println("IOException in ChatClient run method: " + ioe.getMessage());
		}
	}

	public static void main(String[] args) {
		Lab10Client l10c = new Lab10Client("localhost", 6789);
		Thread t = new Thread(l10c);
		t.start();

	}

}
