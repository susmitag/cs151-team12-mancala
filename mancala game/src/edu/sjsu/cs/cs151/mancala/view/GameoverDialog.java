package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.JOptionPane;

public class GameoverDialog extends JOptionPane {
	
	public GameoverDialog(String winner) {
		super();
		showMessageDialog(null, winner + " wins!", "Gameover", JOptionPane.INFORMATION_MESSAGE);
	}
}
