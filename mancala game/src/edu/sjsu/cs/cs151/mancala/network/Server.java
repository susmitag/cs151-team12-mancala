package edu.sjsu.cs.cs151.mancala.network;

import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class Server implements Runnable {
	
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 6666;
	private LinkedBlockingQueue<Message> queue;
	private LinkedBlockingQueue<Message> internalQueue;
	private String host = DEFAULT_HOST;
	private int port = DEFAULT_PORT;
	private ServerSocket socket;
	private Socket connection;
	private DataInputStream in;
	private DataInputStream out;
	
	public Server(LinkedBlockingQueue<Message> q) {
		queue = q;
		internalQueue = new LinkedBlockingQueue<Message>();
	}
	
	public void run() {
		try {
			socket = new ServerSocket(port, 50, InetAddress.getByName(host));
			connection = socket.accept();
			while (true) {
				// check for events to send to client
				// listen for events from client, add them to queue
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// complain
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
