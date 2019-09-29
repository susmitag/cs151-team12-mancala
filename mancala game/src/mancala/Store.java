package mancala;

/**
 * A store holds Marbles captured by a Player. Marbles can not be removed from a Store. 
 *
 */
public class Store extends Hole {
	
	/**
	 * Constructs a default empty Store.
	 */
	public Store() {
		marbleCount = 0;		// stores begin with zero marbles
	}

}
