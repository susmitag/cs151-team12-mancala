package edu.sjsu.cs.cs151.mancala.controller;

/**
 * This class contains information about the current state of the game/
 * @author user
 *
 */
public class GameInfo {
	private int chosenHole;
	private int[] marbleCounts;
	public boolean[] activeStates;
	private boolean turnChanged;
	private int playerWithTurn;
	private boolean gameEnded;
	private boolean exitEarly = false;
	public static final int NOT_USED = -1;
	
	/**
	 * Constructed by view, contains information about which hole has been chosen.
	 * @param chosenHole chosen hole for sowing
	 */
	public GameInfo(int chosenHole) {
		this.chosenHole = chosenHole;
		marbleCounts = null;
	}
	
	/**
	 * Constructed by view when user prematurely ends game
	 * @param gameEnded true when the game is over
	 */
	public GameInfo(boolean gameEnded) {
		this.gameEnded = gameEnded;
		exitEarly = true;
	}
	
	/**
	 * Constructed by model, contains information about current game state
	 * @param marbleCounts array of hole marble counts based on index
	 * @param activeStates array of hole states based on index
	 * @param turnChanged true if the turn has changed
	 * @param playerWithTurn player with turn
	 * @param gameEnded true if the game is over
	 */
	public GameInfo(int[] marbleCounts, boolean[] activeStates, boolean turnChanged, int playerWithTurn, boolean gameEnded) {
		this.marbleCounts = marbleCounts;
		this.activeStates = activeStates;
		this.turnChanged = turnChanged;
		this.playerWithTurn = playerWithTurn;
		this.gameEnded = gameEnded;
		chosenHole = NOT_USED;
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
	
	public boolean isEarly() { return exitEarly; }
}