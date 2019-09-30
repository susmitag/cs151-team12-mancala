package mancala;

/**
 * A Hole stores Marbles and keeps count of how many are in it.
 * Extends Marble class
 */
public class Hole{

    int marbleCount;
    
    // Contructs a default Hole with the marble count set to 4
	public Hole() {
		marbleCount = INITIAL_HOLE_MARBLE_COUNT;
	}

	/**
	 * Adds a marble to hole, incrementing the hole's marbleCount
	 * @param marbleCount marble to add to hole
	 */
	public void placeMarble(Marble marbleCount) {
		
	}

	/**
	 * if the user lands on his own side's hole, capture opposing side's parallel hole's marbles
	 * Cannot be used in Store class
	 */
	public final void captureMarbles() {

	}

	
	public static void moveMarbles() {

    }
}
