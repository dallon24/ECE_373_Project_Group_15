package org.Final_Project.Game;

import org.Final_Project.Deck.*;
import org.Final_Project.Players.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.util.ArrayList;

public class Game24 {
	private Deck deck24;
	private ArrayList<String> acceptableOperations;
	private ArrayList<String> currentOperations;
	private ArrayList<Integer> currentCardValues;
	private ArrayList<Player24> players24;
	private HotPlayer24 hotPlayer;
	
	public Game24(){
		super();
		//setGame24Deck(new Deck24());
		deck24 = new Deck();
		deck24.Shuffle();
		acceptableOperations = new ArrayList<String>();
		acceptableOperations.add("+");
		acceptableOperations.add("-");
		acceptableOperations.add("*");
		acceptableOperations.add("/");
		acceptableOperations.add("^");
		acceptableOperations.add("sqrt");
		acceptableOperations.add("(");
		acceptableOperations.add(")");
		currentOperations = new ArrayList<String>();
		currentCardValues = new ArrayList<Integer>();
		players24 = new ArrayList<Player24>();
		hotPlayer = new HotPlayer24();
	}
	public boolean checkEquation(String equation) {
		equation = equation.replaceAll("[()]", "");
		String [] split = equation.split("\\s+");

		//int a, b, c, d;
		String opAB, opBC, opCD;
		if (split.length == 7) {
			//int 1
//			a = Integer.parseInt(split[0]);
//			currentCardValues.add(a);
			//operation 1
			opAB = split[1];
			currentOperations.add(opAB);
			//int 2
//			b = Integer.parseInt(split[2]);
//			currentCardValues.add(b);
			//operation 2
			opBC = split[3];
			currentOperations.add(opBC);
			//int 3
//			c = Integer.parseInt(split[4]);
//			currentCardValues.add(c);
			//operation 3
			opCD = split[5];
			currentOperations.add(opCD);
			//int 4
//			d = Integer.parseInt(split[6]);
//			currentCardValues.add(d);
//			System.out.println(opAB + opBC + opCD);
//			System.out.println(currentOperations.size());
			
			printOperations(currentOperations);
//			printCardValues(currentCardValues);
			
			if (!checkOperation(opAB) || !checkOperation(opBC) || !checkOperation(opCD)) {
				System.out.println("Invalid operation");
				currentOperations.clear();
				currentCardValues.clear();
				System.out.println(currentOperations.size() + " " + currentCardValues.size());
				return false;
			}
		}
		else {
			System.out.println("too many or not enough commands");
			return false;
		}
		return true;
	}

	public boolean check24(String equation) throws ScriptException {
		//"a + b + c + d" ?= 24
		if (!checkEquation(equation)) {
			return false;
		}
		else {
			Object result = calculateEquation(equation);
			if (!checkObject(result)) {
				System.out.println("Error: the result of the expression is not an integer, result is " + result);
				return false;
			}
			if (!result.equals(24)){
				System.out.println("Error: the expression does not equal 24, result is " + result);
				return false;
			}
		}
		System.out.println("You made 24!");
		return true;
	}

	public Object calculateEquation(String equation) throws ScriptException {
		//double result = 0.0;
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Object result = engine.eval(equation);
		return result;
	}
	public boolean checkObject(Object result) {
		if (result instanceof Integer) {
			return true;
		}
		return false;
	}
	public boolean checkValidTurn(Player24 player) {
		return true;
	}
	public boolean checkOperation(String operation) {
		for (int i = 0; i < acceptableOperations.size(); i++) {
			if (operation.equals(acceptableOperations.get(i))) {
				return true;
			}
		}
		return false;
	}

	public void displayScores() {
		//consider moving to specific places in JFrame
		System.out.println(players24.get(0).getScore());
		System.out.println(players24.get(1).getScore());
		System.out.println(players24.get(2).getScore());
		System.out.println(players24.get(3).getScore());
	}

