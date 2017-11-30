package org.Final_Project.Game;

import org.Final_Project.Deck.*;
import org.Final_Project.Players.*;
import java.util.ArrayList;

public class Game24 {
	private Deck24 game24Deck;
	private ArrayList<String> acceptableOperations;
	private ArrayList<String> currentOperations;
	private ArrayList<Player24> players24;
	private HotPlayer24 hotPlayer;
	
	public Game24(){
		super();
		game24Deck = new Deck24();
		acceptableOperations = new ArrayList<String>();
		players24 = new ArrayList<Player24>();
		hotPlayer = new HotPlayer24();
	}
	public boolean CheckEquation(String equation) {
		String [] split = equation.split("\\s+");
		
		int a, b, c, d;
		String opAB, opBC, opCD;
		
		a = Integer.parseInt(split[0]);
		//operation 1
		opAB = split[1];
		b = Integer.parseInt(split[2]);
		//operation 2
		c = Integer.parseInt(split[4]);
		//operation 3
		d = Integer.parseInt(split[6]);
		
		return true;
	}
	
	public boolean Check24(String equation) {
		//"a + b + c + d" ?= 24
		String [] split = equation.split("\\s+");
		
		int a, b, c, d;
		a = Integer.parseInt(split[0]);
		//operation 1
		b = Integer.parseInt(split[2]);
		//operation 2
		c = Integer.parseInt(split[4]);
		//operation 3
		d = Integer.parseInt(split[6]);
		
		
		
		return true;
	}
	
	public void DisplayScores() {

	}

	public int CalculatePoints(ArrayList<String> operations) { //removed number of cards because variable card length calculation would be hell
		return 0;
	}
	public boolean CheckValidTurn(Player24 player) {
		return true;
	}
	public void Play() {
		
	}

}
