package edu.sjsu.cs.cs151.mancala.controller;

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
		try {
			m = controller.updateModel(m);
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
