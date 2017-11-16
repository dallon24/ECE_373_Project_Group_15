package org.Final_Project.Game;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.Final_Project.Deck.UNOCard;
import org.Final_Project.Deck.UNODeck;
import org.Final_Project.Players.Player;
import org.Final_Project.Players.UNOPlayer;


public class UNO {    //subclass of Game
	
	//Fields for UNO
	private UNODeck GameUNODeck;
	private String gameDirection;
	private String currentTurnColor;
	private ArrayList<UNOCard> discardPile;
	private ArrayList<UNOPlayer> UNOplayers;
	private UNOPlayer currentPlayerToPlay;
	private int currentPlayerToPlayIndex;
	
	//Constructor
	public UNO(ArrayList<UNOPlayer> players) {
		GameUNODeck = new UNODeck();
		gameDirection = "right";
		currentTurnColor = "unknown color";
		discardPile = new ArrayList<UNOCard>();
		UNOplayers = players;
		currentPlayerToPlay = new UNOPlayer();
		currentPlayerToPlayIndex = 0;
	}
	
	//Methods for UNO
	
	public void DealCards() {  // Deals 7 cards to each player to start the game
		for (int i = 1; i <= 7; i++) {
			for (int j = 0; j < UNOplayers.size(); i++) {
				UNOplayers.get(j).getPlayersHand().add(GameUNODeck.DealACard());
			}
		}
	}
	
	public void PlayerToPlayNext(UNOCard aCard) { // Dertermines the next player to play
		if (gameDirection == "right") { // If Game is moving in one direction
			currentPlayerToPlayIndex++;
			if (currentPlayerToPlayIndex >= UNOplayers.size()) {
				currentPlayerToPlayIndex = currentPlayerToPlayIndex - UNOplayers.size();
			}
		}
		
		if (gameDirection == "left") { // If Game is moving in other direction
			currentPlayerToPlayIndex--;
			if (currentPlayerToPlayIndex <= 0) {
				currentPlayerToPlayIndex = currentPlayerToPlayIndex + UNOplayers.size();
			}
		}
		
		currentPlayerToPlay = UNOplayers.get(currentPlayerToPlayIndex); // Next Player to play a card
	}
	
