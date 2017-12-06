package org.Final_Project.Deck;

public class Card {
	
	//fields for Card
	private String name;
	private String suit;
	private int value;
	
	//Card Contructor
	public Card(){
		name = "unknown name";
		suit = "unknown suit";
		value = 0;
	}
	public Card(String aName, String aSuit, int aValue) {
		name = aName;
		suit = aSuit;
		value = aValue;
	}
	
	//Methods for Card
	public String getName() {
		return name;
	}
	public void setName(String aName) {
		name = aName;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String aSuit) {
		suit = aSuit;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int aValue) {
		value = aValue;
	}

}
