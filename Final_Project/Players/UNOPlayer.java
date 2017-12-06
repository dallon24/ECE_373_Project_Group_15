package org.Final_Project.Players;

import java.util.ArrayList;


import org.Final_Project.Deck.UNOCard;

public class UNOPlayer extends Player{   //subclass of Player

	//Fields for UNOPlayer
	private ArrayList<UNOCard> unoPlayersHand;
	private boolean userCalledUNO;
	
	//Constructor
	public UNOPlayer() {
		unoPlayersHand = new ArrayList<UNOCard>();
		userCalledUNO = false;
	}
	
	//Methods for UNOPlayere
	public ArrayList<UNOCard> getUNOPlayersHand(){
		return unoPlayersHand;
	}
	public void addToUNOPlayersHand(UNOCard aCard) {
		unoPlayersHand.add(aCard);
	}
	public boolean didUserCallUNO() {
		return userCalledUNO;
	}
	public void setUserCallUNO(boolean userChoice) {
		userCalledUNO = userChoice;
	}
	
}
