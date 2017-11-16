package org.Final_Project.Players;

import java.util.ArrayList;


import org.Final_Project.Deck.UNOCard;

public class UNOPlayer extends Player{   //subclass of Player

	//Fields for UNOPlayer
	private ArrayList<UNOCard> unoPlayersHand;
	
	//Constructor
	public UNOPlayer() {
		unoPlayersHand = new ArrayList<UNOCard>();
	}
	
	//Methods for UNOPlayer
	public ArrayList<UNOCard> getUNOPlayersHand(){
		return unoPlayersHand;
	}
	public void addToUNOPlayersHand(UNOCard aCard) {
		unoPlayersHand.add(aCard);
	}
	
}
