package edu.sjsu.cs.cs151.mancala.network;

import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class Server implements Runnable 
{	
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 6666;
	public static final int BUFFER_SIZE = 20;
	private LinkedBlockingQueue<Message> queue;
	private LinkedBlockingQueue<Message> internalQueue;
	private String host = DEFAULT_HOST;
	private int port = DEFAULT_PORT;
	private ServerSocket socket;
	private Socket connection;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean done = false;
	
	public Server(LinkedBlockingQueue<Message> q) {
		queue = q;
		internalQueue = new LinkedBlockingQueue<Message>();
	}
	
	public void run() {
		try 
		{
			socket = new ServerSocket(port, 50, InetAddress.getByName(host));
			connection = socket.accept();
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());
			Message m;
			while (!done) {
				if (!internalQueue.isEmpty()) {
					m = internalQueue.take();
					System.out.println(m.getInfo().getChosenHole());
					out.writeObject(m.getInfo());
					out.flush();
					System.out.println("Sent");
				}
				if (in.available() > 0) {
					GameInfo g = (GameInfo) in.readObject();
					queue.add(new Message(g, false, true));
				}
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
		internalQueue.add(m);
	}
	
	public void close() {
		try {
			in.close();
			out.close();
			connection.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		done = true;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
}
