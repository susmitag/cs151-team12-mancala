package edu.sjsu.cs.cs151.mancala.controller;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.*;
import java.util.concurrent.*;

public class Controller 
{
	private LinkedBlockingQueue<Message> queue;
	private PlayScreen view;
	private Game model;
	
	public Controller(LinkedBlockingQueue<Message> queue, PlayScreen view, Game model)
	{
		this.queue = queue;
		this.view = view;
		this.model = model;
	}
	
	public void addViewEvent(Message m) {
		try {
			queue.put(m);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
			//Error dialog?
		}
	}
	
	public void updateView(Message m) {
		view.update(m);
	}
	
	public Message updateModel(Message m) throws MancalaException {
		model.sow(m.getInfo().getChosenHole());
		Message m2 = model.getMarbleCounts();
		return m2;
	}
}
