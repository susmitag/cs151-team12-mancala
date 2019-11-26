package edu.sjsu.cs.cs151.mancala.model;
import edu.sjsu.cs.cs151.mancala.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

/**
 * A Game object represents a single mancala game between two players.
 * Currently incomplete due to GUI and network problems needed to solve first
 */
public class Game {
	public boolean gameOver = false;

	private Board board = new Board();
	private static final Game instance = new Game();
	
	/**
	 * Private constructor to prevent creation of multiple games
	 */
	private Game() {
		// instance fields already constructed, nothing to do here
	}
	
	/**
	 * Returns reference to the Game
	 * @return the Game object
	 */
	public static Game getGame() {
		return instance;
	}
	
	/**
	 * Resets board to starting format
	 */
	public void reset() {
		board = new Board();
	}
	
	/**
	 * Gives a Message containing info about the number of marbles in each hole.
	 * 	The index of the array corresponds with the index of the Hole
	 * @return Message with array of marbles counts
	 */
	public Message getMarbleCounts() {
		int[] marbleCounts = new int[Board.AMOUNT_OF_HOLES];
		for (int i = 0; i < Board.AMOUNT_OF_HOLES; i++) {
			marbleCounts[i] = board.getHoleAt(i).getMarblecount();
		}
		return new Message(new GameInfo(marbleCounts));
	}
	/**
	 * Given an index of a Hole, sows that Hole
	 * @param index index of Hole to sow
	 */
	public void sow(int index) throws MancalaException { 
		Hole h = board.getHoleAt(index);
		Store s = board.getCorrespondingStore(index);
		sow(h, s);
	}
	
	/**
	 * Places all marbles in\
	 *  selected hole into the next holes and stores according to mancala's rules.
	 * 
	 * @param hole the hole to take the marbles from
	 * @param playerStore the Store corresponding to the hole being sown
	 * @return true if the last marble sowed ends in player's store (for free turn)
	 */
	public boolean sow(Hole hole, Store playerStore) throws MancalaException {
		if (Game.getGame().getBoard().checkIfStore(hole)) {
			throw new MancalaException("Error: can not sow marbles from a store.");
		}
		int marbleCount = hole.removeMarbles();
		Board board = Game.getGame().getBoard();
		while (marbleCount > 0) {
			hole = board.getNextHole(hole);
			// add marbles to the hole only if it is the calling player's store, or if it is a hole
			if ((board.checkIfStore(hole) && hole.equals(playerStore)) || !board.checkIfStore(hole)) {
					hole.addMarble();
					marbleCount--;
			}
		}
		//checks if last marble placed was in their store (player goes again)
		if (hole.equals(playerStore)) 
			return true;
		else {
			if (hole.getMarblecount() == 1) { 				// if the hole has 1 marble, then it was previously empty and
				board.captureOpposite(hole, playerStore);   //    the player captures all marbles in the opposite hole
			}
			return false;
		}
	}

	/**
	 * Accessor method for the Game's board
	 * @return the current Game Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Checks if all holes on one side is empty
	 * @return returns a boolean value that's true if all holes on one side is empty
	 */
	private boolean checkSideEmpty(int start, int end){
		boolean isSideEmpty = true;
		for(int i=start; i < end; ++i){
			Hole h = board.getHoleAt(i);
			if(board.checkIfStore(h)) continue;
			if(h.getMarblecount() > 0) {
				isSideEmpty = false;
				break;
			}
		}
		return isSideEmpty;
	}

	/**
	 * Checks for the current game's running status
	 * @return returns a boolean value that's true if the game is over, false if the game is still going
	 */
	public boolean gameStatus(){
		int numberOfHoles = board.getNumberOfHoles();
		if ((checkSideEmpty(0, numberOfHoles/2)) || (checkSideEmpty(numberOfHoles/2 + 1, numberOfHoles)))
			gameOver = true;
		else
			gameOver = false;
		return gameOver;
	}
}