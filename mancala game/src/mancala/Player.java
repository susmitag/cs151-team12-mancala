package mancala;

public class Player {

    public boolean gameOver = false; 

    /**
     * Keeps track of the amount of marbles in the player's hands (the amount they pick up)
     */
    public void marblesInHand(){
        
    }

    /**
     * Determines whether or not the player is a winner
     * @return returns boolean value that's true if the player is a winner
     */
    public boolean winner(){
        return gameOver;
    }

    
}