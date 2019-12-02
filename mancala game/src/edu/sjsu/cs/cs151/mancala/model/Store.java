package edu.sjsu.cs.cs151.mancala.model;

/**
 * A store holds Marbles captured by a Player. Marbles can not be removed from a Store. 
 *
 */
public class Store extends Hole {
	
	private int marbleCount;
	
	/**
	 * Constructs a default empty Store.
	 */
	public Store() {
		super(Board.INITIAL_STORE_MARBLE_COUNT);
	}

	/**
	 * Adds multiple marbles to store
	 * @param amount amount to add
	 */
	public void addMarbles(int amount) {
		marbleCount += amount;
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
}
