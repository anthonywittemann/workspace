package aw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class Lab10Server{
	//VECTOR WHEN MULTITHREADING, ARRAYLIST WHEN SINGLE THREAD
	private Vector<AttackThread> atVector = new Vector<AttackThread>();
	
	//TODO should only be 2 clients at once
	
	
	
	
	public Lab10Server(int port){
		
		
		/*** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF ***/ 
		try {
			ServerSocket ss = new ServerSocket(port);
			while(true){
				if(atVector.size() < 2){
					Socket s = ss.accept();
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					out.print("Test**************************");
					AttackThread at = new AttackThread(s, this);
					atVector.add(at);
					at.start();
					
					if(atVector.size() == 2){
						//TODO change JLabels on both to reflect that they're now connected
						//Don't let any more connect
					}
				}
				else{
					//TODO wait for threads to attack
				}
			}
			
		} catch (IOException ioe) {
			System.out.println("IOE: " + ioe.getMessage());
		}
	}

	//sends message out to all other clients, should only need to send to one other client
	public void attack(String message, AttackThread at){
		for(AttackThread a: atVector){
			if(!a.equals(at)){
				a.attackMe(at.isSword());
			}
		}
	}
	
	public void gameOver(AttackThread at){
		if(at.equals(atVector.get(0))){
			//TODO JDialogBox saying Player 0 wins to player 1 and player 0
		}
		else{
			//TODO JDialogBox saying Player 1 wins to player 1 and player 0
		}
	}
	
	
	public static void main(String[] args){
		int port = 6789;//port should be above 1023 for non Admin/root
		new Lab10Server(port);
	}

}