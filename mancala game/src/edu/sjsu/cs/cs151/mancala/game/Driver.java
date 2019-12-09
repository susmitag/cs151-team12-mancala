package edu.sjsu.cs.cs151.mancala.game;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.view.introAnimation.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.network.*;


import java.util.concurrent.*;

public class Driver {

	public static void main(String[] args) 
	{
        LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
        PlayScreen view = PlayScreen.init(queue);
		Game model = Game.getGame();
		Controller controller = new Controller(queue, view, model);
		IntroAnimation intro = new IntroAnimation(view, controller);
		
		int gameType = controller.getGameType();		// until player chooses, the gameType is UNASSIGNED
		while (gameType == Controller.UNASSIGNED) 
			gameType = controller.getGameType();

		if (gameType == SetupDialog.NEW_NETWORK_GAME) // new network setup
			view.disablePlayer2Holes(); // disable other players holes to prevent cheating
		
		else if (gameType == SetupDialog.CONNECT_TO_GAME) { // connect to network setup
			model = null; 			// server keeps track of model. this one can be garbage collected
			view.disablePlayer1Holes();
		}

		view.addActionListeners(); // adds action listeners to all holes that arent disabled
		UpdateGameStateValve gameValve = new UpdateGameStateValve(controller);
		ValveResponse response = ValveResponse.EXECUTED;
		while (response != ValveResponse.FINISHED && response != ValveResponse.EXIT) {
			try {
				Message m = queue.take();
				response = gameValve.execute(m);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (response != ValveResponse.EXIT) // seperate dialog if a player quits early
			view.displayWinner();
		view.close();
		queue.clear();
		System.exit(0);
	}		
}