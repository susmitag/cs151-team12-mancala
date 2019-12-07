package edu.sjsu.cs.cs151.mancala.network;

import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class Client 
{
	private PlayScreen view;
	private LinkedBlockingQueue<Message> queue;
	private String host;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client(PlayScreen view, LinkedBlockingQueue<Message> queue, String host, int port) {
		this.queue = queue;
		this.view = view;
		this.host = host;
		this.port = port;
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
	
	public Message addEvent(Message m) {
		GameInfo g;
		// send to server, get response, return response
		try {
			out.writeObject(m.getInfo());
			out.flush();
			g = (GameInfo) in.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			g = null;
		}
		return new Message(g, true, false);
	}
}
