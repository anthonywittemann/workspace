package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread extends Thread{
	private Socket s;
	private ChatServer cs;
	private PrintWriter pw;
	
	public ChatThread(Socket s, ChatServer cs){
		this.s = s;
		this.cs = cs;
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
				cs.sendMessage(line, this);
				line = br.readLine(); //blocking line (waits until it receives input, makes sure there's no infinite loop, stackoverflow
			}
			
		} catch (IOException ioe) {
			System.out.println("IOE: " + ioe.getMessage());
			//thrown when client disconnected 
			//TODO call method in chatserver class to remove instance of that thread from vector
		}
	}
	
	public void send(String message){
		pw.println(message);
		pw.flush(); //FLUSH!!!!!!!!!!!!!!!!!!!!!!!!!!
	}


}
