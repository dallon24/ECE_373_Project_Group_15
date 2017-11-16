package org.Final_Project.Deck;

public class Deck24 {
	private int numDecks;
	private int numCardsOnField;
	
	public Deck24() {
		super();
		numDecks = 1;
		numCardsOnField = 4;
	}
	
	
	public int getNumCardsOnField() {
		return numCardsOnField;
	}
	public void setNumCardsOnField(int numCardsOnField) {
		this.numCardsOnField = numCardsOnField;
	}
	public int getNumDecks() {
		return numDecks;
	}
	public void setNumDecks(int numDecks) {
		this.numDecks = numDecks;
	}
	
}
