package org.Final_Project.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.Final_Project.Players.UNOPlayer;

@SuppressWarnings("serial")

public class UNOGUI extends JFrame{

	private JButton playButton;
	private JButton instructionButton;
	private JButton exitButton;
	private ArrayList<UNOPlayer> players;
	private UNO game;
	
	public UNOGUI(String title) {
		
		super(title);    //Creating the JFrame with desired parameters
		setSize(800,800);
		setLayout(new GridLayout(2,1));
		getContentPane().setBackground(Color.BLACK);
		
		//Creating a label for the window with the text with certain parameters
		JLabel label = new JLabel("<HTML><center>UNO" +
				"<BR>Select either to play, view instructions or exit to main menu.</center></HTML>", JLabel.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 24));
		label.setForeground(Color.RED);
		add(label);
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		playButton = new JButton("Play"); //Play button to start the UNO game
		playButton.setPreferredSize(new Dimension(10,10));
		
		instructionButton = new JButton("Instructions"); // Instructions button that will open a window that will show all the instructions
		instructionButton.setPreferredSize(new Dimension(10,10));
		
		exitButton = new JButton("Exit");  // Exit button will close the UNO GUI window
		exitButton.setPreferredSize(new Dimension(10,10));
		
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
				game = new UNO(players);
				game.playUNO();
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
		window.setLayout(new GridLayout(2,1));
		window.getContentPane().setBackground(Color.BLACK);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("<HTML><center>UNO" +
				"<BR>Select how many players will play. There can be either 2, 3, or 4 players.", JLabel.CENTER);
		label.setFont(new Font("Serif", Font.BOLD, 24));
		label.setForeground(Color.BLUE);
		window.add(label);
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		JButton twoPlayerButton = new JButton("Two Players"); 
		JButton threePlayerButton = new JButton("Three Players"); 
		JButton fourPlayerButton = new JButton("Four Players"); 
	
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
					newPlayer.setName(player3NameField.getText());
				}
			}
			window.setVisible(false);
			dispose();
				}
		});
		
	}
	
	public void instructionsWindow() {
		JFrame window = new JFrame("UNO Instructions");
		window.setSize(1000, 1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		JTextArea textArea = new JTextArea(50,50);
		textArea.setFont(new Font("Serif", Font.BOLD, 24));
		
			FileReader reader;
			try {
				reader = new FileReader("UNOInstructions.txt");
				try {
					textArea.read(reader, null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
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
	
	
	
}
