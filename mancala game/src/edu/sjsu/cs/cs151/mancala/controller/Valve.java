package edu.sjsu.cs.cs151.mancala.controller;

public interface Valve {
	
	/**
	 * Respond to a message
	 * @param m message to respond to
	 * @return response to message
	 */
	public ValveResponse execute(Message m);
}
