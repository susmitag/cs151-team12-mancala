package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.JOptionPane;

/**
 * This dialog is a popup announcing the winner of the game.
 */
public class GameoverDialog extends JOptionPane {
	
	/**
	 * Creates a new GameoverDialog
	 * @param winner a string with the name of the winner
	 */
	public GameoverDialog(String winner) {
		super();
		showMessageDialog(null, winner + " wins!", "Gameover", JOptionPane.INFORMATION_MESSAGE);
	}
}
