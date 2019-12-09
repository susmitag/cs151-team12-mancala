package edu.sjsu.cs.cs151.mancala.model;

import edu.sjsu.cs.cs151.mancala.*;

public class Player {

	public static final int PLAYER1_START_INDEX = 0;
	public static final int PLAYER1_END_INDEX = 5;
	public static final int PLAYER2_START_INDEX = 7;
	public static final int PLAYER2_END_INDEX = 12;
    private Store store;
    
    /**
     * Constructs a Player instance
     * @param s the Player's store
     */
    public Player(Store s) {
    	store = s;
    }
    
    public Player() {
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
        if (game.getBoard().getPlayer1Store().equals(store))
        	player1 = true;
        if (player1)
        	return store.getMarblecount() > game.getBoard().getPlayer2Store().getMarblecount();
		return store.getMarblecount() > game.getBoard().getPlayer1Store().getMarblecount();
    }

    
}