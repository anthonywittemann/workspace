package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class ChatServer {
	//VECTOR WHEN MULTITHREADING, ARRAYLIST WHEN SINGLE THREAD
	private Vector<ChatThread> ctVector = new Vector<ChatThread>();
	//TODO remove clients from vector when disconnected
	
	
	public ChatServer(int port){
		try {
			ServerSocket ss = new ServerSocket(port);
			while(true){
				Socket s = ss.accept();
				ChatThread ct = new ChatThread(s, this);
				ctVector.add(ct);
				ct.start();
			}
			
		} catch (IOException ioe) {
			System.out.println("IOE: " + ioe.getMessage());
		}
	}
	
	//sends message out to all other clients
	public void sendMessage(String message, ChatThread ct){
		for(ChatThread c: ctVector){
			if(!c.equals(ct)){
				c.send(message);
			}
		}
	}
	
	
	public static void main(String[] args){
		Scanner scan  = new Scanner(System.in);
		System.out.print("What port? "); //port should be above 1023 for non Admin/root
		int port = scan.nextInt();
		new ChatServer(port);
	}

}
