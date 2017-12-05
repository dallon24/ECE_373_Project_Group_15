package org.Final_Project.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.Final_Project.Players.Player;

public class Game {

	//Fields for Game
	protected ArrayList<Player> players;
	
	//Constructor
	public Game() {
		players = new ArrayList<Player>();
	}
	
	//Methods for Game
	public Player getPlayer(int index) {
		return players.get(index);
	}
	public void addPlayer(Player newPlayer) {
		players.add(newPlayer);
	}
	
public void MainMenuGUI() {
		JFrame window = new JFrame("Classic Card Games");
		window.setSize(800,800);
		window.setLayout(new GridLayout(2,1));
		window.getContentPane().setBackground(Color.BLACK);
		
		//Creating a label for the window tol hold the UNO Main Menu Picture
		JLabel label = new JLabel(new ImageIcon(new ImageIcon("ClassicCardGames.png").getImage().getScaledInstance(800,400,Image.SCALE_SMOOTH)), JLabel.CENTER);
		window.add(label);
		
		//Creating a panel to hold the buttons in desired positions
		JPanel panel = new JPanel(new GridLayout(4,1));
		JButton playUNOButton;
		JButton play24Button;
		JButton play99Button;
		JButton exitButton;
		
		playUNOButton = new JButton("Play UNO"); //Play button to start the UNO game
		playUNOButton.setBackground(Color.GREEN);
		playUNOButton.setFont(new Font("Serif", Font.BOLD, 24));
		
		play24Button = new JButton("Play 24"); // Instructions button that will open a window that will show all the instructions
		play24Button.setBackground(Color.BLUE);
		play24Button.setFont(new Font("Serif", Font.BOLD, 24));
		
		play99Button = new JButton("Play 99");  // Exit button will close the UNO GUI window
		play99Button.setBackground(Color.YELLOW);
		play99Button.setFont(new Font("Serif", Font.BOLD, 24));
		
		exitButton = new JButton("Exit");  // Exit button will close the UNO GUI window
		exitButton.setBackground(Color.RED);
		exitButton.setFont(new Font("Serif", Font.BOLD, 24));
		
		// Adding the buttons to the panel
		panel.add(playUNOButton);
		panel.add(play24Button);
		panel.add(play99Button);
		panel.add(exitButton);
		
		window.add(panel); // adding panel to JFrame
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // Positioning the JFrame and opening the window
		window.setVisible(true);
		
		playUNOButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false); 
				window.dispose();
				@SuppressWarnings("unused")
				UNOGUI game = new UNOGUI("UNO");
			}
		});
		
		play24Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false); 
				window.dispose();
				@SuppressWarnings("unused")
				Game24GUI game = new Game24GUI("Game24");
			}
		});
		
		play99Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false); 
				window.dispose();
				@SuppressWarnings("unused")
				Game99GUI game = new Game99GUI("Game99");
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false); 
				window.dispose();
				System.exit(0);  // Exit main menu GUI
			}
		});
		
	}
	
}
