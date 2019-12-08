package edu.sjsu.cs.cs151.mancala.network;

import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class Client implements Runnable
{
	private PlayScreen view;
	private LinkedBlockingQueue<Message> queue;
	private LinkedBlockingQueue<Message> internalQueue;
	private String host;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean done = false;
	
	public Client(PlayScreen view, LinkedBlockingQueue<Message> queue, String host, int port) 
	{
		this.queue = queue;
		this.view = view;
		this.host = host;
		this.port = port;
		internalQueue = new LinkedBlockingQueue<Message>();
		try {
			socket = new Socket(host, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
			// complain
		}
	}
	
	public void run() {
		GameInfo g;
		
		// send to server, get response, return response
		try {
			while (!done) {
				System.out.println(done);
				if (!internalQueue.isEmpty()) {
					System.out.println("sending update");
					Message m = internalQueue.take();
					if (m instanceof Message) {
						out.writeObject(m.getInfo());
						out.flush();
					}
				}
				if (in.available() > 0) {
					System.out.println("recieving update");
					g = (GameInfo) in.readObject();
					System.out.println("it works!");
					queue.add(new Message(g, true, false));
				}
				g = null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		done = true;
	}
	
	public void addEvent(Message m) {
		internalQueue.add(m);
	}
}
