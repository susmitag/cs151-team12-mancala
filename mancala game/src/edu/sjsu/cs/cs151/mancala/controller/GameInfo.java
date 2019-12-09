package edu.sjsu.cs.cs151.mancala.controller;

import java.io.*;

/**
 * This class contains information about the current state of the game/
 * @author user
 *
 */
public class GameInfo implements Serializable {
	public static final int UNASSIGNED = 15; // outside of hole index range
	private int chosenHole = UNASSIGNED;
	private int[] marbleCounts;
	public boolean[] activeStates;
	private boolean turnChanged;
	private int playerWithTurn;
	private boolean gameEnded = false;
	private boolean exitEarly = false;
	private boolean quit = false;
	
	/**
	 * Constructed by view, contains information about which hole has been chosen.
	 * @param chosenHole chosen hole for sowing
	 */
	public GameInfo(int chosenHole) {
		this.chosenHole = chosenHole;
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
	}
	
	/**
	 * @return chosenHole variable if set, UNASSIGNED otherwise
	 */
	public int getChosenHole() {
		return chosenHole;
	}
	
	/**
	 * @return marbleCounts variable if set, null otherwise
	 */
	public int[] getMarbleCounts() {
		return marbleCounts;
	}

	/**
	 * @return activeStates variable if set, null otherwise
	 */
	public boolean[] getActiveStates() { 
		return activeStates; 
	}

	/**
	 * @return true if turn has changed
	 */
	public boolean getTurnChanged() { 
		return turnChanged; 
	}

	/**
	 * @return 1 if player1 has the turn, 2 for player2
	 */
	public int getPlayerWithTurn() { 
		return playerWithTurn; 
	}

	/**
	 * @return true if the game is over
	 */
	public boolean getGameEnded() { 
		return gameEnded; 
	}
	
	/**
	 * @return true if a player quit the game early
	 */
	public boolean isEarly() { 
		return exitEarly; 
	}
	
	/**
	 * @return return true if the other player quit the game
	 */
	public boolean didQuit() {
		return quit;
	}
	
	/**
	 * Sets quit variable
	 * @param p true if this player quit the game
	 */
	public void setQuit(boolean p) {
		quit = p;
	}
}