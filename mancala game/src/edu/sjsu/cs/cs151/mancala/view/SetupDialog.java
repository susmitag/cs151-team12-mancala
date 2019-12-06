package edu.sjsu.cs.cs151.mancala.view;

import java.awt.Color;

import javax.swing.*;

public class SetupDialog extends JOptionPane
{
	public static final int NEW_LOCAL_GAME = 0;
	public static final int NETWORK_GAME = 1;
	public static final int NEW_NETWORK_GAME = 2;
	public static final int CONNECT_TO_GAME = 3;
	public static final int UNEXPLAINABLE_ERROR = -1;
	
	public static int question() {
		Object[] locationOptions = {"local game", "network game"};
		Object[] networkOptions = {"create new game", "connect to existing game"};
		int response = showOptionDialog(null, "Are you playing local game or a network game?", "Setup", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, locationOptions, null);
		if (response == NEW_LOCAL_GAME)
			return response;
		else if (response == NETWORK_GAME) {
			response = showOptionDialog(null, "Create a new game, or connect to existing game?", "Setup",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, networkOptions, null);
			if (response == 0) 				
				return NEW_NETWORK_GAME;  	// showOptionDialog always returns the same numbers
			else if (response == 1 )
				return CONNECT_TO_GAME;
		}
		return UNEXPLAINABLE_ERROR; //this should never be returned
	}
}
