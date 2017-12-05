package org.Final_Project.Game;

import org.Final_Project.Deck.*;
import org.Final_Project.Deck.Card;
import org.Final_Project.Players.Player;

import java.util.ArrayList;

public class Game99 {

	//Field for Game 99
	private Deck Game99Deck;
	private int pointTotal;
	private int specialDecision;
	private int specialValue;
	private ArrayList<Player> player99;
	private Player currentPlayerToPlay;
	private int currentPlayerToPlayIndex;

	//Constructor
	public Game99(ArrayList<Player> players) {
		Game99Deck = new Deck();
		pointTotal = 0;
		specialDecision = 0;
		specialValue = 0;
		player99 = new ArrayList<Player>();
		player99.addAll(players);
		currentPlayerToPlay = new Player();
		currentPlayerToPlayIndex = 0;
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
    public Deck get99Deck() {
		return Game99Deck;
	}
    public ArrayList<Player> getPlayerList(){
		return player99;
	}
	
	public Player getCurrentPlayerToPlay() {
		return currentPlayerToPlay;
	}
	
	public void setCurrentPlayerToPlay(Player player) {
		currentPlayerToPlay = player;
	}
	
	// Deals 5 cards to each player to start the game
	public void DealCards() {  
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j < player99.size(); j++) {
				player99.get(j).getPlayersHand().add(Game99Deck.DealACard());
			}
		}
		//player99.get(0).getPlayersHand().add(new Card("Ace", "Spade", 1));
		//Game99Deck.addCardToDiscardPile(Game99Deck.DealACard());
	}
	
	public void PlayerToPlayNext() { // Determines the next player to play
	
		currentPlayerToPlayIndex++;
		if (currentPlayerToPlayIndex >= player99.size()) {
			currentPlayerToPlayIndex = 0;
		}
		currentPlayerToPlay = player99.get(currentPlayerToPlayIndex); // Next Player to play a card
	}
	
	
	//Checks if pointTotal is 99 or not
	public boolean CheckPointTotal() { 
		if (this.pointTotal > 99) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Special cards function/decision (A, 10, J, Q, K)
	public int SpecialCardDecision(Card aCard) {
		if (aCard.getName().equals("Jack")) {
			specialDecision = 11;
		}
		else if (aCard.getName().equals("Queen")) {
			specialDecision = 12;
		}
		else if (aCard.getName().equals("King")) {
			specialDecision = 13;
		}
		else if (aCard.getName().equals("Ace") && !(aCard.getSuit().equals("Spade"))) {
			specialDecision = 1;
		}
		else if (aCard.getName().equals("Ace") && aCard.getSuit().equals("Spade")) {
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
    	Game99Deck.addCardToDiscardPile(aCard);		//add the played card to discardPile
    	
    	if(specialDecision == 13) {
    		this.pointTotal = 99;
    	}
    	else if(specialDecision == 11) {
    		this.pointTotal += 0;
    	}
    	else if(specialDecision == 111) {
    		this.pointTotal = 0;
    	}
    	else if(specialDecision != 0) {
    		this.pointTotal += specialValue;
    	}
    	else {
    		this.pointTotal += aCard.getValue();
    	}
    	
    	if(this.pointTotal < 0) {
    		this.pointTotal = 0;
    	}
    	player99.get(currentPlayerToPlayIndex).getPlayersHand().add(Game99Deck.DealACard());
    }
    
}
