package org.Final_Project.Deck;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	//Fields for Deck
	private ArrayList<Card> cardPile;
	private ArrayList<Card> discardPile;
	private Random rand;
	
	//Constructor
	public Deck() {
		cardPile = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		rand = new Random();
		
		//Creating Poker Cards;
		for (int i = 1; i <=13; i++) {
			if(i == 1) {
				cardPile.add(new Card("Ace", "Spade", i));
				cardPile.add(new Card("Ace", "Heart", i));
				cardPile.add(new Card("Ace", "Club", i));
				cardPile.add(new Card("Ace", "Diamond", i));
			}
			if(i == 2) {
				cardPile.add(new Card("Two", "Spade", 2));
				cardPile.add(new Card("Two", "Heart", 2));
				cardPile.add(new Card("Two", "Diamond", 2));
				cardPile.add(new Card("Two", "Club", 2));
			}
			if(i == 3) {
				cardPile.add(new Card("Three", "Spade", 3));
				cardPile.add(new Card("Three", "Heart", 3));
				cardPile.add(new Card("Three", "Diamond", 3));
				cardPile.add(new Card("Three", "Club", 3));
			}
			if(i == 4) {
				cardPile.add(new Card("Four", "Spade", 4));
				cardPile.add(new Card("Four", "Heart", 4));
				cardPile.add(new Card("Four", "Diamond", 4));
				cardPile.add(new Card("Four", "Club", 4));
			}
			if(i == 5) {
				cardPile.add(new Card("Five", "Spade", 5));
				cardPile.add(new Card("Five", "Heart", 5));
				cardPile.add(new Card("Five", "Diamond", 5));
				cardPile.add(new Card("Five", "Club", 5));
			}
			if(i == 6) {
				cardPile.add(new Card("Six", "Spade", 6));
				cardPile.add(new Card("Six", "Heart", 6));
				cardPile.add(new Card("Six", "Diamond", 6));
				cardPile.add(new Card("Six", "Club", 6));
			}
			if(i == 7) {
				cardPile.add(new Card("Seven", "Spade", 7));
				cardPile.add(new Card("Seven", "Heart", 7));
				cardPile.add(new Card("Seven", "Diamond", 7));
				cardPile.add(new Card("Seven", "Club", 7));
			}
			if(i == 8) {
				cardPile.add(new Card("Eight", "Spade", 8));
				cardPile.add(new Card("Eight", "Heart", 8));
				cardPile.add(new Card("Eight", "Diamond", 8));
				cardPile.add(new Card("Eight", "Club", 8));
			}
			if(i == 9) {
				cardPile.add(new Card("Nine", "Spade", 9));
				cardPile.add(new Card("Nine", "Heart", 9));
				cardPile.add(new Card("Nine", "Diamond", 9));
				cardPile.add(new Card("Nine", "Club", 9));
			}
			if(i == 10) {
				cardPile.add(new Card("Ten", "Spade", 10));
				cardPile.add(new Card("Ten", "Heart", 10));
				cardPile.add(new Card("Ten", "Diamond", 10));
				cardPile.add(new Card("Ten", "Club", 10));
			}
			if(i == 11) {
				cardPile.add(new Card("Jack", "Spade", 11));
				cardPile.add(new Card("Jack", "Heart", 11));
				cardPile.add(new Card("Jack", "Diamond", 11));
				cardPile.add(new Card("Jack", "Club", 11));
			}
			if(i == 12) {
				cardPile.add(new Card("Queen", "Spade", 12));
				cardPile.add(new Card("Queen", "Heart", 12));
				cardPile.add(new Card("Queen", "Diamond", 12));
				cardPile.add(new Card("Queen", "Club", 12));
			}
			if(i == 13) {
				cardPile.add(new Card("King", "Spade", 13));
				cardPile.add(new Card("King", "Heart", 13));
				cardPile.add(new Card("King", "Diamond", 13));
				cardPile.add(new Card("King", "Club", 13));
			}
		}
		
		//Check for error in creating Deck
		if (cardPile.size() != 52) {
			System.out.println("Error in creating cardPile");
		}
		else {
			//TODO: uncomment for testing
			//PrintDeck(cardPile);
			Shuffle();
			//PrintDeck(cardPile);
		}
		
	}
	
	//Methods for Deck
	
	
	public ArrayList<Card> getCardPile() {
		return cardPile;
	}
	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}
	
	//Shuffles a Deck. Having a Shuffle Factor of "1" gives a good shuffled deck, but we can increase this to have a more shuffled deck
	public void Shuffle() {
		int shuffleFactor = 1;
		for (int i = 0; i < (shuffleFactor*cardPile.size()); i++) {
			int x = rand.nextInt(cardPile.size());
			int y = rand.nextInt(cardPile.size());
			Card temp = cardPile.get(x);
			cardPile.set(x, cardPile.get(y));
			cardPile.set(y, temp);
		}
	}
	
	//Deals a card from the Deck
	public Card DealACard() {
		if (cardPile.size() == 0) {
			OutOfCardsReShuffle();
		}
		addCardToDiscardPile(cardPile.get(0));
		return cardPile.remove(0);
	}
	
	public void addCardToDiscardPile(Card c) {
		discardPile.add(c);
	}
	
	//Checks if the Deck is empty
	public boolean isEmpty() {
		return (cardPile.size() == 0);
	}
	
	//WE ran out of cards, so we grab cards from the discard pile and puts it in the game deck and reshuffles
	public void OutOfCardsReShuffle() {
		System.out.println("Out of cards! Do the truffle reshuffle!");
		cardPile.addAll(discardPile);
		discardPile.clear();
		Shuffle();
	}
	public void PrintDeck(ArrayList<Card> deck) {
		for(Card c : deck) {
			System.out.println(c.getName() + " of " + c.getSuit() );
		
		}
	}
	public void PrintCard(Card c) {
		System.out.println("Dealing a card: " + c.getName() + " of " + c.getSuit());
	}
	
}
