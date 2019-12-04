package edu.sjsu.cs.cs151.mancala.controller;

/**
 * Messages carry information about the game
 */
public class Message {
	
	private GameInfo info;
	
	/**
	 * Creates a new Message
	 * @param info information about the current state of the game
	 */
	public Message(GameInfo info) {
		this.info = info;
	}
	
	public GameInfo getInfo() {
		return info;
	}

}
