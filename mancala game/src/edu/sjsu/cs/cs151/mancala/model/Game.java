package edu.sjsu.cs.cs151.mancala.model;
import edu.sjsu.cs.cs151.mancala.*;

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
	 * Places all marbles in\
	 *  selected hole into the next holes and stores according to mancala's rules.
	 * 
	 * @param hole the hole to take the marbles from
	 * @return true if the last marble sowed ends in player's store (for free turn)
	 */
	public boolean sow(Hole hole, Store playerStore) throws MancalaException{
		if (Game.getGame().getBoard().isStore(hole)) {
			throw new MancalaException("Error: can not sow marbles in from a store.");
		}
		int marbleCount = hole.removeMarbles();
		Board board = Game.getGame().getBoard();
		
		while (marbleCount > 0) {
			hole = board.getNextHole(hole);
			// add marbles to the hole only if it is the calling player's store, or if it is a hole
			if ((board.isStore(hole) && hole.equals(playerStore)) || !board.isStore(hole)) {
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
	 * Resets player's turn if they land on their own store
	 */
	private boolean landInStore(){
		return false;
	}

	/**
	 * Checks for the current game's running status
	 * @return returns a boolean value that's true if the game is over, false if the game is still going
	 */
	public boolean gameStatus(){
		return gameOver;
	}

	/**
     * Captures all marbles on player's side if the enemy does not have any more marbles
     */
    public void captureMarbles(){
		
	}
}
