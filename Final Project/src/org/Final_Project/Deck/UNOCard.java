package org.Final_Project.Deck;

public class UNOCard extends Card{

	//Fields for UNOCard
	private String color;
	
	//Constructor
	public UNOCard() {
		
	}
	public UNOCard(String aName, String aColor, int aValue) {
		 setName(aName);
		color = aColor;
		setSuit("unknown suit");
		setValue(aValue);
	}
	
	//Methods
	public String getColor() {
		return color;
	}
	public void setColor(String aColor) {
		color = aColor;
	}
}
