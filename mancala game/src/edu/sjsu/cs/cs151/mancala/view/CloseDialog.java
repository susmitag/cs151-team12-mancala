package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

/**
 * Dialog to make sure players actually want to quit.
 */
public class CloseDialog extends JOptionPane 
{
	
	private PlayScreen view;
	
	/**
	 * Creates a default Close dialog
	 * @param view to close on confirm
	 */
	public CloseDialog(PlayScreen view) {
		super();
		this.view = view;
	}
	
	public void question() {
		int response = this.showConfirmDialog(null, "Are you sure you want to exit?", "Leaving so soon?", JOptionPane.WARNING_MESSAGE);
		if (response == JOptionPane.YES_OPTION)
			view.getQueue().add(new Message(new GameInfo(true)));
	}
}
