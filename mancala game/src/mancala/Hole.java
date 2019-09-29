package mancala;

/**
 * A Hole stores Marbles and keeps count of how many are in it.
 */
public class Hole {

    static final int INITIAL_MARBLE_COUNT = 4;
    int marbleCount;
    
    /**
     * Contructs a default Hole with the marble count set to 4
     */
	public Hole() {
		marbleCount = INITIAL_MARBLE_COUNT;
	}

	/**
	 * Adds a marble to hole, incrementing the hole's marbleCount
	 * 
	 * @param marble marble to add to hole
	 */
	public void placeMarble(Marble marble) {
		
	}
}
