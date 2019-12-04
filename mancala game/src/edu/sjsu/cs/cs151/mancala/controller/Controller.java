package edu.sjsu.cs.cs151.mancala.controller;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.*;
import java.util.concurrent.*;

/**
 * Controller for game operations
 */
public class Controller 
{
	private LinkedBlockingQueue<Message> queue;
	private PlayScreen view;
	private Game model;
	
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
}
