package edu.sjsu.cs.cs151.mancala.model;
import edu.sjsu.cs.cs151.mancala.*;

public class Player {

    public static boolean gameOver = false; 
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

    public void selectHoleToSow(Hole start) {
    	Game game = Game.getGame();
    	try {
			game.sow(start, store);
    	}
    	catch (MancalaException x) {
    		System.out.println(x);
    	}
    }
    
    /**
     * Keeps track of the amount of marbles in the player's hands (the amount they pick up)
     */
    public int marblesInHand(){
    	return INITIAL_HOLE_MARBLE_COUNT;
    }

    /**
     * Determines whether or not the player is a winner
     * @return returns boolean value that's true if the player is a winner
     */
    public boolean winner(){
        if(gameOver != true)
        {
            return false;
        }
        else{
            return true;
        }
    }

    
}