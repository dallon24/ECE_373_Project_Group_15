package org.Final_Project.Players;

import java.util.ArrayList;

import org.Final_Project.Deck.*;

public class Player {
	
	//Fields for Player
	private String name;
	private ArrayList<Card> playersHand;
	private int playerScore;
	
	//Constructor
	public Player() {
		name = "unknown name";
		playersHand = new ArrayList<Card>();
		playerScore = 0;
	}
	
	//Methods for Player
	public String getName() {
		return name;
	}
	public void setName(String aName) {
		name = aName;
	}
	public int getPlayerScore() {
		return playerScore;
	}
	public void setPlayerScore(int score) {
		playerScore = score;
	}
	public ArrayList<Card> getPlayersHand(){
		return playersHand;
	}
	public void addToPlayersHand(Card aCard) {
		playersHand.add(aCard);
	}
	public void emptyHand() {
		playersHand.clear();
	}
	public void printHand() {
		for (Card c : playersHand) {
			System.out.println("Player's hand: " + c.getName() + " of " + c.getSuit());
		}
	}

}