package org.Final_Project.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import org.Final_Project.Deck.*;
import org.Final_Project.Players.*;

@SuppressWarnings("serial")

public class Game99GUI extends JFrame{

	private JButton playButton;
	private JButton instructionButton;
	private JButton exitButton;
	private ArrayList<Player> players;
	private Game99 game;
	
	public Game99GUI() {
		
	}
	
	public Game99GUI(String title) {
		
		super(title);    //Creating the JFrame with desired parameters
		setSize(800,800);
		setLayout(new GridLayout(2,1));
		getContentPane().setBackground(Color.BLACK);
		
		//Creating a label for the window to hold the Game99 Main Menu Picture
		JLabel label = new JLabel(new ImageIcon(new ImageIcon("images99\\Game99.png").getImage().getScaledInstance(800,400,Image.SCALE_SMOOTH)), JLabel.CENTER);
		add(label);
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		//Creating the list to hold all the players that will play game99
		players = new ArrayList<Player>();
		
		playButton = new JButton("Play"); 
		playButton.setBackground(Color.GREEN);
		playButton.setFont(new Font("Serif", Font.BOLD, 24));
		
		instructionButton = new JButton("Instructions"); // Instructions button that will open a window that will show all the instructions
		instructionButton.setBackground(Color.ORANGE);
		instructionButton.setFont(new Font("Serif", Font.BOLD, 24));
		
		exitButton = new JButton("Exit");  // Exit button will close the Game99 GUI window
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
				
				Game mainMenu = new Game();  // Restart Classic Card Games Main Menu GUI
				mainMenu.MainMenuGUI();
			}
		});
		
	}
		
	public void askForAmountOfPlayersWindow() {
		JFrame window = new JFrame("How many players are there?");
		window.setSize(900, 900);
		window.setLayout(new GridLayout(3,1));
		window.getContentPane().setBackground(Color.ORANGE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JLabel pictureLabel = new JLabel(new ImageIcon(new ImageIcon("images99\\Game99.png").getImage().getScaledInstance(900,280,Image.SCALE_SMOOTH)), JLabel.CENTER);
		JLabel messageLabel = new JLabel("<HTML><center>Game99" +
				"<BR>Select how many players will play. There can be either 2, 3, or 4 players.", JLabel.CENTER);
		messageLabel.setFont(new Font("Serif", Font.ITALIC, 38));
		window.add(pictureLabel);
		window.add(messageLabel);
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout());
		
		JButton twoPlayerButton = new JButton("Two Players");
		twoPlayerButton.setBackground(Color.GREEN);
		twoPlayerButton.setFont(new Font("Serif", Font.ITALIC, 24));
		JButton threePlayerButton = new JButton("Three Players");
		threePlayerButton.setBackground(Color.YELLOW);
		threePlayerButton.setFont(new Font("Serif", Font.ITALIC, 24));
		JButton fourPlayerButton = new JButton("Four Players"); 
		fourPlayerButton.setBackground(Color.PINK);
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
		JTextField player1NameField = new JTextField(40);
		JLabel player2MessageLabel = new JLabel("Player Two's Name:");
		JTextField player2NameField = new JTextField(40);
		JLabel player3MessageLabel = new JLabel("Player Three's Name:");
		JTextField player3NameField = new JTextField(40);
		JLabel player4MessageLabel = new JLabel("Player Four's Name:");
		JTextField player4NameField = new JTextField(20);
		
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
			
			playGame99(); //START THE GAME!
				}
		});
		
	}
	
	public void instructionsWindow() {
		JFrame window = new JFrame("GAME99 Instructions");
		window.setSize(1500, 1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JTextArea textArea = new JTextArea(50,50);
		textArea.setFont(new Font("Serif", Font.BOLD, 24));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.BLACK);
		
			FileReader reader;
			try {
				reader = new FileReader("Instructions99.txt");
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
	
	
	public void playGame99() {
		
		game = new Game99(players);
		game.setCurrentPlayerToPlay(players.get(0));
		game.DealCards();
		createGameWindow();
		
	}
	
	public void createGameWindow() {
		
		JFrame gameWindow = new JFrame("Play Game99");
		gameWindow.setSize(900, 900);
		gameWindow.setLayout(new GridLayout(3,1));
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setLocationRelativeTo(null);
		
		JLabel pictureLabel = new JLabel(new ImageIcon(new ImageIcon("images99\\Game99.png").getImage().getScaledInstance(900,280,Image.SCALE_SMOOTH)), JLabel.CENTER);
		
		JPanel PointTotalPanel = new JPanel(new FlowLayout());
		PointTotalPanel.setBackground(Color.LIGHT_GRAY);
		gameWindow.add(pictureLabel);
		
		JLabel PointTotalMessageLabel = new JLabel("Current Points Total: " + game.getPointsTotal(), JLabel.CENTER);
		
		
		PointTotalMessageLabel.setFont(new Font("Serif", Font.BOLD, 30));
		PointTotalMessageLabel.setForeground(Color.RED);
		
		
		JPanel deckPanel = new JPanel(new GridLayout(1,2));   // Holding the last card put on top of the discard pile
		deckPanel.setBackground(Color.LIGHT_GRAY);
	
		deckPanel.add(PointTotalMessageLabel);


		JLabel currentPlayerToPlayNameLabel = new JLabel(game.getCurrentPlayerToPlay().getName() + "'s Turn", JLabel.CENTER); // Puts a label so we know whose turn it is
		currentPlayerToPlayNameLabel.setFont(new Font("Serif", Font.BOLD, 30));
		currentPlayerToPlayNameLabel.setForeground(Color.BLUE);
		

		deckPanel.add(currentPlayerToPlayNameLabel);
		gameWindow.add(deckPanel);
		
		
		JPanel playerHandPanel = new JPanel(new GridLayout(1,200));  // Holding current player to play's hand
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JButton btn = (JButton) e.getSource();

				
				String cardName = btn.getName().substring(0, btn.getName().indexOf(" "));
				String cardSuit = btn.getName().substring(btn.getName().indexOf(" ") + 1, btn.getName().lastIndexOf(" "));
				String cardValue = btn.getName().substring(btn.getName().lastIndexOf(" ") + 1);
				System.out.println(cardName + " " + cardSuit + " " + cardValue);
				System.out.println(game.get99Deck().getDiscardPile().size());
				Card cardPickedByPlayer = new Card(cardName, cardSuit, Integer.valueOf(cardValue));
				
					
				for (int i = 0; i < game.getCurrentPlayerToPlay().getPlayersHand().size(); i++) { // removing the card played from the Players hand
					if (game.getCurrentPlayerToPlay().getPlayersHand().get(i).getName().equals(cardName) && game.getCurrentPlayerToPlay().getPlayersHand().get(i).getSuit().equals(cardSuit)) {
						game.getCurrentPlayerToPlay().getPlayersHand().remove(i);
						break;
					}
				}
				
				
				game.SpecialCardDecision(cardPickedByPlayer);
				
				if (game.getspecialDecision() != 0 && game.getspecialDecision() != 11 && game.getspecialDecision() != 13 && game.getspecialDecision() != 111) {
				SpecialCardValue(cardPickedByPlayer);
				}
				else {
				game.CalculatePointsTotal(cardPickedByPlayer);
				}
				
				gameWindow.dispose();
				
				if (game.CheckPointTotal()) {  // GAME OVER
					//gameWindow.dispose();
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals(game.getCurrentPlayerToPlay().getName())) {
							showPlayerLost(players.get(i));
							players.remove(i);
							game.getPlayerList().remove(i);
						}
					}
				}
				
				
				else if (game.getspecialDecision() == 0 || game.getspecialDecision() == 11 || game.getspecialDecision() == 13 || game.getspecialDecision() == 111) {
					game.PlayerToPlayNext();  //Finds who the next player to play a card should be
					createGameWindow();
				}
			}
		};
		
		// This is adding the correct pictures of the current players hand and adding it to the the Game Window
		for (int i = 0; i < game.getCurrentPlayerToPlay().getPlayersHand().size(); i++) {
			JButton cardInPlayersHandPicture = new JButton(new ImageIcon(new ImageIcon("images99\\" + game.getCurrentPlayerToPlay().getPlayersHand().get(i).getName()
					+ game.getCurrentPlayerToPlay().getPlayersHand().get(i).getSuit() + ".png").getImage().getScaledInstance(180,300,Image.SCALE_SMOOTH)));
			
				cardInPlayersHandPicture.setName(game.getCurrentPlayerToPlay().getPlayersHand().get(i).getName() + " " + game.getCurrentPlayerToPlay().getPlayersHand().get(i).getSuit()
						+ " " + Integer.toString(game.getCurrentPlayerToPlay().getPlayersHand().get(i).getValue()));
				cardInPlayersHandPicture.addActionListener(listener);
				playerHandPanel.add(cardInPlayersHandPicture);
		
		}

		
		gameWindow.add(playerHandPanel);
		gameWindow.setVisible(true);
		
	}
	
