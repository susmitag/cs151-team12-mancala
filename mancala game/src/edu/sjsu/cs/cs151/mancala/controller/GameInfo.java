package edu.sjsu.cs.cs151.mancala.controller;

public class GameInfo {
	private int chosenHole;
	private int[] marbleCounts;
	
	public GameInfo(int chosenHole) {
		this.chosenHole = chosenHole;
		marbleCounts = null;
	}
	
	public GameInfo(int[] marbleCounts) {
		this.marbleCounts = marbleCounts;
		chosenHole = -1;
	}
	
	public int getChosenHole() {
		return chosenHole;
	}
	
	public int[] getMarbleCounts() {
		return marbleCounts;
	}
}
