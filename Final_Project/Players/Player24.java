package org.Final_Project.Players;

public class Player24 {
	private boolean turn;
	private int score;
	
	public Player24() {
		super();
		turn = true;
		score = 0;
	}
	
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
