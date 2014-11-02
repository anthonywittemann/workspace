package aw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class AttackThread extends Thread{
	private Socket s;
	private Lab10Server ls;
	private PrintWriter pw;
	private boolean isSword;
	private short pHealth;
	
	public AttackThread(Socket s, Lab10Server ls){
		this.s = s;
		this.ls = ls;
		isSword = true;
		pHealth = 10;
		try {
			this.pw = new PrintWriter(s.getOutputStream());
		} catch (IOException ioe) {
			System.out.println("IOE: " + ioe.getMessage());
		}
		
	}
	
	
	public void run(){
		// a client has connected to our server
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line = br.readLine();
			while(true){
				//TODO change line to sword or magic
				ls.attack(line, this);
				line = br.readLine(); //blocking line (waits until it receives input, makes sure there's no infinite loop, stackoverflow
			}

		} catch (IOException ioe) {
			System.out.println("IOE: " + ioe.getMessage());
			//thrown when client disconnected 
			//TODO call method in Lab10Server class to remove instance of that thread from vector
		}
	}
	
	public boolean isSword(){
		return isSword;
	}
	
	//called if this thread is being attacked
	public void attackMe(boolean isSword){ //true if attacking with sword, false if with magic//
		if(isSword){
			pw.println("Sword attack!!!"); 
			pHealth --;
			if(pHealth == 0){
				//TODO JDialogBox Popup, End Game (System.exit(0))
				ls.gameOver(this);
			}
		}
		else{
			pw.println("Magic attack!!!"); 
			pHealth --;
			if(pHealth == 0){
				//TODO JDialogBox Popup, End Game (System.exit(0))
				ls.gameOver(this);
			}
		}
		
	}
	
	//called if this thread is doing that attacking
	public void attackOther(boolean isSword){
		
	}
	
	
	
}
