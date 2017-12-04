package org.Final_Project.Game;

import org.Final_Project.Deck.Deck;
import org.Final_Project.Deck.Card;
import org.Final_Project.Players.Player;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game99 extends Game{

	//Field for Game 99
	private Deck Game99Deck;
	private int pointTotal;
	private ArrayList<Card> discardPile;
	private int specialDecision;
	private int specialValue;
	private Player currentPlayer;
	private Player nextPlayer;;
	private Player winner;

	//Constructor
	public Game99() {
		Game99Deck = new Deck();
		pointTotal = 0;
		specialDecision = 0;
		specialValue = 0;
		discardPile = new ArrayList<Card>();
		currentPlayer = new Player();
		nextPlayer = new Player();
		winner = new Player();
	}
	
	//Methods for Game99
	
	//getter and setter
	public void setspecialDecision(int num) {
		specialDecision = num;
	}
	public int getspecialDecision() {
		return specialDecision;
	}
	public void setspecialValue(int num) {
		specialValue = num;
	}
	public int getspecialValue() {
		return specialValue;
	}
	public void setgetPointsTotal(int num) {
		pointTotal = num;
	}
    public int getPointsTotal() {
    	return pointTotal;
    }
	
	// Deals 5 cards to each player to start the game
	public void DealCards() {  
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j < players.size(); i++) {
				players.get(j).getPlayersHand().add(Game99Deck.DealACard());
			}
		}
	}
	
	//Checks if pointTotal is 99 or not
	public boolean CheckPointTotal() { 
		if (this.pointTotal == 99) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Special cards function/decision (A, 10, J, Q, K)
	public int SpecialCardDecision(Card aCard) {
		if (aCard.getName() == "Jake") {
			specialDecision = 11;
		}
		else if (aCard.getName() == "Queen") {
			specialDecision = 12;
		}
		else if (aCard.getName() == "King") {
			specialDecision = 13;
		}
		else if (aCard.getName() == "Ace" && aCard.getSuit() != "Spade") {
			specialDecision = 1;
		}
		else if (aCard.getName() == "Ace" && aCard.getSuit() == "Spade") {
			specialDecision = 111;
		}
		else if (aCard.getValue() == 10) {
			specialDecision = 10;
		}
		else {
			specialDecision = 0;
		}
		return specialDecision;
	}

	//calculate the current points total
    public void CalculatePointsTotal(Card aCard){
    	discardPile.add(aCard);		//add the played card to discardPile
    	
    	if(specialDecision == 13) {
    		this.pointTotal = 99;
    	}
    	else if(specialDecision == 11) {
    		this.pointTotal += 0;
    	}
    	else if(specialDecision != 0) {
    		this.pointTotal += specialValue;
    	}
    	else if(specialDecision == 111) {
    		this.pointTotal = 0;
    	}
    	else {
    		this.pointTotal += aCard.getValue();
    	}
    	currentPlayer.getPlayersHand().add(Game99Deck.DealACard());
    }
    
    // Setter and getter for players
    public void setCurrentlayer(Player cp) {
		currentPlayer = cp;
	}
	public Player getcurrentPlayer() {
		return currentPlayer;
	}
    
	public void setNextPlayer(Player np) {
		nextPlayer = np;
	}
	public Player getnextPlayer() {
		return nextPlayer;
	}
    
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public Player getWinner() {
		return winner;
	}
	
	// Creating window to ask user for the Special Card decision
	public void SpecialCardValue() { 
		//Creating window
		JFrame window = new JFrame("Special Cards");
		window.setSize(500, 300);
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		// Creating name field
		JLabel cardMessage = new JLabel("Choose the value of Special Card:");
		
		// Creating value buttons
		JButton plus20 = new JButton("+20");
		JButton minus20 = new JButton("-20");
		JButton plus10 = new JButton("+10");
		JButton minus10 = new JButton("-10");
		JButton plus1 = new JButton("+1");
		JButton minus1 = new JButton("-1");
		
		//Creating panels to add all objects to
		JPanel panel1 = new JPanel(new GridLayout(1,1)); 
		panel1.add(cardMessage);

		JPanel panel2 = new JPanel(new GridLayout(3,2));
		panel2.add(plus20);
		panel2.add(minus20);
		panel2.add(plus10);
		panel2.add(minus10);
		panel2.add(plus1);
		panel2.add(minus1);
		
		//Adding panel of objects to the JFrame window
		window.add(panel1);
		window.add(panel2);
		window.setVisible(true); 
		
		if(specialDecision == 12) {
		//Event handler for +20
		plus20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialValue = 20;
				window.setVisible(false);
			}
		});
		//Event Handler for -20
		minus20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialValue = -20;
				window.setVisible(false);
			}
		});
		}
		
		if(specialDecision == 10) {
		//Event Handler for +10
		plus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialValue = 10;
				window.setVisible(false);
			}
		});		
		//Event Handler for -10
		minus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialValue = -10;
				window.setVisible(false);
			}
		});
		}
		
		if(specialDecision == 1) {
		//Event Handler for +1
		plus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialValue = 1;
				window.setVisible(false);
			}
		});
		//Event Handler for -1
		minus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				specialValue = -1;
				window.setVisible(false);
			}
		});
		}
				
	}

	
}
