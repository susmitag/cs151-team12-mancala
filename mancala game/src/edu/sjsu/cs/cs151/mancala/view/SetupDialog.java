package edu.sjsu.cs.cs151.mancala.view;

import java.awt.*;
import javax.swing.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.network.Server;

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
			if (response == 0) {				
				gameType = NEW_NETWORK_GAME;  	// showOptionDialog always returns the same numbers
			}
			else if (response == 1 ) {
				gameType = CONNECT_TO_GAME;
			}
		}

		JTextField hostField = new JTextField(16);
		JTextField portField = new JTextField(6);
		JLabel hostLabel = new JLabel("hostname:");
		JLabel portLabel = new JLabel("port:");
		JPanel panel = new JPanel();	
		portField.setText(""+Server.DEFAULT_PORT);
		hostField.setText(Server.DEFAULT_HOST);
		String msg1 = "Enter hostname and port number.";
		String msg2 = "If you are the server, enter localhost";
		String msg3 = "If you are the client, get the hostname/port from the server.";
		String msg4 = "Feel free to change the default port.";
		panel.setLayout(new GridLayout(10,1));
		JLabel msg1Label = new JLabel(msg1);
		JLabel msg2Label = new JLabel(msg2);
		JLabel msg3Label = new JLabel(msg3);
		JLabel msg4Label = new JLabel(msg4);
		panel.add(msg1Label);
		panel.add(msg2Label);
		panel.add(msg3Label);
		panel.add(msg4Label);
		panel.add(Box.createVerticalStrut(5));
		panel.add(hostLabel);
		panel.add(hostField);
		panel.add(Box.createVerticalStrut(5));
		panel.add(portLabel);
		panel.add(portField);
		
		int port;
		String host;
		int r = JOptionPane.showConfirmDialog(null, panel, "Setup", JOptionPane.PLAIN_MESSAGE);
		try {
			host = hostField.getText().trim();
			port = Integer.parseInt(portField.getText().trim());
		}
		catch (Exception e) {
			host = "localhost";		// random defaults that will likely fail
			port = 666;
		}
		
		if (gameType == NEW_NETWORK_GAME) {
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(4,1));
			JLabel l1 = new JLabel("If you are playing over a LAN, enter ifconfig (ipconfig on windows) into");
			JLabel l2 = new JLabel("a terminal to find your local IP address. It likely looks like \"10.0.0.212\"");
			JLabel l3 = new JLabel("or \"192.0.0.12\". Give this address and port number \""+port+"\" to the client.");
			JLabel l4 = new JLabel("If you are playing over the internet, substitute the appropriate hostname.");
			panel2.add(l1);
			panel2.add(l2);
			panel2.add(l3);
			panel2.add(l4);
			showMessageDialog(null, panel2);
		}
		return new SetupInfo(gameType, host, port);
	}
}
