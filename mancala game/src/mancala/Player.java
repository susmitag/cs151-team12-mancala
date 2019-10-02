package mancala;

public class Player {

    public static boolean gameOver = false; 
    public static int INITIAL_HOLE_MARBLE_COUNT = 4;

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
        return gameOver;
    }

    
}