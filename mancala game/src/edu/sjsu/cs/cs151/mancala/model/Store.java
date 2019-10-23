package edu.sjsu.cs.cs151.mancala.model;

/**
 * A store holds Marbles captured by a Player. Marbles can not be removed from a Store. 
 *
 */
public class Store extends Hole {
	
	
	/**
	 * Constructs a default empty Store.
	 */
	public Store() {
		marbleCount = INITIAL_STORE_MARBLE_COUNT;		// stores begin with zero marbles
	}

	
}
