package Lab6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Anthony Wittemann
//CSCI 201L Dr. Miller Lab W 10:00 am 
//Lab 6 GUI TIC-TAC-TOE


public class Lab6Main extends JFrame{
	
	/*** GUI COMPONENTS ****** GUI COMPONENTS ****** GUI COMPONENTS ****** GUI COMPONENTS ***/
	private JLabel currentPlayerLabel;
	
	private JButton topLeftButton;
	private JButton topCenterButton;
	private JButton topRightButton;
	private JButton middleLeftButton;
	private JButton middleCenterButton;
	private JButton middleRightButton;
	private JButton bottomLeftButton;
	private JButton bottomCenterButton;
	private JButton bottomRightButton;
	
	private JButton restartGameButton;
	
	private JLabel topLeftLabel;
	private JLabel topCenterLabel;
	private JLabel topRightLabel;
	private JLabel middleLeftLabel;
	private JLabel middleCenterLabel;
	private JLabel middleRightLabel;
	private JLabel bottomLeftLabel;
	private JLabel bottomCenterLabel;
	private JLabel bottomRightLabel; 
	
	private JPanel topLeftPanel;
	private JPanel topCenterPanel;
	private JPanel topRightPanel;
	private JPanel middleLeftPanel;
	private JPanel middleCenterPanel;
	private JPanel middleRightPanel;
	private JPanel bottomLeftPanel;
	private JPanel bottomCenterPanel;
	private JPanel bottomRightPanel; 
	
	JPanel boardPanel;
	
	GridBagConstraints gbc;
	
	/*** booleans to keep track of game state *** *** booleans to keep track of game state ***/
	private boolean isPlayer1Turn;
	private boolean allSquaresAreFilled; //TODO check for this case
	private boolean hasTicTacToe; //TODO check for this case
	private short numberOfMoves;
	
