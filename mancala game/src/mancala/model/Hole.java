package mancala.model;

/**
 * A Hole stores Marbles and keeps count of how many are in it.
 * Extends Marble class
 */
public class Hole{
    int index;
	int marbleCount;
	public static final int INITIAL_HOLE_INDEX = -1;
	public static final int INITIAL_MARBLE_COUNT = 4; //Sets initial marble count to 4 for holes
   // public static final int INITIAL_STORE_MARBLE_COUNT = 0; //Sets initial marble count to 0 for stores
    
    // Constructs a default Hole with the marble count set to 4
	public Hole() {
		marbleCount = INITIAL_MARBLE_COUNT;
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
	 * @param the index of the hole in the board
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
	 * @param marbleCount marble to add to hole
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
	
	public int removeMarble() {
		int count = this.marbleCount;
		this.marbleCount = 0;
		return count;
    }
	
	
}
