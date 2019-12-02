package edu.sjsu.cs.cs151.mancala.controller;

public class GameInfo {
	private int chosenHole;
	private int[] marbleCounts;
	public boolean[] activeStates;
	private boolean turnChanged;
	private int playerWithTurn;
	private boolean gameEnded;
	
	public GameInfo(int chosenHole) {
		this.chosenHole = chosenHole;
		marbleCounts = null;
	}
	
	public GameInfo(int[] marbleCounts, boolean[] activeStates, boolean turnChanged, int playerWithTurn, boolean gameEnded) {
		this.marbleCounts = marbleCounts;
		this.activeStates = activeStates;
		this.turnChanged = turnChanged;
		this.playerWithTurn = playerWithTurn;
		this.gameEnded = gameEnded;
		chosenHole = -1;
	}
	
	public int getChosenHole() {
		return chosenHole;
	}
	
	public int[] getMarbleCounts() {
		return marbleCounts;
	}

	public boolean[] getActiveStates() { return activeStates; }

	public boolean getTurnChanged() { return turnChanged; }

	public int getPlayerWithTurn() { return playerWithTurn; }

	public boolean getGameEnded() { return gameEnded; }
}