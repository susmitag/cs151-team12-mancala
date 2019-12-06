package edu.sjsu.cs.cs151.mancala.controller;

/**
 * Messages carry information about the game
 */
public class Message {
	
	private GameInfo info;
	private boolean isClient = false; // clients must behave differently in the UpdateGameStateValve class
	
	/**
	 * Creates a new Message
	 * @param info information about the current state of the game
	 */
	public Message(GameInfo info) {
		this.info = info;
	}
	
	/**
	 * Creates a new message for clients
	 * @param info information about current state of the game
	 * @param isClient true if this game instance belongs to a client
	 */
	public Message (GameInfo info, boolean isClient) {
		this.info = info;
		this.isClient = isClient;
	}
	
	public boolean isClient() {
		return isClient;
	}
	
	public GameInfo getInfo() {
		return info;
	}

}
