package org.Final_Project.Game;

import java.util.ArrayList;

import org.Final_Project.Deck.UNOCard;
import org.Final_Project.Deck.UNODeck;
import org.Final_Project.Players.UNOPlayer;


public class UNO {    //subclass of Game
	
	//Fields for UNO
	private UNODeck GameUNODeck;
	private String gameDirection;
	private String currentTurnColor;
	private ArrayList<UNOPlayer> UNOplayers;
	private UNOPlayer currentPlayerToPlay;
	private int currentPlayerToPlayIndex;
	
	//Constructor
	public UNO(ArrayList<UNOPlayer> players) {
		GameUNODeck = new UNODeck();
		gameDirection = "right";
		currentTurnColor = "unknown color";
		UNOplayers = new ArrayList<UNOPlayer>();
		UNOplayers.addAll(players);
		currentPlayerToPlay = new UNOPlayer();
		currentPlayerToPlayIndex = 0;
	}
	
	//Methods for UNO
	
	public UNODeck getUNODeck() {
		return GameUNODeck;
	}
	
	public ArrayList<UNOPlayer> getPlayerList(){
		return UNOplayers;
	}
	
	public UNOPlayer getCurrentPlayerToPlay() {
		return currentPlayerToPlay;
	}
	
	public void setCurrentPlayerToPlay(UNOPlayer player) {
		currentPlayerToPlay = player;
	}
	
	public String getCurrentTurnColor() {
		return currentTurnColor;
	}
	
	public void setCurrentTurnColor(String color) {
		currentTurnColor = color;
	}
	
	public void DealCards() {  // Deals 7 cards to each player to start the game
		for (int i = 1; i <= 7; i++) {
			for (int j = 0; j < UNOplayers.size(); j++) {
				UNOplayers.get(j).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
			}
		}
		GameUNODeck.addUNOCardToDiscardPile(GameUNODeck.DealAUNOCard());
		currentTurnColor = GameUNODeck.getUNODiscardPile().get(0).getColor();
	}
	
	public void PlayerToPlayNext(UNOCard aCard) { // Dertermines the next player to play
		
		if (aCard.getName().equals("Reverse") && UNOplayers.size() == 2) {
			return;
		}
		
		if (gameDirection.equals("right")) { // If Game is moving in one direction
			currentPlayerToPlayIndex++;
			if (currentPlayerToPlayIndex >= UNOplayers.size()) {
				currentPlayerToPlayIndex = 0;
			}
			if (currentPlayerToPlayIndex < 0) {
				currentPlayerToPlayIndex = UNOplayers.size() - 1;
			}
		}
		
		if (gameDirection.equals("left")) { // If Game is moving in other direction
			currentPlayerToPlayIndex--;
			if (currentPlayerToPlayIndex >= UNOplayers.size()) {
				currentPlayerToPlayIndex = 0;
			}
			if (currentPlayerToPlayIndex < 0) {
				currentPlayerToPlayIndex = UNOplayers.size() - 1;
			}
		}
		
		currentPlayerToPlay = UNOplayers.get(currentPlayerToPlayIndex); // Next Player to play a card
	}
	
