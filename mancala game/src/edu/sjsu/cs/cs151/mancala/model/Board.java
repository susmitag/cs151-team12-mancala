package edu.sjsu.cs.cs151.mancala.model;
  
/*
 * A board is constructed for each game. It consists of two rows of six Holes, and two Stores.
 *
 * BOARD LAYOUT:
 *
 *      12   11   10   09   08   07        <<Player2
 * 13                                  06
 *      00   01   02   03   04   05        <<Player1
 * 06 is Player1's store, 13 is Player2's store
 *
 */
public class Board {
    public static final int AMOUNT_OF_HOLES = 14;
    public static final int AMOUNT_OF_HOLES_PER_PLAYER = AMOUNT_OF_HOLES/2 - 1;
    public static final int PLAYER1_STORE_INDEX = 6;
    public static final int PLAYER2_STORE_INDEX = 13;
    public static final int INITIAL_HOLE_MARBLE_COUNT = 4;
    public static final int INITIAL_STORE_MARBLE_COUNT = 0;
    
    private Hole[] board = new Hole[14];

    /**
     * Constructs a new Board object
     */
    public Board(){
        for (int i = 0; i < AMOUNT_OF_HOLES; i++) {
            if (i == PLAYER1_STORE_INDEX || i == PLAYER2_STORE_INDEX)
                board[i] = new Store();
            else
                board[i] = new Hole(INITIAL_HOLE_MARBLE_COUNT);
            board[i].setIndex(i);
        }
    }

    /**
     * Given the index of a Hole, determines whether that Hole belongs to 
     * 	player1 or player2 and returns the corresponding Store
     * @param index index of Hole to find Store of
     * @return Store corresponding to Hole
     */
    public Store getCorrespondingStore(int index)
    {
    	if (index >= 7 && index <= 12)
    		return (Store) board[PLAYER2_STORE_INDEX];
    	else if (index >= 0 && index <= 6)
    		return (Store) board[PLAYER1_STORE_INDEX];
    	else return null;
    }
    
    /**
     * Displays the state of a side of the board by number of marbles per hole or store
     * @param start hole of side
     * @param end hole of side
     * @param player whose side to display
     */
    private void displaySide(int start, int end, String player){
        System.out.println(player + "'s side");
        for (int i = start; i < end; ++i) {
            if (i == PLAYER1_STORE_INDEX || i == PLAYER2_STORE_INDEX)
                System.out.printf("(%d, [%d]) ",i, board[i].getMarblecount());
            else
                System.out.printf("(%d, %d) ",i, board[i].getMarblecount());
        }
        System.out.println();
    }

    /**
     * Displays the state of the board by number of marbles per hole or store
     */
    public void displayBoard(){
        displaySide(0, AMOUNT_OF_HOLES/2, "Player 1");
        displaySide(AMOUNT_OF_HOLES/2, AMOUNT_OF_HOLES, "Player 2");
    }

    /**
     * Removes all marbles from opposite hole and adds them to players store
     * @param h hole to find opposite of
     * @param playerStore store to add captured marbles to
     * @param opposite this hole or opposite hole
     */
    public void captureHole(Hole h, Store playerStore, boolean opposite) {
    	int capturedMarbles = h.removeMarbles();
    	if (opposite) {
			Hole capturedHole = getOpposite(h);
			capturedMarbles+=capturedHole.removeMarbles();
    	}
    	playerStore.addMarbles(capturedMarbles);
    }
    
    /**
     * Finds the Hole opposite of the given Hole
     * @param h hole to find opposite of
     * @return Hole opposite of h, null if passed store
     */
    private Hole getOpposite(Hole h) {
    	switch (h.getIndex()) {
    		case 0:
    			return board[12];
    		case 1:
    			return board[11];
    		case 2:
    			return board[10];
    		case 3:
    			return board[9];
    		case 4:
    			return board[8];
    		case 5:
    			return board[7];
    		case 7:
    			return board[5];
    		case 8:
    			return board[4];
    		case 9:
    			return board[3];
    		case 10:
    			return board[2];
    		case 11:
    			return board[1];
    		case 12:
    			return board[0];
    	}
    	return null;
    }
    
    /**
     * Given an index, returns the Hole at that index
     * @param i index of Hole to return
     * @return Hole at index i
     */
    public Hole getHoleAt(int i) {
        return board[i];
    }

    /**
     * Returns the next Hole on the Board according to the Board layout. Used for sowing marbles.
     * @param h current Hole
     * @return next Hole on Board
     */
    public Hole getNextHole(Hole h) {
        if (h.getIndex() == board.length - 1)       // if the Hole h is at the end of board array,
            return board[0];                        //  wrap around back to the beginning of board
        return getHoleAt(h.getIndex() + 1);
    }

    /**
     * Checks if a Hole is a Store or not.
     * @param h Hole to check
     * @return true if parameter h is a Store, false otherwise
     */
    public boolean checkIfStore(Hole h) {
        int index = h.getIndex();
        return index == PLAYER1_STORE_INDEX || index == PLAYER2_STORE_INDEX;
    }

    /**
     * Returns player1's store
     * @return player1's store
     */
    public Store getPlayer1Store() {
    	return (Store) board[PLAYER1_STORE_INDEX];
    }

    /** 
     * Returns player2's store
     * @return player2's store
     */
    public Store getPlayer2Store() {
    	return (Store) board[PLAYER2_STORE_INDEX];
    }
    
    /**
     * Returns player's initial hole to sow to
     * @param playerIndex index of player
     * @return index of players initial hole
     */
    public int getStartHoleOfPlayer (int playerIndex) {
        if (playerIndex == 1) {
            return 0;
        } else {
            return 7;
        }
    }
}