	public Lab6Main(){
		super("Tic-Tac-Toe");
		setSize(400,400);
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		//initialize boolean variables
		isPlayer1Turn = true;
		allSquaresAreFilled = false;
		hasTicTacToe = false;
		
		createGameBoard();
		
		
		
		currentPlayerLabel = new JLabel("Current Player: Player 1");
		restartGameButton = new JButton("Restart Game");
		restartGameButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				isPlayer1Turn = true;
				allSquaresAreFilled = false;
				hasTicTacToe = false;
				numberOfMoves = 0;
				boardPanel.removeAll();
				createGameBoard();
				currentPlayerLabel.setText("Current Player: Player 1");
				boardPanel.revalidate();
				
				
			}
			
		});
		
		//add top label, bottom button and main board
		add(currentPlayerLabel, BorderLayout.NORTH);
		//add(boardPanel, BorderLayout.CENTER);
		add(boardPanel);
		add(restartGameButton, BorderLayout.SOUTH);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	public void createGameBoard(){
		//top left button and panel
		topLeftPanel = new JPanel();  //create local JPanel
		topLeftLabel = new JLabel(); //create label, disable and invisible
		topLeftLabel.setVisible(false);
		topLeftLabel.setEnabled(false);
		topLeftButton = new JButton(" "); //create button
		topLeftButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(isPlayer1Turn){
					topLeftLabel.setText("X");		//mark X or O in Label & setVisible to true
				}
				else{
					topLeftLabel.setText("O");
				}
				gbc.fill = GridBagConstraints.BOTH;
				topLeftLabel.setVisible(true);
				topLeftLabel.setEnabled(true);
				topLeftPanel.remove(topLeftButton);		//remove the button
										
				updateWhosTurnItIs();					//other player's turn
			}			
		});
		gbc.fill = GridBagConstraints.BOTH;
		topLeftPanel.add(topLeftLabel, gbc);
		topLeftPanel.add(topLeftButton, gbc);
		topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		//top center button and panel
		topCenterPanel = new JPanel(); //create local JPanel
		topCenterLabel = new JLabel(); //create label, disable and invisible
		topCenterLabel.setVisible(false);
		topCenterLabel.setEnabled(false);
		topCenterButton = new JButton(" "); //create button
		topCenterButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(isPlayer1Turn){
					topCenterLabel.setText("X");		//mark X or O in Label & setVisible to true
				}
				else{
					topCenterLabel.setText("O");
				}
				gbc.fill = GridBagConstraints.BOTH;
				topCenterLabel.setVisible(true);
				topCenterLabel.setEnabled(true);
				topCenterPanel.remove(topCenterButton);		//remove the button
										
				updateWhosTurnItIs();					//other player's turn
			}			
		});
		gbc.fill = GridBagConstraints.BOTH;
		topCenterPanel.add(topCenterLabel, gbc);
		topCenterPanel.add(topCenterButton, gbc);
		topCenterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//top right button and panel
		topRightPanel = new JPanel(); //create local JPanel
		topRightLabel = new JLabel(); //create label, disable and invisible
		topRightLabel.setVisible(false);
		topRightLabel.setEnabled(false);
		topRightButton = new JButton(" "); //create button
		topRightButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(isPlayer1Turn){
					topRightLabel.setText("X");		//mark X or O in Label & setVisible to true
				}
				else{
					topRightLabel.setText("O");
				}
				gbc.fill = GridBagConstraints.BOTH;
				topRightLabel.setVisible(true);
				topRightLabel.setEnabled(true);
				topRightPanel.remove(topRightButton);		//remove the button
										
				updateWhosTurnItIs();					//other player's turn
			}			
		});
		gbc.fill = GridBagConstraints.BOTH;
		topRightPanel.add(topRightLabel, gbc);
		topRightPanel.add(topRightButton, gbc);
		topRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		//middle left button and panel
		middleLeftPanel = new JPanel(); //create local JPanel
		middleLeftLabel = new JLabel(); //create label, disable and invisible
		middleLeftLabel.setVisible(false);
		middleLeftLabel.setEnabled(false);
		middleLeftButton = new JButton(" "); //create button
		middleLeftButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(isPlayer1Turn){
					middleLeftLabel.setText("X");		//mark X or O in Label & setVisible to true
				}
				else{
					middleLeftLabel.setText("O");
				}
				gbc.fill = GridBagConstraints.BOTH;
				middleLeftLabel.setVisible(true);
				middleLeftLabel.setEnabled(true);
				middleLeftPanel.remove(middleLeftButton);		//remove the button
										
				updateWhosTurnItIs();					//other player's turn
			}			
		});
		gbc.fill = GridBagConstraints.BOTH;
		middleLeftPanel.add(middleLeftLabel, gbc);
		middleLeftPanel.add(middleLeftButton, gbc);
		middleLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				
				//middle Center button and panel
				middleCenterPanel = new JPanel(); //create local JPanel
				middleCenterLabel = new JLabel(); //create label, disable and invisible
				middleCenterLabel.setVisible(false);
				middleCenterLabel.setEnabled(false);
				middleCenterButton = new JButton(" "); //create button
				middleCenterButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent ae) {
						if(isPlayer1Turn){
							middleCenterLabel.setText("X");		//mark X or O in Label & setVisible to true
						}
						else{
							middleCenterLabel.setText("O");
						}
						gbc.fill = GridBagConstraints.BOTH;
						middleCenterLabel.setVisible(true);
						middleCenterLabel.setEnabled(true);
						middleCenterPanel.remove(middleCenterButton);		//remove the button
												
						updateWhosTurnItIs();					//other player's turn
					}			
				});
				gbc.fill = GridBagConstraints.BOTH;
				middleCenterPanel.add(middleCenterLabel, gbc);
				middleCenterPanel.add(middleCenterButton, gbc);
				middleCenterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				
				//middle right button and panel
				middleRightPanel = new JPanel(); //create local JPanel
				middleRightLabel = new JLabel(); //create label, disable and invisible
				middleRightLabel.setVisible(false);
				middleRightLabel.setEnabled(false);
				middleRightButton = new JButton(" "); //create button
				middleRightButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent ae) {
						if(isPlayer1Turn){
							middleRightLabel.setText("X");		//mark X or O in Label & setVisible to true
						}
						else{
							middleRightLabel.setText("O");
						}
						gbc.fill = GridBagConstraints.BOTH;
						middleRightLabel.setVisible(true);
						middleRightLabel.setEnabled(true);
						middleRightPanel.remove(middleRightButton);		//remove the button
												
						updateWhosTurnItIs();					//other player's turn
					}			
				});
				gbc.fill = GridBagConstraints.BOTH;
				middleRightPanel.add(middleRightLabel, gbc);
				middleRightPanel.add(middleRightButton, gbc);
				middleRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				//bottom Left button and panel
				bottomLeftPanel = new JPanel(); //create local JPanel
				bottomLeftLabel = new JLabel(); //create label, disable and invisible
				bottomLeftLabel.setVisible(false);
				bottomLeftLabel.setEnabled(false);
				bottomLeftButton = new JButton(" "); //create button
				bottomLeftButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent ae) {
						if(isPlayer1Turn){
							bottomLeftLabel.setText("X");		//mark X or O in Label & setVisible to true
						}
						else{
							bottomLeftLabel.setText("O");
						}
						gbc.fill = GridBagConstraints.BOTH;
						bottomLeftLabel.setVisible(true);
						bottomLeftLabel.setEnabled(true);
						bottomLeftPanel.remove(bottomLeftButton);		//remove the button
												
						updateWhosTurnItIs();					//other player's turn
					}			
				});
				gbc.fill = GridBagConstraints.BOTH;
				bottomLeftPanel.add(bottomLeftLabel, gbc);
				bottomLeftPanel.add(bottomLeftButton, gbc);
				bottomLeftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				
				//bottom Center button and panel
				bottomCenterPanel = new JPanel(); //create local JPanel
				bottomCenterLabel = new JLabel(); //create label, disable and invisible
				bottomCenterLabel.setVisible(false);
				bottomCenterLabel.setEnabled(false);
				bottomCenterButton = new JButton(" "); //create button
				bottomCenterButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent ae) {
						if(isPlayer1Turn){
							bottomCenterLabel.setText("X");		//mark X or O in Label & setVisible to true
						}
						else{
							bottomCenterLabel.setText("O");
						}
						gbc.fill = GridBagConstraints.BOTH;
						bottomCenterLabel.setVisible(true);
						bottomCenterLabel.setEnabled(true);
						bottomCenterPanel.remove(bottomCenterButton);		//remove the button
												
						updateWhosTurnItIs();					//other player's turn
					}			
				});
				gbc.fill = GridBagConstraints.BOTH;
				bottomCenterPanel.add(bottomCenterLabel, gbc);
				bottomCenterPanel.add(bottomCenterButton, gbc);
				bottomCenterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				//bottom right button and panel
				bottomRightPanel = new JPanel(); //create local JPanel
				bottomRightLabel = new JLabel(); //create label, disable and invisible
				bottomRightLabel.setVisible(false);
				bottomRightLabel.setEnabled(false);
				bottomRightButton = new JButton(" "); //create button
				bottomRightButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent ae) {
						if(isPlayer1Turn){
							bottomRightLabel.setText("X");		//mark X or O in Label & setVisible to true
						}
						else{
							bottomRightLabel.setText("O");
						}
						gbc.fill = GridBagConstraints.BOTH;
						bottomRightLabel.setVisible(true);
						bottomRightLabel.setEnabled(true);
						bottomRightPanel.remove(bottomRightButton);		//remove the button
												
						updateWhosTurnItIs();					//other player's turn
					}			
				});
				gbc.fill = GridBagConstraints.BOTH;
				bottomRightPanel.add(bottomRightLabel, gbc);
				bottomRightPanel.add(bottomRightButton, gbc);
				bottomRightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		boardPanel.add(topLeftPanel, gbc);
		gbc.gridx = 1;
		boardPanel.add(topCenterPanel, gbc);
		gbc.gridx = 2;
		boardPanel.add(topRightPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		boardPanel.add(middleLeftPanel, gbc);
		gbc.gridx = 1;
		boardPanel.add(middleCenterPanel, gbc);
		gbc.gridx = 2;
		boardPanel.add(middleRightPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		boardPanel.add(bottomLeftPanel, gbc);
		gbc.gridx = 1;
		boardPanel.add(bottomCenterPanel, gbc);
		gbc.gridx = 2;
		boardPanel.add(bottomRightPanel, gbc);
	}

	private void updateWhosTurnItIs(){
		if(allSquaresAreFilled || hasTicTacToe){
			
		}
		else{
			numberOfMoves++;
			isPlayer1Turn = !isPlayer1Turn; //switch who's turn it is
			//check to see if there's a tic tac toe
			System.out.println("DEBUG: Top Left: " + topLeftLabel.getText());
			if(topLeftLabel.getText().equalsIgnoreCase("X")){
				System.out.println("DEBUG: Top Left == X? TRUE");
			}
			else{
				System.out.println("DEBUG: Top Left == X? False");
			}
			
			//check to see if top row is all Xs
			if(topLeftLabel.getText().equalsIgnoreCase("X") && topCenterLabel.getText().equalsIgnoreCase("X") && topRightLabel.getText().equalsIgnoreCase("X")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 1 Wins!");
				System.out.println("DEBUG: Player 1 WINS !!!");
			}

			//check to see if middle row is all Xs
			if(middleLeftLabel.getText().equalsIgnoreCase("X") && middleCenterLabel.getText().equalsIgnoreCase("X") && middleRightLabel.getText().equalsIgnoreCase("X")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 1 Wins!");
			}

			//check to see if bottom row is all Xs
			if(bottomLeftLabel.getText().equalsIgnoreCase("X") && bottomCenterLabel.getText().equalsIgnoreCase("X") && bottomRightLabel.getText().equalsIgnoreCase("X")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 1 Wins!");
			}

			//check to see if left column is all Xs
			if(topLeftLabel.getText().equalsIgnoreCase("X") && middleLeftLabel.getText().equalsIgnoreCase("X") && bottomLeftLabel.getText().equalsIgnoreCase("X")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 1 Wins!");
			}

			//check to see if middle column is all Xs
			if(topCenterLabel.getText().equalsIgnoreCase("X") && middleCenterLabel.getText().equalsIgnoreCase("X") && bottomCenterLabel.getText().equalsIgnoreCase("X")){
				currentPlayerLabel.setText("Player 1 Wins!");
			}

			//check to see if right column is all Xs
			if(topRightLabel.getText().equalsIgnoreCase("X") && middleRightLabel.getText().equalsIgnoreCase("X") && bottomRightLabel.getText().equalsIgnoreCase("X")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 1 Wins!");
			}

			//check if l-->r, t-->b diagonal is all Xs
			if(topLeftLabel.getText().equalsIgnoreCase("X") && middleCenterLabel.getText().equalsIgnoreCase("X") && bottomRightLabel.getText().equalsIgnoreCase("X")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 1 Wins!");
			}

			//check if l-->r, b-->t diagonal is all Xs
			if(topRightLabel.getText().equalsIgnoreCase("X") && middleCenterLabel.getText().equalsIgnoreCase("X") && bottomLeftLabel.getText().equalsIgnoreCase("X")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 1 Wins!");
			}



			//check to see if top row is all Os
			if(topLeftLabel.getText().equalsIgnoreCase("O") && topCenterLabel.getText().equalsIgnoreCase("O") && topRightLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}

			//check to see if middle row is all Os
			if(middleLeftLabel.getText().equalsIgnoreCase("O") && middleCenterLabel.getText().equalsIgnoreCase("O") && middleRightLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}

			//check to see if bottom row is all Os
			if(bottomLeftLabel.getText().equalsIgnoreCase("O") && bottomCenterLabel.getText().equalsIgnoreCase("O") && bottomRightLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}

			//check to see if left column is all Os
			if(topLeftLabel.getText().equalsIgnoreCase("O") && middleLeftLabel.getText().equalsIgnoreCase("O") && bottomLeftLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}

			//check to see if middle column is all Os
			if(topCenterLabel.getText().equalsIgnoreCase("O") && middleCenterLabel.getText().equalsIgnoreCase("O") && bottomCenterLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}

			//check to see if right column is all Os
			if(topRightLabel.getText().equalsIgnoreCase("O") && middleRightLabel.getText().equalsIgnoreCase("O") && bottomRightLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}

			//check if l-->r, t-->b diagonal is all Os
			if(topLeftLabel.getText().equalsIgnoreCase("O") && middleCenterLabel.getText().equalsIgnoreCase("O") && bottomRightLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}

			//check if l-->r, b-->t diagonal is all Os
			if(topRightLabel.getText().equalsIgnoreCase("O") && middleCenterLabel.getText().equalsIgnoreCase("O") && bottomLeftLabel.getText().equalsIgnoreCase("O")){
				hasTicTacToe = true;
				currentPlayerLabel.setText("Player 2 Wins!");
			}
			
				
			if(numberOfMoves == 9 && !hasTicTacToe){
				allSquaresAreFilled = true;
				currentPlayerLabel.setText("Tie Game!");
				System.out.println("DEBUG: TIE!!!!!");
			}
			else if(hasTicTacToe){

			}
			else{
				if(isPlayer1Turn){
					currentPlayerLabel.setText("Current Player: Player 1");
				}
				else{
					currentPlayerLabel.setText("Current Player: Player 2");
				}
			}
			
			
			
			boardPanel.revalidate();
			repaint();
		}
	}
	
	
	public static void main(String[] args) {
		new Lab6Main();
	}

}
