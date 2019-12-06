package edu.sjsu.cs.cs151.mancala.network;

import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import java.util.concurrent.*;

public class Client 
{
	private PlayScreen view;
	private LinkedBlockingQueue<Message> queue;
	
	public Client(PlayScreen view, LinkedBlockingQueue<Message> queue) {
		this.view = view;
		this.queue = queue;
	}
	
	public void addEvent(Message m) {
		
	}
}
