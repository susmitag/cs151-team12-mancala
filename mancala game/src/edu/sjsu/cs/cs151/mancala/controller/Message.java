package edu.sjsu.cs.cs151.mancala.controller;

/**
 * Messages carry information about the game
 */
public class Message {
	
	private GameInfo info;
	private boolean isClient = false; // local games, servers, and clients must behave 
	private boolean isServer = false; // differently in the UpdateGameStateValve class
	
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
	public Message (GameInfo info, boolean isClient, boolean isServer) {
		this.info = info;
		this.isClient = isClient;
		this.isServer = isServer;
	}
	
	public boolean isClient() {
		return isClient;
	}
	
	public boolean isServer() {
		return isServer;
	}
	
	public GameInfo getInfo() {
		return info;
	}

}
