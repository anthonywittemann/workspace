package aw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Lab10Server extends Thread{
	 private int count = 0;
	    private ListenThread[] connections = new ListenThread[2];
	    private int port;
	    
	    public Lab10Server(int port){
	    	this.port = port;
	    }
	    
		/*** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF *** DO SERVER STUFF ***/ 
	    public void run() {
	        ServerSocket serverSocket = null;
	        Socket socket = null;

	        try {
	            serverSocket = new ServerSocket(port);
	            while (count < 2) {
	                socket = serverSocket.accept();
	                connections[count] = new ListenThread(socket, count);
	                connections[count].start();
	                count++;
	            }
	            //wait for 3 seconds by sending a -99 special code
	            for (ListenThread lt: connections) {
	                lt.pw.println(-99);
	                lt.pw.flush();
	            }
	        } catch (IOException e) {
	            System.err.println("Could not listen on port: " + port);
	            System.exit(1);
	        }
	    }

	    

	    private class ListenThread extends Thread {
	        public PrintWriter pw;
	        public BufferedReader br;
	        public Socket socket;
	        public int playerNum;
	        
	        ListenThread(Socket socket, int id) {
	            this.socket = socket;
	            this.playerNum = id;
	            try {
	                pw = new PrintWriter(socket.getOutputStream(), true);
	                pw.flush();
	                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }

	        @Override
	        public void run() {
	            System.out.println("Created listener thread.");
	            try {
	                while (true) {
	                    String damageMessage = br.readLine(); //blocking line (waits until it receives input, makes sure there's no infinite loop, stackoverflow
	                    System.out.println("Damage: " + damageMessage);
	                    int damage = Integer.parseInt(damageMessage);
	                    connections[1 - playerNum].pw.println(damage);
	                    connections[1 - playerNum].pw.flush();
	                }
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
	    }

	    
	    public static void main (String [] args) {
	    	int port = 9876;//port should be above 1023 for non Admin/root
	        Lab10Server server = new Lab10Server(port);
	        server.start();
	    }
	


}
