package edu.sjsu.cs.cs151.mancala.controller;

public enum ValveResponse {
	MISS,		// unable to process Message
	EXECUTED,	// successfully processed Message
	FINISHED,	// Game has ended
	EXIT		// exiting the game early
	;
}

