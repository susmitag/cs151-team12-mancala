package edu.sjsu.cs.cs151.mancala.model;

/**
 * A store holds Marbles captured by a Player. Marbles can not be removed from a Store. 
 *
 */
public class Store extends Hole {
	
    private static final int INITIAL_STORE_MARBLE_COUNT = 0; //Sets initial marble count to 0 for stores
	private int marbleCount;
	
	/**
	 * Constructs a default empty Store.
	 */
	public Store() {
		marbleCount = INITIAL_STORE_MARBLE_COUNT;		// stores begin with zero marbles
	}

	/**
	 * Adds multiple marbles to store
	 * @param amount amount to add
	 */
	public void addMarbles(int amount) {
		marbleCount += amount;
	}
	
}
