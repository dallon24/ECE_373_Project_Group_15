package org.Final_Project.Deck;

public class UNODeckDriver {
	
	public static void main(String[] args) {
		
		//TODO:testing constructor
		System.out.println("Testing Constructor:");
		UNODeck deck = new UNODeck();
		UNODeck shuffleDeck = new UNODeck();
		shuffleDeck.PrintUNODeck(shuffleDeck.getUNOCardPile());
		
		//TODO:testing shuffle method 
		System.out.println("\nTesting Shuffle:");
		shuffleDeck.Shuffle();
		shuffleDeck.PrintUNODeck(shuffleDeck.getUNOCardPile());
		
		//TODO:testing card deal and discard pile
		System.out.println("\nTesting card deal and discard pile:");
		UNOCard c = deck.DealAUNOCard();
		deck.PrintCard(c);
		System.out.println("\nPrinting the discard pile:");
		deck.PrintUNODeck(deck.getUNODiscardPile());
		
		System.out.println("\nTesting reshuffle:");
		for (int i = 0; i < 107; i++) {

			c = deck.DealAUNOCard();
			
			System.out.println(c.getColor() + " " + c.getName() + " is the card dealt. " + deck.getUNOCardPile().size() + " cards left. " );
		}
		
		System.out.println("\nDiscard pile has " + deck.getUNODiscardPile().size() + " cards left");
		System.out.println("Dealing one card with no cards left: \n");
		c = deck.DealAUNOCard();
		
		System.out.println("Card pile has " + deck.getUNOCardPile().size() + " cards now");
		System.out.println("Discard pile has " + deck.getUNODiscardPile().size() + " cards now");
		
	}
}
