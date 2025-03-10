package edu.sjsu.cs.cs151.mancala.model;

/**
 * A Hole stores Marbles and keeps count of how many are in it.
 * Extends Marble class
 */
public class Hole {
    private int index;
	private int marbleCount;
	static final int INITIAL_HOLE_INDEX = -1;
	public static final int INITIAL_HOLE_MARBLE_COUNT = 4;
	protected boolean isActive = false;
    
    /** Constructs a default Hole with specified marble count
     * @param i initial marble count
     */
	public Hole(int i) {
		marbleCount = i;
		index = INITIAL_HOLE_INDEX;
	}
	
	/**
	 * Constructs a default Hole
	 */
	public Hole() {
		marbleCount = Board.INITIAL_HOLE_MARBLE_COUNT;
		index = INITIAL_HOLE_INDEX;
	}
	
	/**
	 * Get index of the hole in the board
	 * @return the index of the hole in the board
	 */	
	public int getIndex()
	{
		return index;
	}
	
	/**
	 * Set index of the hole in the board
	 * @param index of the hole in the board
	 */		
	public void setIndex(int index)
	{
		this.index = index;
	}

	/**
	 * Get count of marbles in the hole
	 * @return the count of marbles in the hole
	 */	
	public int getMarblecount()
	{
		return marbleCount;
	}

	/**
	 * Adds a marble to hole, incrementing the hole's marbleCount
	 * @postcondition getMarblecount() > 0
	 */
	public void addMarble() {
		this.marbleCount += 1;	
	}
	
	/**
	 * Removes  marbles from hole, decrementing the hole's marbleCount to 0
	 * @return the count of marbles in the hole before the call
	 * @precondition getMarblecount() > 0
	 */
	public int removeMarbles() {
		int count = this.marbleCount;
		this.marbleCount = 0;
		return count;
    }
	
	/**
	 * Checks if two Holes are the same based on their index
	 * @param h Hole to compare with
	 * @return true if the Holes are the same
	 */
	public boolean equals(Hole h) {
		if (this.index == h.getIndex())
			return true;
		return false;
	}

	public void setIsActive (boolean state) { 
		isActive = state; 
	}

	public boolean getIsActive () { 
		return isActive; 
		}
}
