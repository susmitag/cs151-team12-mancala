package edu.sjsu.cs.cs151.mancala.model;
import edu.sjsu.cs.cs151.mancala.*;

public class Player {

    public static int INITIAL_HOLE_MARBLE_COUNT = 4;
    private Store store;
    
    /**
     * Constructs a Player instance
     * @param s the Player's store
     */
    public Player(Store s) {
    	store = s;
    }
    
    /**
     * Checks if a Store belongs to this Player
     * @param s store to check ownership of
     * @return true if this Player own the store
     */
    public boolean ownsStore(Store s) {
    	return s.equals(store);
    }

    /**
     * Selects Hole to sow marbles from and then sows them
     * @param start Hole to sow marbles from
     * @return free turn or not
     */
    public boolean selectHoleToSow(Hole start) throws MancalaException{
    	Game game = Game.getGame();
    	return game.sow(start, store);
    }
    
    /**
     * Keeps track of the amount of marbles in the player's hands (the amount they pick up)
     */
    public int marblesInHand(){
    	return INITIAL_HOLE_MARBLE_COUNT;
    }

    /**
     * Checks if two Players are the same based on their store
     * @param p Player to compare with
     * @return true if the Players are the same
     */
    public boolean equals(Player p) {
        if (p.ownsStore(this.store))
            return true;
        return false;
    }

    /**
     * Determines whether or not the player is a winner
     * @return returns boolean value that's true if the player is a winner
     */
    public boolean winner(){
    	Game game = Game.getGame();
    	if (!game.gameStatus())
    		return false;
        boolean player1 = false;
        if (game.getBoard().getHoleAt(Board.PLAYER1_STORE_INDEX).equals(store))
        	player1 = true;
        if (player1)
        	return store.getMarblecount() > game.getBoard().getHoleAt(Board.PLAYER2_STORE_INDEX).getMarblecount();
		return store.getMarblecount() > game.getBoard().getHoleAt(Board.PLAYER1_STORE_INDEX).getMarblecount();
    }

    
}