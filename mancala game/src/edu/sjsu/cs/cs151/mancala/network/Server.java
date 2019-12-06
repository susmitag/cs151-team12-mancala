package edu.sjsu.cs.cs151.mancala.network;

import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class Server implements Runnable {
	
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 666;
	private LinkedBlockingQueue<Message> queue;
	private String host = DEFAULT_HOST;
	private int port = DEFAULT_PORT;
	
	public Server(LinkedBlockingQueue<Message> q) {
		queue = q;
	}
	
	public void run() {

	}
	
	public void updateClient(Message m) {
		
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
}
