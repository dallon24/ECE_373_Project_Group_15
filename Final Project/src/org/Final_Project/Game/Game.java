package org.Final_Project.Game;

import java.util.ArrayList;

import org.Final_Project.Players.Player;

public class Game {

	//Fields for Game
	protected ArrayList<Player> players;
	
	//Constructor
	public Game() {
		players = new ArrayList<Player>();
	}
	
	//Methods for Game
	public Player getPlayer(int index) {
		return players.get(index);
	}
	public void addPlayer(Player newPlayer) {
		players.add(newPlayer);
	}
}
