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
	
	/**
	 * @return true if this message belongs to a client instance of the game
	 */
	public boolean isClient() {
		return isClient;
	}
	
	/**
	 * @return true if this message belongs to a server instance of the game
	 */
	public boolean isServer() {
		return isServer;
	}
	
	/**
	 * @return GameInfo that this message carries
	 */
	public GameInfo getInfo() {
		return info;
	}
	
	/**
	 * Sets the isServer field
	 * @param t true if this is a server
	 */
	public void setServer(boolean t) {
		isServer = t;
	}

}
