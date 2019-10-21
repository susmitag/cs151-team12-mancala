package mancala.model;

/**
 * A Game object represents a single mancala game between two players.
 * Currently incomplete due to GUI and network problems needed to solve first
 */
public class Game {
	public boolean gameOver = false; 

	/**
	 * Places all marbles in\
	 *  selected hole into the next holes and stores according to mancala's rules.
	 * 
	 * @param hole the hole to take the marbles from
	 */
	public void sow(Hole playerOneHole) {
		
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
