package org.Final_Project.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.Final_Project.Deck.UNOCard;
import org.Final_Project.Players.UNOPlayer;

@SuppressWarnings("serial")

public class UNOGUI extends JFrame{

	private JButton playButton;
	private JButton instructionButton;
	private JButton exitButton;
	private ArrayList<UNOPlayer> players;
	private UNO game;
	
	public UNOGUI() {
		
	}
	
	public UNOGUI(String title) {
		
		super(title);    //Creating the JFrame with desired parameters
		setSize(800,800);
		setLayout(new GridLayout(2,1));
		getContentPane().setBackground(Color.BLACK);
		
		//Creating a label for the window tol hold the UNO Main Menu Picture
		JLabel label = new JLabel(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\UNOMainScreen.png").getImage().getScaledInstance(800,400,Image.SCALE_SMOOTH)), JLabel.CENTER);
		add(label);
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		// Creating the list to hold all the players that will play UNO
		players = new ArrayList<UNOPlayer>();
		
		playButton = new JButton("Play"); //Play button to start the UNO game
		playButton.setBackground(Color.GREEN);
		playButton.setFont(new Font("Serif", Font.BOLD, 24));
		
		instructionButton = new JButton("Instructions"); // Instructions button that will open a window that will show all the instructions
		instructionButton.setBackground(Color.BLUE);
		instructionButton.setFont(new Font("Serif", Font.BOLD, 24));
		
		exitButton = new JButton("Exit");  // Exit button will close the UNO GUI window
		exitButton.setBackground(Color.RED);
		exitButton.setFont(new Font("Serif", Font.BOLD, 24));
		
		// Adding the buttons to the panel
		panel.add(playButton);
		panel.add(instructionButton);
		panel.add(exitButton);
		
		add(panel); // adding panel to JFrame
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Positioning the JFrame and opening the window
		setVisible(true);
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
				askForAmountOfPlayersWindow();
			}
		});
		
		instructionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructionsWindow();
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
	}
		
	public void askForAmountOfPlayersWindow() {
		JFrame window = new JFrame("How many players are there?");
		window.setSize(1000, 1000);
		window.setLayout(new GridLayout(3,1));
		window.getContentPane().setBackground(Color.YELLOW);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JLabel pictureLabel = new JLabel(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\UNOGamePicture.png").getImage().getScaledInstance(1000,400,Image.SCALE_SMOOTH)), JLabel.CENTER);
		JLabel messageLabel = new JLabel("<HTML><center>UNO" +
				"<BR>Select how many players will play. There can be either 2, 3, or 4 players.", JLabel.CENTER);
		messageLabel.setFont(new Font("Serif", Font.ITALIC, 38));
		window.add(pictureLabel);
		window.add(messageLabel);
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout());
		
		JButton twoPlayerButton = new JButton("Two Players");
		twoPlayerButton.setBackground(Color.RED);
		twoPlayerButton.setFont(new Font("Serif", Font.ITALIC, 24));
		JButton threePlayerButton = new JButton("Three Players");
		threePlayerButton.setBackground(Color.GREEN);
		threePlayerButton.setFont(new Font("Serif", Font.ITALIC, 24));
		JButton fourPlayerButton = new JButton("Four Players"); 
		fourPlayerButton.setBackground(Color.BLUE);
		fourPlayerButton.setFont(new Font("Serif", Font.ITALIC, 24));
	
		// Adding the buttons to the panel
		panel.add(twoPlayerButton);
		panel.add(threePlayerButton);
		panel.add(fourPlayerButton);
		
		window.add(panel); // adding panel to JFrame
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // Positioning the JFrame and opening the window
		window.setVisible(true);
		
		twoPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterPlayerNamesWindow(2);
				window.setVisible(false);
				dispose();
			}
		});
		
		threePlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterPlayerNamesWindow(3);
				window.setVisible(false);
				dispose();
			}
		});
		
		fourPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterPlayerNamesWindow(4);
				window.setVisible(false);
				dispose();
			}
		});
	}
	
	public void enterPlayerNamesWindow(int amountOfPlayers) {
		JFrame window = new JFrame("Enter Players Names");
		window.setSize(600, 300);
		window.setLayout(new GridLayout(3,1));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JLabel player1MessageLabel = new JLabel("Player One's Name:");
		JTextField player1NameField = new JTextField(10);
		JLabel player2MessageLabel = new JLabel("Player Two's Name:");
		JTextField player2NameField = new JTextField(10);
		JLabel player3MessageLabel = new JLabel("Player Three's Name:");
		JTextField player3NameField = new JTextField(10);
		JLabel player4MessageLabel = new JLabel("Player Four's Name:");
		JTextField player4NameField = new JTextField(10);
		
		// Creating button
		JButton okButton = new JButton("Ok");
		
		//Creating panel to add all objects to
		JPanel panel = new JPanel(new GridLayout(4,2));
		JPanel panel2 = new JPanel();
		
		// Creating players Name fields
		if (amountOfPlayers == 2) {
			panel.add(player1MessageLabel);
			panel.add(player2NameField);
			panel.add(player2MessageLabel);
			panel.add(player1NameField);
		}
		if (amountOfPlayers == 3) {
			panel.add(player1MessageLabel);
			panel.add(player1NameField);
			panel.add(player2MessageLabel);
			panel.add(player2NameField);
			panel.add(player3MessageLabel);
			panel.add(player3NameField);
		}
		if (amountOfPlayers == 4) {
			panel.add(player1MessageLabel);
			panel.add(player1NameField);
			panel.add(player2MessageLabel);
			panel.add(player2NameField);
			panel.add(player3MessageLabel);
			panel.add(player3NameField);
			panel.add(player4MessageLabel);
			panel.add(player4NameField);
		}

		panel2.add(okButton);
		
		//Adding panel of objects to the JFrame window
		window.add(panel);
		window.add(panel2);
		window.setVisible(true); 
		
		//Event handler for OK button
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < amountOfPlayers; i++) {
				UNOPlayer newPlayer = new UNOPlayer();
				if (i == 0) {
					newPlayer.setName(player1NameField.getText());
				}
				if (i == 1) {
					newPlayer.setName(player2NameField.getText());
				}
				if (i == 2) {
					newPlayer.setName(player3NameField.getText());
				}
				if (i == 3) {
					newPlayer.setName(player4NameField.getText());
				}
				players.add(newPlayer);
			}
			window.setVisible(false);
			dispose();
			
			playUNO(); //START THE GAME!!!!!
				}
		});
		
	}
	
	public void instructionsWindow() {
		JFrame window = new JFrame("UNO Instructions");
		window.setSize(1500, 1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JTextArea textArea = new JTextArea(50,50);
		textArea.setFont(new Font("Serif", Font.BOLD, 24));
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.RED);
		
			FileReader reader;
			try {
				reader = new FileReader("UNOInstructions.txt");
				try {
					textArea.read(reader, null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			
	
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		JButton okButton = new JButton("OK");
		JPanel panel = new JPanel();
		
		panel.add(okButton);
		window.add(panel, BorderLayout.SOUTH);
		window.add(scrollPane, BorderLayout.CENTER);
		window.setVisible(true);
		
		//Event handler for OK button
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
			}
		});
	}
	
	
	public void playUNO() {
		
		game = new UNO(players);
		game.setCurrentPlayerToPlay(players.get(0));
		game.DealCards();
		createGameWindow();
	}
	
	public void createGameWindow() {
		
		JFrame gameWindow = new JFrame("Play UNO");
		gameWindow.setSize(1000, 1000);
		gameWindow.setLayout(new GridLayout(3,1));
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setLocationRelativeTo(null);
		
		JLabel backgroundPicture = new JLabel(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\UNOGamePicture2.png").getImage().getScaledInstance(1100,400,Image.SCALE_SMOOTH)), JLabel.CENTER);
		
		JPanel playerScorePanel = new JPanel(new FlowLayout());
		playerScorePanel.setBackground(Color.DARK_GRAY);
		
		JLabel player1ScoreMessageLabel = new JLabel();
		JLabel player2ScoreMessageLabel = new JLabel();
		JLabel player3ScoreMessageLabel = new JLabel();
		JLabel player4ScoreMessageLabel = new JLabel();
		JLabel player1Score = new JLabel();
		JLabel player2Score = new JLabel();
		JLabel player3Score = new JLabel();
		JLabel player4Score = new JLabel();
		
		player1ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player1ScoreMessageLabel.setForeground(Color.RED);
		player1Score.setFont(new Font("Serif", Font.BOLD, 24));
		player1Score.setForeground(Color.RED);
		player2ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player2ScoreMessageLabel.setForeground(Color.BLUE);
		player2Score.setFont(new Font("Serif", Font.BOLD, 24));
		player2Score.setForeground(Color.BLUE);
		player3ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player3ScoreMessageLabel.setForeground(Color.GREEN);
		player3Score.setFont(new Font("Serif", Font.BOLD, 24));
		player3Score.setForeground(Color.GREEN);
		player4ScoreMessageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		player4ScoreMessageLabel.setForeground(Color.YELLOW);
		player4Score.setFont(new Font("Serif", Font.BOLD, 24));
		player4Score.setForeground(Color.YELLOW);
		
		
		if (game.getPlayerList().size() == 2) {
			player1Score.setText(Integer.toString(game.getPlayerList().get(0).getPlayerScore()));
			player2Score.setText(Integer.toString(game.getPlayerList().get(1).getPlayerScore()));
			
			player1ScoreMessageLabel.setText(game.getPlayerList().get(0).getName() + "'s Score: ");
			player2ScoreMessageLabel.setText(game.getPlayerList().get(1).getName() + "'s Score: ");
			
			playerScorePanel.add(player1ScoreMessageLabel);
			playerScorePanel.add(player1Score);
			playerScorePanel.add(player2ScoreMessageLabel);
			playerScorePanel.add(player2Score);
		}
		
		if (game.getPlayerList().size() == 3) {
			player1Score.setText(Integer.toString(game.getPlayerList().get(0).getPlayerScore()));
			player2Score.setText(Integer.toString(game.getPlayerList().get(1).getPlayerScore()));
			player3Score.setText(Integer.toString(game.getPlayerList().get(2).getPlayerScore()));;
			
			player1ScoreMessageLabel.setText(game.getPlayerList().get(0).getName() + "'s Score: ");
			player2ScoreMessageLabel.setText(game.getPlayerList().get(1).getName() + "'s Score: ");
			player3ScoreMessageLabel.setText(game.getPlayerList().get(2).getName() + "'s Score: ");
			
			playerScorePanel.add(player1ScoreMessageLabel);
			playerScorePanel.add(player1Score);
			playerScorePanel.add(player2ScoreMessageLabel);
			playerScorePanel.add(player2Score);
			playerScorePanel.add(player3ScoreMessageLabel);
			playerScorePanel.add(player3Score);
		}
		
		if (game.getPlayerList().size() == 4) {
			player1Score.setText(Integer.toString(game.getPlayerList().get(0).getPlayerScore()));
			player2Score.setText(Integer.toString(game.getPlayerList().get(1).getPlayerScore()));
			player3Score.setText(Integer.toString(game.getPlayerList().get(2).getPlayerScore()));
			player4Score.setText(Integer.toString(game.getPlayerList().get(3).getPlayerScore()));
			
			player1ScoreMessageLabel.setText(game.getPlayerList().get(0).getName() + "'s Score: ");
			player2ScoreMessageLabel.setText(game.getPlayerList().get(1).getName() + "'s Score: ");
			player3ScoreMessageLabel.setText(game.getPlayerList().get(2).getName() + "'s Score: ");
			player4ScoreMessageLabel.setText(game.getPlayerList().get(3).getName() + "'s Score: ");
			
			playerScorePanel.add(player1ScoreMessageLabel);
			playerScorePanel.add(player1Score);
			playerScorePanel.add(player2ScoreMessageLabel);
			playerScorePanel.add(player2Score);
			playerScorePanel.add(player3ScoreMessageLabel);
			playerScorePanel.add(player3Score);
			playerScorePanel.add(player4ScoreMessageLabel);
			playerScorePanel.add(player4Score);
		}
		
		JPanel currentTurnMessageBoard = new JPanel(new GridLayout(2,1));
		currentTurnMessageBoard.setBackground(Color.DARK_GRAY);
		JLabel currentPlayerToPlayNameLabel = new JLabel(game.getCurrentPlayerToPlay().getName() + "'s Turn", JLabel.CENTER); // Puts a label so we know whose turn it is
		currentPlayerToPlayNameLabel.setFont(new Font("Serif", Font.BOLD, 30));
		currentPlayerToPlayNameLabel.setForeground(Color.GREEN);
		JLabel currentTurnsColorLabel = new JLabel("Turn Color: " + game.getCurrentTurnColor(), JLabel.CENTER);
		currentTurnsColorLabel.setFont(new Font("Serif", Font.BOLD, 25));
		if (game.getCurrentTurnColor().equals("Green")) {
			currentTurnsColorLabel.setForeground(Color.GREEN);
		}
		else if (game.getCurrentTurnColor().equals("Red")) {
			currentTurnsColorLabel.setForeground(Color.RED);
		}
		else if (game.getCurrentTurnColor().equals("Blue")) {
			currentTurnsColorLabel.setForeground(Color.GREEN);
		}
		else {
			currentTurnsColorLabel.setForeground(Color.YELLOW);
		}
		currentTurnMessageBoard.add(currentPlayerToPlayNameLabel);
		currentTurnMessageBoard.add(currentTurnsColorLabel);
		
		JPanel playerHandPanel = new JPanel(new GridLayout(1,108));  // Holding current player to play's hand
		
		// Listens to when the user picks a card out of her/his hand. It then created a UNOCard that resembles the card pictured on the JButton. 
		// It does this by parsing through the string I set for the buttons name when I created the JButtons from the cards in the players hand.
		// The string will look something like this "One Blue 1" or "Wild None 50" so I can then put it back into a card.
		// Once it is back into a Card, it is processed to see if the card picked is a valid card to play.
		ActionListener listener = new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				
				if (btn.getName().equals("Draw Card Button")) {
					gameWindow.dispose();
					game.getCurrentPlayerToPlay().getUNOPlayersHand().add(game.getUNODeck().DealAUNOCard());
					createGameWindow();
				}
				
				String cardName = btn.getName().substring(0, btn.getName().indexOf(" "));
				String cardColor = btn.getName().substring(btn.getName().indexOf(" ") + 1, btn.getName().lastIndexOf(" "));
				String cardValue = btn.getName().substring(btn.getName().lastIndexOf(" ") + 1);
				System.out.println(cardName + " " + cardColor + " " + cardValue);
				System.out.println(game.getUNODeck().getUNODiscardPile().size());
				UNOCard cardPickedByPlayer = new UNOCard(cardName, cardColor, Integer.valueOf(cardValue));
				
				if (game.CheckIfCardPlayedIsValid(cardPickedByPlayer)) {
					
					for (int i = 0; i < game.getCurrentPlayerToPlay().getUNOPlayersHand().size(); i++) { // removing the card played from the Players hand
						if (game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName().equals(cardName) && game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getColor().equals(cardColor)) {
							game.getCurrentPlayerToPlay().getUNOPlayersHand().remove(i);
							break;
						}
					}
					
					 // Checking if the player only had one card. If he/she does then the call UNO window will be displayed
					// and the must call uno in three seconds or else they will recieve two cards
					if (game.getCurrentPlayerToPlay().getUNOPlayersHand().size() == 1) {   
							
						if (cardName.equals("Wild") || cardName.equals("Wild_Draw_Four")) {
							WildIsPlayedAskUserForColor();
						}
					}
					
					else if (game.getCurrentPlayerToPlay().getUNOPlayersHand().size() == 0) {    // Checking if the player dealt his/her last card
						game.CalculatePointsFromEachPlayerToWinner(game.getCurrentPlayerToPlay()); // Calculating points for the winner
						gameWindow.dispose();
						
						if (game.getCurrentPlayerToPlay().getPlayerScore() > 500) {  // GAME OVER!!!!!!!!!!!!!!
							gameWindow.dispose();
							gameOverShowPlayerWhoWon(game.getCurrentPlayerToPlay());
						}
						
						for (UNOPlayer player : game.getPlayerList()) {  //getting rid of all the cards in the players hands to get ready for the new hand
							player.getUNOPlayersHand().clear();
						}
						game.getUNODeck().OutOfUNOCardsReShuffle();   // Reshuffling the deck for the new hand
						game.setCurrentPlayerToPlay(players.get(0));  // setting the player to play first
						game.DealCards();                          // deal the new hand to the players
						createGameWindow();                      // LET"S GO!!!!!!!
					}
					
					game.PlayerToPlayNext(cardPickedByPlayer);  //Finds who the next player to play a card should be
	
					if (cardName.equals("Wild") || cardName.equals("Wild_Draw_Four") && game.getCurrentPlayerToPlay().getUNOPlayersHand().size() != 1) {
						WildIsPlayedAskUserForColor();
					}
					else {
					createGameWindow();
					}
				}
				else {
					invalidCardPlayMessageWindow();
				}
			}
		};
		
		// This is adding the correct pictures of the current players hand and adding it to the the Game Window
		for (int i = 0; i < game.getCurrentPlayerToPlay().getUNOPlayersHand().size(); i++) {
			
			if (game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() != "Wild_Draw_Four" && game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() != "Wild") {
			JButton cardInPlayersHandPicture = new JButton(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\" + game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() + 
					"_" + game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getColor() + ".png").getImage().getScaledInstance(150,355,Image.SCALE_SMOOTH)));
			
			cardInPlayersHandPicture.setName(game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() + " " + game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getColor()
					+ " " + Integer.toString(game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getValue()));
			cardInPlayersHandPicture.addActionListener(listener);
			playerHandPanel.add(cardInPlayersHandPicture);
			}
			else {
				JButton cardInPlayersHandPicture = new JButton(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\" + game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() +
						".png").getImage().getScaledInstance(150,355,Image.SCALE_SMOOTH)));
				
				cardInPlayersHandPicture.setName(game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getName() + " " + game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getColor()
						+ " " + Integer.toString(game.getCurrentPlayerToPlay().getUNOPlayersHand().get(i).getValue()));
				cardInPlayersHandPicture.addActionListener(listener);
				playerHandPanel.add(cardInPlayersHandPicture);
			}
		}
		
		
		JButton callUNOButton = new JButton("Call UNO");
		callUNOButton.addActionListener(listener);
		callUNOButton.setName("Call UNO");
		playerScorePanel.add(callUNOButton);
		
		JPanel deckPanel = new JPanel(new GridLayout(1,4));   // Holding the last card put on top of the discard pile
		deckPanel.setBackground(Color.DARK_GRAY);
	
		deckPanel.add(playerScorePanel);
		
		if (game.getUNODeck().getUNODiscardPile().get(game.getUNODeck().getUNODiscardPile().size() - 1).getName() != "Wild_Draw_Four" && game.getUNODeck().getUNODiscardPile().get(game.getUNODeck().getUNODiscardPile().size() - 1).getName() != "Wild") {
			JButton lastCardPlayedPicture = new JButton(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\" + game.getUNODeck().getUNODiscardPile().get(game.getUNODeck().getUNODiscardPile().size() - 1).getName() + 
					"_" + game.getUNODeck().getUNODiscardPile().get(game.getUNODeck().getUNODiscardPile().size() - 1).getColor() + ".png").getImage().getScaledInstance(250,330,Image.SCALE_SMOOTH)));
			deckPanel.add(lastCardPlayedPicture);
			}
			else {
				WildIsPlayedAskUserForColor();
				JButton lastCardPlayedPicture = new JButton(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\" + game.getUNODeck().getUNODiscardPile().get(game.getUNODeck().getUNODiscardPile().size() - 1).getName() +
						".png").getImage().getScaledInstance(250,330,Image.SCALE_SMOOTH)));
				deckPanel.add(lastCardPlayedPicture);
			}
		
		JButton drawCard = new JButton();
		drawCard.setIcon(new ImageIcon(new ImageIcon("UNO_Card_Pictures\\UNO_Deck.png").getImage().getScaledInstance(275,375,Image.SCALE_SMOOTH)));
		drawCard.setName("Draw Card Button");
		drawCard.addActionListener(listener);
		deckPanel.add(drawCard);
		deckPanel.add(currentTurnMessageBoard);
		
		
		gameWindow.add(backgroundPicture);
		gameWindow.add(deckPanel);
		gameWindow.add(playerHandPanel);
		gameWindow.setVisible(true);
		
		
		
	}
	
	public void invalidCardPlayMessageWindow() {
		JFrame window = new JFrame("Invalid Card");
		window.setSize(600, 150);
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JLabel text = new JLabel("<html>Invalid Card was chosen. Please Choose a Different Card to Play.</html>");
		JButton okButton = new JButton("Ok");
		JPanel panel1 = new JPanel(new GridLayout(2,1));
		JPanel panel2 = new JPanel();
		
		panel1.add(text);
		panel2.add(okButton);
		panel1.add(panel2);
		
		window.add(panel1);
		window.setVisible(true);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
			}
		});
	}
	
	
	// Creating window to ask user for the new color due to wild card being played
	public void WildIsPlayedAskUserForColor() { 
		//Creating window
		JFrame window = new JFrame("Wild Card Was Played");
		window.setSize(600, 125);
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		// Creating Color name field
		JLabel colorMessageLabel = new JLabel("Choose New Game Color:");
		
		//Creating Listener for JButtons to to know what color the user chooses
		ActionListener listener = new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				game.setCurrentTurnColor(btn.getText());
				window.dispose();
				createGameWindow();
			}
		};
		
		// Creating color buttons
		JButton redButton = new JButton("Red");
		redButton.addActionListener(listener);
		JButton greenButton = new JButton("Green");
		greenButton.addActionListener(listener);
		JButton blueButton = new JButton("Blue");
		blueButton.addActionListener(listener);
		JButton yellowButton = new JButton("Yellow");
		yellowButton.addActionListener(listener);
		
		//Creating panels to add all objects to
		JPanel panel1 = new JPanel(new GridLayout(1,1)); // Creating a Flow Layout for first row
		panel1.add(colorMessageLabel);

		JPanel panel2 = new JPanel(new GridLayout(2,2));
		panel2.add(redButton);
		panel2.add(greenButton);
		panel2.add(blueButton);
		panel2.add(yellowButton);
		
		//Adding panel of objects to the JFrame window
		window.add(panel1);
		window.add(panel2);
		window.setVisible(true); 
				
	}
	

	
	//	int delay = 10000; //milliseconds
	//	Timer unoTimer = new Timer(delay, listener);
	//	unoTimer.setRepeats(false);
	//	unoTimer.setActionCommand("Timer");
	//	unoTimer.start();
		

	
	//Creating a window to display the game is over and what player won the game
	public void gameOverShowPlayerWhoWon(UNOPlayer winner) {  
		JFrame window = new JFrame("Game Over");
		window.setSize(800,800);
		window.setLayout(new FlowLayout());
		window.setBackground(Color.BLACK);
		
		JLabel pictureLabel = new JLabel(new ImageIcon(new ImageIcon(
				"UNO_Card_Pictures\\UNOGamePicture2.png").getImage().getScaledInstance(1000,400,Image.SCALE_SMOOTH)), JLabel.CENTER);
		JLabel text = new JLabel("Congradulations! " + winner.getName() + " has won! Great Game!", JLabel.CENTER);
		text.setFont(new Font("Serif", Font.BOLD, 30));
		text.setForeground(Color.RED);
		text.setBackground(Color.DARK_GRAY);
		
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.setBackground(Color.BLACK);
		panel.add(pictureLabel);
		panel.add(text);
		
		window.add(panel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // Positioning the JFrame and opening the window
		window.setVisible(true);
		
		return;
		
	}
	
	
}