	public boolean CheckIfCardPlayedIsValid(UNOCard aCard) { //Checks if the card played is valid and if true it adds that card to the discard pile
		
		if (discardPile.size() == 0) {
			discardPile.add(aCard);
			return true;
		}
		else if (discardPile.get(discardPile.size() - 1).getName() == aCard.getName()) { // If card has the same value as last card played it is valid
			discardPile.add(aCard);
			currentTurnColor = aCard.getColor();
			if (aCard.getName() == "Skip" || aCard.getName() == "Reverse" || aCard.getName() == "Draw Two") { //if it is a special card it needs to have the action performed
				SpecialCardAction(aCard);
			}
			return true;
		}
		else if (currentTurnColor == aCard.getColor()) { // if card is the same color as last card played's color it is valid
			discardPile.add(aCard);
			if (aCard.getName() == "Skip" || aCard.getName() == "Reverse" || aCard.getName() == "Draw Two") { //if it is a special card it needs to have the action performed
				SpecialCardAction(aCard);
			}
			return true;
		}
		else if (aCard.getName() == "Wild" || aCard.getName() == "Wild Draw Four") { // if it is a wild or wild draw four card it is valid and performs its special action
			SpecialCardAction(aCard);
			discardPile.add(aCard);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void CalculatePointsFromEachPlayerToWinner(Player winner) { //Adding all the points from everyones cards in their hand to the winner of that hand score

		for (int i = 0; i < UNOplayers.size(); i++) {  
			if (UNOplayers.get(i).getName() != winner.getName()) {
				for (int j = 0; j < UNOplayers.get(i).getPlayersHand().size(); j++) {
					winner.setPlayerScore(UNOplayers.get(i).getPlayersHand().get(j).getValue() + winner.getPlayerScore());
			}
		}
	}
		
		if (winner.getPlayerScore() > 500) {
			//End of game. Implement this later
		}
	}
	public boolean CheckForUNO(Player aPlayer) { //Checks to see if the player who called UNO really only has one card left
		if (aPlayer.getPlayersHand().size() == 1) {
			return true;
		}
		else {
			aPlayer.getPlayersHand().add(GameUNODeck.DealACard()); // IF player called for UNO and really did not have UNO, the player is dealt two cards
			aPlayer.getPlayersHand().add(GameUNODeck.DealACard());
			return false;
		}
	}
			
	public void SpecialCardAction(UNOCard aCard) { //Performs the action of a special card => Skip, Draw Two, Reverse, Wild Draw Four
		
		if (aCard.getName() == "Reverse") {  // Reverse card is played, so game direction is switched
			if (gameDirection == "right") {
				gameDirection = "left";
			}
			else {
				gameDirection = "right";
			}
		}
		
		if (aCard.getName() == "Skip") {
			if (gameDirection == "right") { // If Game is moving in one direction
				currentPlayerToPlayIndex = currentPlayerToPlayIndex + 2; // player next to play is skipped
				if (currentPlayerToPlayIndex >= UNOplayers.size()) {
					currentPlayerToPlayIndex = currentPlayerToPlayIndex - UNOplayers.size();
				}
			}
			
			if (gameDirection == "left") { // If Game is moving in other direction
				currentPlayerToPlayIndex = currentPlayerToPlayIndex - 2; // player next to play is skipped
				if (currentPlayerToPlayIndex <= 0) {
					currentPlayerToPlayIndex = currentPlayerToPlayIndex + UNOplayers.size();
				}
			}
		}
		
		currentPlayerToPlay = UNOplayers.get(currentPlayerToPlayIndex); // Next Player to play a card
		
		if (aCard.getName() == "Draw Two") {  // If a Draw Two Card is played, the enxt player to play is dealt two cards
			if (gameDirection == "right") {
				if (currentPlayerToPlayIndex < UNOplayers.size()) {
					UNOplayers.get(currentPlayerToPlayIndex + 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(currentPlayerToPlayIndex + 1).getPlayersHand().add(GameUNODeck.DealACard());
				}
				else {
					UNOplayers.get(0).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(0).getPlayersHand().add(GameUNODeck.DealACard());
				}
			}
			
			if (gameDirection == "left") {
				if (currentPlayerToPlayIndex != 0) {
					UNOplayers.get(currentPlayerToPlayIndex - 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(currentPlayerToPlayIndex - 1).getPlayersHand().add(GameUNODeck.DealACard());
				}
				else {
					UNOplayers.get(UNOplayers.size() - 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(UNOplayers.size() - 1).getPlayersHand().add(GameUNODeck.DealACard());
				}
			}
		}
		
		if (aCard.getName() == "Wild") {  // Wild is played, function called to ask user for new color
			WildIsPlayedAskUserForColor();
		}
		
		if (aCard.getName() == "Wild Draw Four") {
			if (gameDirection == "right") { // if game direction is this way
				if (currentPlayerToPlayIndex < UNOplayers.size()) {
					UNOplayers.get(currentPlayerToPlayIndex + 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(currentPlayerToPlayIndex + 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(currentPlayerToPlayIndex + 1).getPlayersHand().add(GameUNODeck.DealACard()); // draw four cards
					UNOplayers.get(currentPlayerToPlayIndex + 1).getPlayersHand().add(GameUNODeck.DealACard());
				}
				else {
					UNOplayers.get(0).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(0).getPlayersHand().add(GameUNODeck.DealACard()); // draw four cards
					UNOplayers.get(0).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(0).getPlayersHand().add(GameUNODeck.DealACard());
				}
			}
			
			if (gameDirection == "left") { // if game direction is this way
				if (currentPlayerToPlayIndex != 0) {
					UNOplayers.get(currentPlayerToPlayIndex - 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(currentPlayerToPlayIndex - 1).getPlayersHand().add(GameUNODeck.DealACard()); // draw four cards
					UNOplayers.get(currentPlayerToPlayIndex - 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(currentPlayerToPlayIndex - 1).getPlayersHand().add(GameUNODeck.DealACard());
				}
				else {
					UNOplayers.get(UNOplayers.size() - 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(UNOplayers.size() - 1).getPlayersHand().add(GameUNODeck.DealACard());
					UNOplayers.get(UNOplayers.size() - 1).getPlayersHand().add(GameUNODeck.DealACard()); // draw four cards
					UNOplayers.get(UNOplayers.size() - 1).getPlayersHand().add(GameUNODeck.DealACard());
				}
			}
			WildIsPlayedAskUserForColor();
		}
		
	}
	
	public void WildIsPlayedAskUserForColor() { // Creating window to ask user for the new color due to wild card being played
		//Creating window
		JFrame window = new JFrame("Wild Card Was Played");
		window.setSize(600, 125);
		window.setLayout(new FlowLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		// Creating Color name field
		JLabel colorMessageLabel = new JLabel("Choose New Game Color:");
		
		// Creating color buttons
		JButton redButton = new JButton("Red");
		JButton greenButton = new JButton("Green");
		JButton blueButton = new JButton("Blue");
		JButton yellowButton = new JButton("Yellow");
		
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
		
		//Event handler for Red button
		redButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTurnColor = "Red";
				window.setVisible(false);
				}
		});
		
		//Event Handler for Green button
		greenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTurnColor = "Green";
				window.setVisible(false);
			}
		});
		
		//Event Handler for Green button
		blueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTurnColor = "Blue";
				window.setVisible(false);
			}
		});
		//Event Handler for Green button
		yellowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTurnColor = "Yellow";
				window.setVisible(false);
			}
		});
				
	}
	
	public void playUNO() {
		JFrame gameWindow = new JFrame();
		
	}

}
