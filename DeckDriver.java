package org.Final_Project.Deck;

public class DeckDriver {
    
	
	public static void main(String[] args) {
		//TODO:testing constructor
		System.out.println("Testing Constructor:");
		Deck deck = new Deck();
		Deck shuffleDeck = new Deck();
		shuffleDeck.PrintDeck(shuffleDeck.getCardPile());
		
		//TODO:testing shuffle method 
		System.out.println("\nTesting Shuffle:");
		shuffleDeck.Shuffle();
		shuffleDeck.PrintDeck(shuffleDeck.getCardPile());
		
		//TODO:testing card deal and discard pile
		System.out.println("\nTesting card deal and discard pile:");
		Card c = deck.DealACard();
		deck.PrintCard(c);
		System.out.println("\nPrinting the discard pile:");
		deck.PrintDeck(deck.getDiscardPile());
		
		System.out.println("\nTesting reshuffle:");
		for (int i = 0; i < 51; i++) {

			c = deck.DealACard();
			
			System.out.println(c.getName() + " of " + c.getSuit() + " is the card dealt. " + deck.getCardPile().size() + " cards left. " );
		}
		
		System.out.println("\nDiscard pile has " + deck.getDiscardPile().size() + " cards left");
		System.out.println("Dealing one card with no cards left: \n");
		c = deck.DealACard();
		
		System.out.println("Card pile has " + deck.getCardPile().size() + " cards now");
		System.out.println("Discard pile has " + deck.getDiscardPile().size() + " cards now");
		
	}
	
}
