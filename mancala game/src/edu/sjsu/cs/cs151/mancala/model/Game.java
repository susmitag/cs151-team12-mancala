package edu.sjsu.cs.cs151.mancala.model;

/**
 * A Game object represents a single mancala game between two players.
 * Currently incomplete due to GUI and network problems needed to solve first
 */
public class Game {
	public boolean gameOver = false;

	private Board board = new Board();

	/**
	 * Places all marbles in\
	 *  selected hole into the next holes and stores according to mancala's rules.
	 * 
	 * @param hole the hole to take the marbles from
	 */
	public boolean sow(Hole playerOneHole) {
		if(playerOneHole.getMarblecount() > 0){
			int numMarblesToSow = playerOneHole.getMarblecount();
			playerOneHole.removeMarble();
			int startSowHoleIndex = playerOneHole.getIndex();
			for(int count = 0; count < numMarblesToSow; ++count){
				int idx = (startSowHoleIndex + count) % board.getNumberOfHoles();
				Hole dest = board.getHoleAt(idx);
				dest.addMarble();
				//TODO
			}
			return true;
		}
		return false;
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