	public int calculatePoints(ArrayList<String> operations) { //removed number of cards because variable card length calculation would be hell
		//only calculate points if 24
		int points = 0;
		for(String s : operations) {
			if (s == "+") {
				points += 10;
			}
			else if (s == "-") {
				points += 10;
			}
			else if (s == "*") {
				points += 20;
			}
			else if (s == "/") {
				points += 20;
			}
			else if (s == "^") {
				points += 40;
			}
			else if (s == "sqrt") {
				points += 40;
			}
			else {
			
			}
		}
		return points;
	}

	//play main menu ->
	//play game24 main menu
		//has three buttons: play, return to main menu, and exit entire game
	//play -> instruction screen [potentially multipage]
	//instruction has start button
	//start button -> game screen
	//deal 4 cards at once (4 buttons for cards, 5-6 buttons for operations)
	//redeal button (if stuck)
	//player push [assigned] button to enter equation, check if player can make turn
	//timer countdown starts, 20 seconds
		//click card + click operations
			//clicking a card again deselects card
			//current equation will update and be visible with every click
		//make a string using equations that sends into check24
		//if player makes 24 with the 4 cards and operations, add points to specific player
	//if timer ends, player's turn is invalid for rest of the round
	public void play24() {
		Deck deck = new Deck();
		deck.Shuffle();
		
		Card a = new Card();
		Card b = new Card();
		Card c = new Card();
		Card d = new Card();

		a = deck.DealACard();
		b = deck.DealACard();
		c = deck.DealACard();
		d = deck.DealACard();
		
		
	}
	public void printEquation(String equation) {
		System.out.println(equation);
	}

	public void printInstruction() {
		System.out.println("24 Game Rules:");
		System.out.println(
				"Four cards and a list of operations will be displayed. The goal is to make the number 24 with these 4 cards using 3 operations (parentheses allowed.");
		System.out.println(
				"For example, (4 + 2) * 2 + 12 = 24. Aces shall have the value of 1, Jacks 11, Queens 12, and Kings 13. There shall be 4 players to this game and each player will be assigned the buttons.");
		System.out.println("Player 1: Q \nPlayer 2: C \nPlayer 3: N \nPlayer 4: O");
		System.out.println(
				"When a player has come up with the solution, the player can press their respective button to make 24");
		System.out.println("After a player triggers their respective button, a 20 second timer will count down for the player to make 24");
		System.out.println(
				"The player will click the card/operation buttons in the order which they want the equation to be. For helpful guidance on what has been entered so far");
		System.out.println("a text will update as the player presses every card/operation. If the player fails to make 24 within the 20 seconds, they will be locked out of their trigger button for the remainder of that turn");
		System.out.println("once that turn is completed, any locked out players will be unlocked for the following turn");
		System.out.println("If the player makes 24 within the 20 seconds, points will be calculated and added to the player that successfully solved for 24");
		System.out.println("The game ends after the entire deck is cycled through, or when a player manually clicks the end game button.");
	}
	
	
	public void printOperations(ArrayList<String> operationList) {
		System.out.println("Printing operations:");
		for(String s : operationList) {
			System.out.println(s);
		}
	}
	public void printCardValues(ArrayList<Integer> cardValues) {
		System.out.println("Printing card values:");
		for(Integer i : cardValues) {
			System.out.println(i);
		}
		
	}
	public Deck getGame24Deck() {
		return deck24;
	}
	public void setGame24Deck(Deck deck24) {
		this.deck24 = deck24;
	}
	public void dealFour() {
		
	}
	public ArrayList<Player24> getPlayers24() {
		return players24;
	}
	public void setPlayers24(ArrayList<Player24> players24) {
		this.players24 = players24;
	}
	public ArrayList<String> getAcceptableOperations() {
		return acceptableOperations;
	}
	public void setAcceptableOperations(ArrayList<String> acceptableOperations) {
		this.acceptableOperations = acceptableOperations;
	}
	public ArrayList<String> getCurrentOperations() {
		return currentOperations;
	}
	public void setCurrentOperations(ArrayList<String> currentOperations) {
		this.currentOperations = currentOperations;
	}

}
