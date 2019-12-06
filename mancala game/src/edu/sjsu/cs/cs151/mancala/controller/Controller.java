package edu.sjsu.cs.cs151.mancala.controller;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.*;
import edu.sjsu.cs.cs151.mancala.network.*;
import java.util.concurrent.*;

/**
 * Controller for game operations
 */
public class Controller 
{
	private LinkedBlockingQueue<Message> queue;
	private PlayScreen view;
	private Game model;
	private int gameType = SetupDialog.NEW_LOCAL_GAME;
	private Client client;
	
	/**
	 * Constructs a new controller
	 * @param queue queue to add messages to
	 * @param view game screen to update
	 * @param model model of inner game mechanics
	 */
	public Controller(LinkedBlockingQueue<Message> queue, PlayScreen view, Game model) {
		this.queue = queue;
		this.view = view;
		this.model = model;
	}
	
	/**
	 * Adds a view event to the queue
	 * @param m a message containing game information
	 */
	public void addViewEvent(Message m) {
		try {
			queue.put(m);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Updates view from the given message
	 * @param m message with game information
	 */
	public void updateView(Message m) {
		view.update(m);
	}
	
	/**
	 * Updates model based on message
	 * @param m message with game information
	 * @return new Message with updated game information
	 * @throws MancalaException
	 */
	public Message updateModel(Message m) throws MancalaException {
		model.sow(m.getInfo().getChosenHole());
		Message m2 = model.getGameState();
		return m2;
	}
	
	/**
	 * Get a reference to the client used to communicate with the server
	 * @return client reference to client object
	 */
	public Client getClient() {
		return client;
	}
	
	/**
	 * Sets game type and network information. This should only be 
	 * 	called once because it also gives the view a new Client
	 * @param type
	 */
	public void setup(SetupInfo info) {
		gameType = info.getGameType();
		if (gameType == SetupDialog.CONNECT_TO_GAME) {		// if we are a client, give view Client
			client =  new Client(view, queue, info.getHost(), info.getPort());    // object to communicate with
			view.setClient(client);		
		}
		else if (gameType == SetupDialog.NEW_NETWORK_GAME) {
			
		}
	}
	
	/**
	 * @return gameType
	 */
	public int getGameType() {
		return gameType;
	}
}