	public boolean CheckIfCardPlayedIsValid(UNOCard aCard) { //Checks if the card played is valid and if true it adds that card to the discard pile
		
		if (GameUNODeck.getUNODiscardPile().get(GameUNODeck.getUNODiscardPile().size() - 1).getName().equals(aCard.getName())) { // If card has the same value as last card played it is valid
			GameUNODeck.addUNOCardToDiscardPile(aCard);
			currentTurnColor = aCard.getColor();
			if (aCard.getName().equals("Skip") || aCard.getName().equals("Reverse") || aCard.getName().equals("Draw_Two")) { //if it is a special card it needs to have the action performed
				SpecialCardAction(aCard);
			}
			return true;
		}
		else if (currentTurnColor.equals(aCard.getColor())) { // if card is the same color as last card played's color it is valid
			GameUNODeck.addUNOCardToDiscardPile(aCard);
			if (aCard.getName().equals("Skip") || aCard.getName().equals("Reverse") || aCard.getName().equals("Draw_Two")) { //if it is a special card it needs to have the action performed
				SpecialCardAction(aCard);
			}
			return true;
		}
		else if (aCard.getName().equals("Wild") || aCard.getName().equals("Wild_Draw_Four")) { // if it is a wild or wild draw four card it is valid and performs its special action
			SpecialCardAction(aCard);
			GameUNODeck.addUNOCardToDiscardPile(aCard);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean CalculatePointsFromEachPlayerToWinner(UNOPlayer winner) { //Adding all the points from everyones cards in their hand to the winner of that hand score
		boolean playerHasWon = false;
		
		for (int i = 0; i < UNOplayers.size(); i++) {  
			if (!UNOplayers.get(i).getName().equals(winner.getName())) {
				for (int j = 0; j < UNOplayers.get(i).getUNOPlayersHand().size(); j++) {
					winner.setPlayerScore(UNOplayers.get(i).getUNOPlayersHand().get(j).getValue() + winner.getPlayerScore());
			}
		}
	}
		
		if (winner.getPlayerScore() > 500) {
			playerHasWon = true;
		}
		
		return playerHasWon;
	}
	public boolean CheckForUNO(UNOPlayer aPlayer) { //Checks to see if the player who called UNO really only has one card left
		if (aPlayer.getUNOPlayersHand().size() == 1) {
			return true;
		}
		else {
			aPlayer.getUNOPlayersHand().add(GameUNODeck.DealAUNOCard()); // IF player called for UNO and really did not have UNO, the player is dealt two cards
			aPlayer.getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
			return false;
		}
	}
			
	public void SpecialCardAction(UNOCard aCard) { //Performs the action of a special card => Skip, Draw Two, Reverse, Wild Draw Four
		
		if (aCard.getName().equals("Reverse")) {  // Reverse card is played, so game direction is switched
			if (gameDirection.equals("right")) {
				gameDirection = "left";
			}
			else {
				gameDirection = "right";
			}
		}
		
		if (aCard.getName().equals("Skip")) {
			if (gameDirection.equals("right")) { // If Game is moving in one direction
				currentPlayerToPlayIndex = currentPlayerToPlayIndex + 1; // player next to play is skipped
				if (currentPlayerToPlayIndex >= UNOplayers.size()) {
					currentPlayerToPlayIndex = currentPlayerToPlayIndex - UNOplayers.size();
				}
			}
			
			if (gameDirection.equals("left")) { // If Game is moving in other direction
				currentPlayerToPlayIndex = currentPlayerToPlayIndex - 1; // player next to play is skipped
				if (currentPlayerToPlayIndex <= 0) {
					currentPlayerToPlayIndex = UNOplayers.size() - currentPlayerToPlayIndex;
				}
			}
		}
		
		if (aCard.getName().equals("Draw_Two")) {  // If a Draw Two Card is played, the enxt player to play is dealt two cards
			if (gameDirection.equals("right")) {
				if (currentPlayerToPlayIndex < UNOplayers.size() - 1) {
					UNOplayers.get(currentPlayerToPlayIndex + 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(currentPlayerToPlayIndex + 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
				else {
					UNOplayers.get(0).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(0).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
			}
			
			if (gameDirection.equals("left")) {
				if (currentPlayerToPlayIndex > 0) {
					UNOplayers.get(currentPlayerToPlayIndex - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(currentPlayerToPlayIndex - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
				else {
					UNOplayers.get(UNOplayers.size() - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(UNOplayers.size() - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
			}
		}
		
		if (aCard.getName().equals("Wild_Draw_Four")) {
			if (gameDirection.equals("right")) { // if game direction is this way
				if (currentPlayerToPlayIndex < UNOplayers.size()- 1) {
					UNOplayers.get(currentPlayerToPlayIndex + 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(currentPlayerToPlayIndex + 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(currentPlayerToPlayIndex + 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard()); // draw four cards
					UNOplayers.get(currentPlayerToPlayIndex + 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
				else {
					UNOplayers.get(0).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(0).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard()); // draw four cards
					UNOplayers.get(0).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(0).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
			}
			
			if (gameDirection.equals("left")) { // if game direction is this way
				if (currentPlayerToPlayIndex > 0) {
					UNOplayers.get(currentPlayerToPlayIndex - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(currentPlayerToPlayIndex - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard()); // draw four cards
					UNOplayers.get(currentPlayerToPlayIndex - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(currentPlayerToPlayIndex - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
				else {
					UNOplayers.get(UNOplayers.size() - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(UNOplayers.size() - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
					UNOplayers.get(UNOplayers.size() - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard()); // draw four cards
					UNOplayers.get(UNOplayers.size() - 1).getUNOPlayersHand().add(GameUNODeck.DealAUNOCard());
				}
			}
		}
		
	}
	
	
	
	

}
