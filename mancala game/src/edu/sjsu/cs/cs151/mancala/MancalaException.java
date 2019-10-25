package edu.sjsu.cs.cs151.mancala;

/**
 * Generic exception for the Mancala game
 */
public class MancalaException extends Exception {
	
	/**
	 * Default constructor with error message
	 * @param msg error message
	 */
	public MancalaException(String msg) {
		super(msg);
	}
}
