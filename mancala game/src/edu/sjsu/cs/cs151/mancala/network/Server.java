package edu.sjsu.cs.cs151.mancala.network;

import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class Server implements Runnable {
	
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 666;
	private LinkedBlockingQueue<Message> queue;
	private LinkedBlockingQueue<Message> internalQueue;
	private String host = DEFAULT_HOST;
	private int port = DEFAULT_PORT;
	
	public Server(LinkedBlockingQueue<Message> q) {
		queue = q;
		internalQueue = new LinkedBlockingQueue<Message>();
	}
	
	public void run() {
		while (true) {
			// check for events to send to client
			// listen for events from client, add them to queue
		}
	}
	
	public void updateClient(Message m) {
		// add message to internal server queue
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
}
