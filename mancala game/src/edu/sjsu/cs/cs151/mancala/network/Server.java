package edu.sjsu.cs.cs151.mancala.network;

import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class Server {
	
	private LinkedBlockingQueue<Message> queue;
	
	public Server(LinkedBlockingQueue<Message> q) {
		queue = q;
	}
}
