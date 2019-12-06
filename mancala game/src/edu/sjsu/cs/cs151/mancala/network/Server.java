package edu.sjsu.cs.cs151.mancala.network;

import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class Server {
	
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 666;
	private LinkedBlockingQueue<Message> queue;
	
	public Server(LinkedBlockingQueue<Message> q) {
		queue = q;
	}
}
