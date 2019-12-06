package edu.sjsu.cs.cs151.mancala.network;

import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import java.util.concurrent.*;

public class Client 
{
	private PlayScreen view;
	private LinkedBlockingQueue<Message> queue;
	private String host;
	private int port;
	
	public Client(PlayScreen view, LinkedBlockingQueue<Message> queue, String host, int port) {
		this.queue = queue;
		this.view = view;
		this.host = host;
		this.port = port;
	}
	
	public Message addEvent(Message m) {
		// send to server, get response, return response
		return null;
	}
}
