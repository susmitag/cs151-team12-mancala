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
		try {
			socket = new ServerSocket(port, 50, InetAddress.getByName(host));
			connection = socket.accept();
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		while (!done) {
			try {
				GameInfo g = (GameInfo) in.readObject();
				queue.add(new Message(g, false, true));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateClient(Message m) {
		try {
			out.writeObject(m.getInfo());
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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
