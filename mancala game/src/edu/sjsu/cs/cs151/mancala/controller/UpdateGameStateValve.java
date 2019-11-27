package edu.sjsu.cs.cs151.mancala.controller;

import edu.sjsu.cs.cs151.mancala.MancalaException;

public class UpdateGameStateValve implements Valve {
	
	private Controller controller;
	
	public UpdateGameStateValve (Controller controller) {
		this.controller = controller;
	}

	public ValveResponse execute(Message m) {
		try {
			m = controller.updateModel(m);
			controller.updateView(m);
		}
		catch (MancalaException e) {
			e.printStackTrace();
			//error dialog?
			return ValveResponse.MISS;
		}
		return ValveResponse.EXECUTED;
	}
}
