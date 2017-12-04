package org.Final_Project.Players;

import org.Final_Project.Deck.*;

public class PlayerDriver {
	public static void main(String[] args) {
		//TODO: testing constructor
		System.out.println("Testing constructor:");
		Player p = new Player();
		System.out.println("Player's name: " + p.getName());
		System.out.println("Player's hand size: " + p.getPlayersHand().size());
		System.out.println("Player's score " + p.getPlayerScore() + "\n");
		
		
		//TODO: testing setters and adders
		System.out.println("Testing setters and adding to hand:");
		Card c1 = new Card();
		c1.setName("Ace");
		c1.setSuit("Spades");
		Card c2 = new Card();
		c2.setName("King");
		c2.setSuit("Diamonds");
	
		p.setName("Hiccup");
		p.setPlayerScore(9001);
		p.addToPlayersHand(c1);
		p.addToPlayersHand(c2);
		
		System.out.println("Player's name: " + p.getName());
		System.out.println("Player's score " + p.getPlayerScore());
		//TODO: testing printHand
		System.out.println("Testing printHand:");
		p.printHand();
		
		//TODO: testing emptyHand
		System.out.println("\nTesting emptyHand: ");
		System.out.println("Numbers of cards before: " + p.getPlayersHand().size());
		p.emptyHand();
		System.out.println("Numbers of cards after: " + p.getPlayersHand().size());
	}
}
