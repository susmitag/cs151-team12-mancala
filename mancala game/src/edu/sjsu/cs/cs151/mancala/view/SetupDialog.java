package edu.sjsu.cs.cs151.mancala.view;

import java.awt.Color;
import javax.swing.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class SetupDialog extends JOptionPane
{
	public static final int NEW_LOCAL_GAME = 0;
	public static final int NETWORK_GAME = 1;
	public static final int NEW_NETWORK_GAME = 2;
	public static final int CONNECT_TO_GAME = 3;
	
	public static SetupInfo question() {
		int gameType = -1;
		Object[] locationOptions = {"local game", "network game"};
		Object[] networkOptions = {"create new game", "connect to existing game"};
		int response = showOptionDialog(null, "Are you playing local game or a network game?", "Setup", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, locationOptions, null);
		if (response == NEW_LOCAL_GAME)
			return new SetupInfo(response);
		else if (response == NETWORK_GAME) {
			response = showOptionDialog(null, "Create a new game, or connect to existing game?", "Setup",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, networkOptions, null);
			if (response == 0) 				
				gameType = NEW_NETWORK_GAME;  	// showOptionDialog always returns the same numbers
			else if (response == 1 )
				gameType = CONNECT_TO_GAME;
		}
		
		JTextField hostField = new JTextField(16);
		JTextField portField = new JTextField(6);
		JLabel hostLabel = new JLabel("hostname:");
		JLabel portLabel = new JLabel("port:");
		JPanel panel = new JPanel();
		panel.add(hostLabel);
		panel.add(hostField);
		panel.add(Box.createHorizontalStrut(16));
		panel.add(portLabel);
		panel.add(portField);
		
		int port;
		String host;
		int r = JOptionPane.showConfirmDialog(null, panel, 
				"Enter hostname and port number.", JOptionPane.PLAIN_MESSAGE);
		try {
			host = hostField.getText().trim();
			port = Integer.parseInt(portField.getText().trim());
		}
		catch (Exception e) {
			host = "localhost";		// random defaults that will likely fail
			port = 666;
		}
		return new SetupInfo(gameType, host, port);
	}
}
