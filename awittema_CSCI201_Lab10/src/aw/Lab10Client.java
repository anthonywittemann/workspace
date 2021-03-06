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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Lab10Client extends JFrame implements ActionListener, Runnable {
	private int myHealth = 10;
	private JLabel myHealthLbl;
	private int otherHealth = 10;
	private JButton swordB;
	private JButton magicB;
	private JLabel otherHealthLbl;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	private int id;
	private int port;
	
	Lab10Client(String title, int port) {
		super("FF7");
		this.port = port;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250,200);
		setLayout(new GridBagLayout());
		
		/*** Initialize components *** Initialize components ***  Initialize components *** Initialize components ***/
		myHealthLbl = new JLabel("10/10 Health");
		otherHealthLbl = new JLabel("Waiting for other player...");
		swordB = new JButton("Sword");
		swordB.setEnabled(false);
		swordB.setActionCommand("sword");
		swordB.addActionListener(this);
		
		magicB = new JButton("Magic");
		magicB.setEnabled(false);
		magicB.setActionCommand("magic");	
		magicB.addActionListener(this);
      
		
		/*** adding components to GUI *** adding components to GUI *** adding components to GUI *** adding components to GUI ***/
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0; gbc.ipady = 10;
		add(myHealthLbl, gbc);
		gbc.gridy = 1;
		add(otherHealthLbl, gbc);
		gbc.gridy = 2;
		add(swordB, gbc);
		gbc.gridy = 3;
		add(magicB, gbc);
		
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
        magicB.setEnabled(false);
        swordB.setEnabled(false);
        this.repaint();
        try {
            int damage = 0;
            if (ae.getActionCommand().equals("sword")) {
                damage = 2;
            }
            else if (ae.getActionCommand().equals("magic")) {
                Random r = new Random();
                damage = r.nextInt(5) + 1;
            }
            this.otherHealth -= damage;
            this.otherHealthLbl.setText("Other Health " + this.otherHealth + "/10 Health");
            pw.println(damage);
            if (this.otherHealth <= 0) {
                JOptionPane.showMessageDialog(null, "WINNER!!!", "Game over", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Thread.currentThread().sleep(3000);
            magicB.setEnabled(true);
            swordB.setEnabled(true);

        } catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	@Override
	public void run() {
        try{
            socket = new Socket("localhost", port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            while(this.myHealth > 0) {
                String attack = br.readLine();
                int damage = Integer.parseInt(attack);
                //if it's not the first run
                if (damage != -99) {
                	this.myHealth -= damage;
                	if (this.myHealth <= 0) {
                		magicB.setEnabled(false);
                		swordB.setEnabled(false);
                		JOptionPane.showMessageDialog(null, "LOSER!", "Game over", JOptionPane.INFORMATION_MESSAGE);
                		return;
                	}
                }
                this.otherHealthLbl.setText("Other Health: " + this.otherHealth + "/10 Health");
                this.myHealthLbl.setText("My Health: " + this.myHealth + "/10 Health");
                //when the method is originally called before game starts
                if (damage == -99) {
                    Thread.currentThread().sleep(3000);
                    magicB.setEnabled(true);
                    swordB.setEnabled(true);
                }
            }
        }
        catch(InterruptedException ie) {
        	ie.printStackTrace();
        } catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		int port = 9876;//port should be above 1023 for non Admin/root
        Lab10Client c = new Lab10Client("FF7", port);
        new Thread(c).start();
	}



}
