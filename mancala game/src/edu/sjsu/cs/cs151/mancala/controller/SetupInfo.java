package edu.sjsu.cs.cs151.mancala.controller;

public class SetupInfo 
{
	private int gameType;
	private int port;
	private String host;
	
	public SetupInfo(int gameType) {
		this.gameType = gameType;
	}
	
	public SetupInfo(int gameType, String host, int port) {
		this.gameType = gameType;
		this.host = host; 
		this.port = port;
	}
	
	public int getGameType() {
		return gameType;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getHost() {
		return host;
	}
}
