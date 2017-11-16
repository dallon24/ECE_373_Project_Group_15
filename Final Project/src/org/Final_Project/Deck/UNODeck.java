package org.Final_Project.Deck;

import java.util.ArrayList;
import java.util.Random;

public class UNODeck extends Deck{   //subclass of Deck
	
	//Fields for UNODeck
	private ArrayList<UNOCard> UNODeck;
	private ArrayList<UNOCard> unoDiscardPile;
	private Random rand;
	
	//Constructor
	public UNODeck() {
		UNODeck = new ArrayList<UNOCard>();
		unoDiscardPile = new ArrayList<UNOCard>();
		rand = new Random();
		
		//Creating the 0 number UNOCards, there are 4 zero UNOCards, one of each color
		UNODeck.add(new UNOCard("Zero", "Red", 0));
		UNODeck.add(new UNOCard("Zero", "Yellow", 0));
		UNODeck.add(new UNOCard("Zero", "Blue", 0));
		UNODeck.add(new UNOCard("Zero", "Green", 0));
		
		//Creating all the 1-9 number UNOCards; There are 2 of each number UNOCard of each color => four UNOCards for each number
		for (int i = 1; i <=9; i++) {
			for (int j = 0; j < 2; j++) {
				if(i == 1) {
					UNODeck.add(new UNOCard("One", "Red", 1));
					UNODeck.add(new UNOCard("One", "Yellow", 1));
					UNODeck.add(new UNOCard("One", "Blue", 1));
					UNODeck.add(new UNOCard("One", "Green", 1));
				}
				if(i == 2) {
					UNODeck.add(new UNOCard("Two", "Red", 2));
					UNODeck.add(new UNOCard("Two", "Yellow", 2));
					UNODeck.add(new UNOCard("Two", "Blue", 2));
					UNODeck.add(new UNOCard("Two", "Green", 2));
				}
				if(i == 3) {
					UNODeck.add(new UNOCard("Three", "Red", 3));
					UNODeck.add(new UNOCard("Three", "Yellow", 3));
					UNODeck.add(new UNOCard("Three", "Blue", 3));
					UNODeck.add(new UNOCard("Three", "Green", 3));
				}
				if(i == 4) {
					UNODeck.add(new UNOCard("Four", "Red", 4));
					UNODeck.add(new UNOCard("Four", "Yellow", 4));
					UNODeck.add(new UNOCard("Four", "Blue", 4));
					UNODeck.add(new UNOCard("Four", "Green", 4));
				}
				if(i == 5) {
					UNODeck.add(new UNOCard("Five", "Red", 5));
					UNODeck.add(new UNOCard("Five", "Yellow", 5));
					UNODeck.add(new UNOCard("Five", "Blue", 5));
					UNODeck.add(new UNOCard("Five", "Green", 5));
				}
				if(i == 6) {
					UNODeck.add(new UNOCard("Six", "Red", 6));
					UNODeck.add(new UNOCard("Six", "Yellow", 6));
					UNODeck.add(new UNOCard("Six", "Blue", 6));
					UNODeck.add(new UNOCard("Six", "Green", 6));
				}
				if(i == 7) {
					UNODeck.add(new UNOCard("Seven", "Red", 7));
					UNODeck.add(new UNOCard("Seven", "Yellow", 7));
					UNODeck.add(new UNOCard("Seven", "Blue", 7));
					UNODeck.add(new UNOCard("Seven", "Green", 7));
				}
				if(i == 8) {
					UNODeck.add(new UNOCard("Eight", "Red", 8));
					UNODeck.add(new UNOCard("Eight", "Yellow", 8));
					UNODeck.add(new UNOCard("Eight", "Blue", 8));
					UNODeck.add(new UNOCard("Eight", "Green", 8));
				}
				if(i == 9) {
					UNODeck.add(new UNOCard("Nine", "Red", 9));
					UNODeck.add(new UNOCard("Nine", "Yellow", 9));
					UNODeck.add(new UNOCard("Nine", "Blue", 9));
					UNODeck.add(new UNOCard("Nine", "Green", 9));
				}
			}
		}
		
		//Creating all the special UNOCards: Skip, Reverse, and Draw 2 => there are two UNOCard of each color of each UNOCard
		for (int i = 0; i < 2; i++) {
			UNODeck.add(new UNOCard("Skip", "Red", 20));
			UNODeck.add(new UNOCard("Skip", "Yellow", 20));  //Skip UNOCards
			UNODeck.add(new UNOCard("Skip", "Blue", 20));
			UNODeck.add(new UNOCard("Skip", "Green", 20));
			
			UNODeck.add(new UNOCard("Reverse", "Red", 20));
			UNODeck.add(new UNOCard("Reverse", "Yellow", 20));  // Reverse UNOCards
			UNODeck.add(new UNOCard("Reverse", "Blue", 20));
			UNODeck.add(new UNOCard("Reverse", "Green", 20));
			
			UNODeck.add(new UNOCard("Draw Two", "Red", 20));
			UNODeck.add(new UNOCard("Draw Two", "Yellow", 20));  // Draw Two UNOCards
			UNODeck.add(new UNOCard("Draw Two", "Blue", 20));
			UNODeck.add(new UNOCard("Draw Two", "Green", 20));
		}
		
		//Creating the Wild and Draw Four Wild UNOCards => There are four of each UNOCard
		for (int i = 0; i < 4; i++) {
			UNODeck.add(new UNOCard("Wild", "None", 50));
			UNODeck.add(new UNOCard("Wild Draw Four", "None", 50));
		}
		
		//Check for error in creating Deck
		if (UNODeck.size() != 108) {
			System.out.println("Error in creating UNODeck");
		}
		else {
			//Shuffle();
		}
	}
	
	//Methods for UNODeck
	
	public ArrayList<UNOCard> getUNOCardPile() {
		return UNODeck;
	}
	public ArrayList<UNOCard> getUNODiscardPile() {
		return unoDiscardPile;
	}
	
	//Shuffles a UNODeck. Having a Shuffle Factor of "1" gives a good shuffled deck, but we can increase this to have a more shuffled deck
		public void Shuffle() {
			int shuffleFactor = 1;
			for (int i = 0; i < (shuffleFactor*UNODeck.size()); i++) {
				int x = rand.nextInt(UNODeck.size());
				int y = rand.nextInt(UNODeck.size());
				UNOCard temp = UNODeck.get(x);
				UNODeck.set(x, UNODeck.get(y));
				UNODeck.set(y, temp);
			}
		}
		//Deals a card from the UNODeck
		public UNOCard DealAUNOCard() {
			if (isEmpty()) {
				OutOfUNOCardsReShuffle();
			}
			
			unoDiscardPile.add(UNODeck.get(0));
			return UNODeck.remove(0);
		}
		
		//Checks if the UNODeck is empty
		public boolean isEmpty() {
			return (UNODeck.size() == 0);
		}
		
		//WE ran out of cards, so we grab cards from the discard pile and puts it in the game deck and reshuffles
		public void OutOfUNOCardsReShuffle() {
			System.out.println("Out of Cards! Need to Reshuffle.");
			UNODeck.addAll(unoDiscardPile);
			unoDiscardPile.clear();
			Shuffle();
		}
		
		public void PrintUNODeck(ArrayList<UNOCard> deck) {
			for(UNOCard c : deck) {
				if (c.getColor() == "None") {
					System.out.println(c.getName());
				}
				else {
					System.out.println(c.getColor() + " " + c.getName());
				}
			
			}
		}
		
		public void PrintCard(UNOCard c) {
			System.out.println("Dealing a card: " + c.getColor() + " " + c.getName());
		}
}