public void SpecialCardValue(Card cardPickedByPlayer) { 
		
		if((game.getspecialDecision() != 0) && (game.getspecialDecision() != 11) && (game.getspecialDecision() != 13) && game.getspecialDecision() != 111) {
		//Creating window
		JFrame window = new JFrame("Special Cards");
		window.setSize(500, 430);
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		// Creating name field
		JLabel cardMessage = new JLabel("Choose the value of Special Card:");
		cardMessage.setFont(new Font("Serif", Font.BOLD, 28));
		
		// Creating value buttons
		JButton plus20 = new JButton("+20");
		JButton minus20 = new JButton("-20");
		JButton plus10 = new JButton("+10");
		JButton minus10 = new JButton("-10");
		JButton plus1 = new JButton("+1");
		JButton minus1 = new JButton("-1");
		
		plus20.setPreferredSize(new Dimension(100,100));
		minus20.setPreferredSize(new Dimension(100,100));
		plus10.setPreferredSize(new Dimension(100,100));
		minus10.setPreferredSize(new Dimension(100,100));
		plus1.setPreferredSize(new Dimension(100,100));
		minus1.setPreferredSize(new Dimension(100,100));
		
		//Creating panels to add all objects to
		JPanel panel1 = new JPanel(new GridLayout(1,1)); 
		panel1.add(cardMessage);

		JPanel panel2 = new JPanel(new GridLayout(3,2));
		
		if (game.getspecialDecision() == 12) {
			panel2.add(plus20);
			panel2.add(minus20);
		}
		
		if (game.getspecialDecision() == 10) {
			panel2.add(plus10);
			panel2.add(minus10);
		}
		
		if (game.getspecialDecision() == 1) {
			panel2.add(plus1);
			panel2.add(minus1);
		}
		
		
		
		//Adding panel of objects to the JFrame window
		window.add(panel1);
		window.add(panel2);
		window.setVisible(true); 
		
		if(game.getspecialDecision() == 12) {
		//Event handler for +20
		plus20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setspecialValue(20);
				game.CalculatePointsTotal(cardPickedByPlayer);
				window.setVisible(false);
				if (game.CheckPointTotal()) {  // GAME OVER
					//gameWindow.dispose();
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals(game.getCurrentPlayerToPlay().getName())) {
							showPlayerLost(players.get(i));
							players.remove(i);
							game.getPlayerList().remove(i);
						}
					}
				}
				else {
				game.PlayerToPlayNext();
				createGameWindow();
				}
			}
		});
		//Event Handler for -20
		minus20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setspecialValue(-20);
				game.CalculatePointsTotal(cardPickedByPlayer);
				window.setVisible(false);
				if (game.CheckPointTotal()) {  // GAME OVER
					//gameWindow.dispose();
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals(game.getCurrentPlayerToPlay().getName())) {
							showPlayerLost(players.get(i));
							players.remove(i);
							game.getPlayerList().remove(i);
						}
					}
				}
				else {
					game.PlayerToPlayNext();
					createGameWindow();
					}
			}
		});
		}
		
		if(game.getspecialDecision() == 10) {
		//Event Handler for +10
		plus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setspecialValue(10);
				game.CalculatePointsTotal(cardPickedByPlayer);
				window.setVisible(false);
				if (game.CheckPointTotal()) {  // GAME OVER
					//gameWindow.dispose();
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals(game.getCurrentPlayerToPlay().getName())) {
							showPlayerLost(players.get(i));
							players.remove(i);
							game.getPlayerList().remove(i);
						}
					}
				}
				else {
					game.PlayerToPlayNext();
					createGameWindow();
					}
			}
		});		
		//Event Handler for -10
		minus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setspecialValue(-10);
				game.CalculatePointsTotal(cardPickedByPlayer);
				window.setVisible(false);
				if (game.CheckPointTotal()) {  // GAME OVER
					//gameWindow.dispose();
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals(game.getCurrentPlayerToPlay().getName())) {
							showPlayerLost(players.get(i));
							players.remove(i);
							game.getPlayerList().remove(i);
						}
					}
				
				}
				else {
					game.PlayerToPlayNext();
					createGameWindow();
					}
			}
		});
		}
		
		if(game.getspecialDecision() == 1) {
		//Event Handler for +1
		plus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setspecialValue(1);
				game.CalculatePointsTotal(cardPickedByPlayer);
				window.setVisible(false);
				if (game.CheckPointTotal()) {  // GAME OVER
					//gameWindow.dispose();
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals(game.getCurrentPlayerToPlay().getName())) {
							showPlayerLost(players.get(i));
							players.remove(i);
							game.getPlayerList().remove(i);
						}
					}
				
				}
				else {
					game.PlayerToPlayNext();
					createGameWindow();
					};
			}
		});
		//Event Handler for -1
		minus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setspecialValue(-1);
				game.CalculatePointsTotal(cardPickedByPlayer);
				window.setVisible(false);
				if (game.CheckPointTotal()) {  // GAME OVER
					//gameWindow.dispose();
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getName().equals(game.getCurrentPlayerToPlay().getName())) {
							showPlayerLost(players.get(i));
							players.remove(i);
							game.getPlayerList().remove(i);
						}
					}
				
				}
				else {
					game.PlayerToPlayNext();
					createGameWindow();
					}
			}
		});
		}		
	}
		
	}
	

	//display the game is over
	public void gameOver() {  
		JFrame window = new JFrame("Game Over");
		window.setSize(600,600);
		window.setLayout(new GridLayout(1,1));
		window.setBackground(Color.BLACK);
		

		JLabel text = new JLabel("Game Over!", JLabel.CENTER);
		text.setFont(new Font("Serif", Font.BOLD, 40));
		text.setForeground(Color.RED);
		text.setBackground(Color.DARK_GRAY);
		
		JLabel text2 = new JLabel("Winner is " + players.get(0).getName(), JLabel.CENTER);
		text2.setFont(new Font("Serif", Font.BOLD, 40));
		text2.setForeground(Color.RED);
		text2.setBackground(Color.DARK_GRAY);
		
		JButton okButton = new JButton("Ok");
		
		JPanel panel = new JPanel(new GridLayout(3,1));
		panel.setBackground(Color.BLACK);
		panel.add(text);
		panel.add(text2);
		panel.add(okButton);
		
		window.add(panel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // Positioning the JFrame and opening the window
		window.setVisible(true);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);

				@SuppressWarnings("unused")
				Game99GUI demo = new Game99GUI("Game99"); // exit back to 99 GUI
			}
		});
		
		return;
		
	}
	
	public void showPlayerLost(Player playerWhoLost) {
		JFrame window = new JFrame("Player Lost");
		window.setSize(600,600);
		window.setLayout(new GridLayout(1,1));
		window.setBackground(Color.BLACK);
		

		JLabel text = new JLabel("Player Lost!", JLabel.CENTER);
		text.setFont(new Font("Serif", Font.BOLD, 40));
		text.setForeground(Color.RED);
		text.setBackground(Color.DARK_GRAY);
		
		JLabel text2 = new JLabel("Player who lost is " + playerWhoLost.getName(), JLabel.CENTER);
		text2.setFont(new Font("Serif", Font.BOLD, 40));
		text2.setForeground(Color.RED);
		text2.setBackground(Color.DARK_GRAY);
		
		JButton okButton = new JButton("Ok");
		
		
		JPanel panel = new JPanel(new GridLayout(3,1));
		panel.setBackground(Color.BLACK);
		panel.add(text);
		panel.add(text2);
		panel.add(okButton);
		
		window.add(panel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // Positioning the JFrame and opening the window
		window.setVisible(true);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
				if (players.size() != 1) {
					game.setgetPointsTotal(0);
					game.PlayerToPlayNext();
					createGameWindow();
					}
				if (players.size() == 1) {
					gameOver();
				}
			}
		});
		
		return;
	}
	
	
}
