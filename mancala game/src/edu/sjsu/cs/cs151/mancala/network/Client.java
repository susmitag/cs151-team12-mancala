package edu.sjsu.cs.cs151.mancala.network;

import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * This class is the client side socket for playing mancala over a network
 */
public class Client implements Runnable
{
	private PlayScreen view;
	private LinkedBlockingQueue<Message> queue;
	private String host;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean done = false;
	
	/**
	 * Constructs a client object
	 * @param view playscreen for client player
	 * @param queue message queue to add events to
	 * @param host server hostname
	 * @param port port to connect to server with
	 */
	public Client(PlayScreen view, LinkedBlockingQueue<Message> queue, String host, int port) 
	{
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
			JOptionPane.showMessageDialog(null, "Error: could not connect", "Error", JOptionPane.ERROR_MESSAGE);
			queue.add(new Message(new GameInfo(true)));
			// complain
		}
	}
	
	/**
	 * Listens for new gameinfo messages from server and adds them to the queue
	 */
	public void run() {
		GameInfo g;
		// send to server, get response, return response
		while (!done) {
			try {
				g = (GameInfo) in.readObject();
				queue.add(new Message(g, true, false));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * closes connections and data streams
	 */
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
	
	/**
	 * Sends an event to the server
	 * @param m message with game information to send
	 */
	public void addEvent(Message m) {
		try {
			out.writeObject(m.getInfo());
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
