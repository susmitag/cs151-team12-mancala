package edu.sjsu.cs.cs151.mancala.controller;

import javax.swing.JOptionPane;

import edu.sjsu.cs.cs151.mancala.MancalaException;

/**
 * This class is an abstraction for events that occur during the game
 */
public class UpdateGameStateValve implements Valve {
	
	private Controller controller;
	
	/**
	 * Creates a new UpdateGameStateValve
	 * @param controller controller to send updates to
	 */
	public UpdateGameStateValve (Controller controller) {
		this.controller = controller;
	}

	/**
	 * Tells the controller to update Game states based an event
	 * @param m the message with game information
	 * @return EXECUTED if message executed with no error, FINISHED if game is over, 
	 * 	MISS if there was an error
	 */
	public ValveResponse execute(Message m) {
		boolean isOver;
		boolean server = false;
		if (m.getInfo().getGameEnded() && m.getInfo().isEarly()) 
		{
			boolean quit = m.getInfo().didQuit();
			if (m.isClient()) {
				if (!quit) {
					GameInfo g = new GameInfo(true);
					g.setQuit(true);
					controller.sendEventAsClient(new Message(g));
				}
				else
					JOptionPane.showMessageDialog(null, "The other player quit the game. That means you win!");
			}
			else if (m.isServer()) {
				if (!quit) {
					GameInfo g = new GameInfo(true);
					g.setQuit(true);
					controller.sendEventAsServer(new Message(g));
				}
				else
					JOptionPane.showMessageDialog(null, "The other player quit the game. That means you win!");
			}
			controller.disconnect();
			return ValveResponse.EXIT;
		}
		try {
			if (m.isClient()) {
				if (m.getInfo().getChosenHole() == GameInfo.UNASSIGNED) // chosenHole is unassigned when the message comes from the server
					controller.updateView(m);
				else  // if message not from server, it must be from view
					controller.sendEventAsClient(m);
			}
			else {
				if (m.isServer())
					server = true;
				m = controller.updateModel(m);
				m.setServer(server);
				if (m.isServer()) {
					controller.sendEventAsServer(m);
				}
			}
			if (!m.isClient())
				controller.updateView(m);
			isOver = m.getInfo().getGameEnded();
		}
		catch (MancalaException e) {
			e.printStackTrace();
			//error dialog?
			return ValveResponse.MISS;
		}
		if (isOver)
			return ValveResponse.FINISHED;
		return ValveResponse.EXECUTED;
	}
}
