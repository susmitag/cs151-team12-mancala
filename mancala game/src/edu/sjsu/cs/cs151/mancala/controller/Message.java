package edu.sjsu.cs.cs151.mancala.controller;

public class Message {
	
	private GameInfo info;
	
	public Message(GameInfo info) {
		this.info = info;
	}
	
	public GameInfo getInfo() {
		return info;
	}

}
