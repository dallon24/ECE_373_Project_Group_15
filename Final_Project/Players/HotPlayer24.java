package org.Final_Project.Players;

public class HotPlayer24 extends Player24 {
	private int streak;
	private double multiplier;
	
	public HotPlayer24() {
		super();
		streak = 0;
		multiplier = 1.0;
	}
	
	public int getStreak() {
		return streak;
	}
	public void setStreak(int streak) {
		this.streak = streak;
	}
	public double getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
	
}
