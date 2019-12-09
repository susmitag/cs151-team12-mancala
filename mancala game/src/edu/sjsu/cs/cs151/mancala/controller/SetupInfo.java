package edu.sjsu.cs.cs151.mancala.controller;

/**
 * This class represents the information needed to setup a network game
 * @author user
 *
 */
public class SetupInfo 
{
	private int gameType;
	private int port;
	private String host;
	
	/**
	 * Constructs a new SetupInfo object with gametype only
	 * @param gameType type of game
	 */
	public SetupInfo(int gameType) {
		this.gameType = gameType;
	}
	
	/**
	 * Constructs a new setupInfo object with all fields as parameters
	 * @param gameType type of game
	 * @param host hostname to connect to (localhost if server)
	 * @param port port to connect to
	 */
	public SetupInfo(int gameType, String host, int port) {
		this.gameType = gameType;
		this.host = host; 
		this.port = port;
	}
	
	/**
	 * @return gameType
	 */
	public int getGameType() {
		return gameType;
	}
	
	/**
	 * @return port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * @return hostname
	 */
	public String getHost() {
		return host;
	}
}
