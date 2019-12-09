package edu.sjsu.cs.cs151.mancala.network;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

/**
 * This class represents the serverside socket program for playing mancala over a network
 * @author user
 *
 */
public class Server implements Runnable 
{	
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 6666;
	public static final int BUFFER_SIZE = 20;
	private LinkedBlockingQueue<Message> queue;
	private String host = DEFAULT_HOST;
	private int port = DEFAULT_PORT;
	private ServerSocket socket;
	private Socket connection;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean done = false;
	
	/**
	 * constructs a server object
	 * @param q message queue to add new events to
	 */
	public Server(LinkedBlockingQueue<Message> q) {
		queue = q;
	}
	
	/**
	 * Listens for new events and adds them to the message queue
	 */
	public void run() {
		try {
			socket = new ServerSocket(port, 50, InetAddress.getByName(host));
			JFrame f = new JFrame();
			f.add(new JLabel("Waiting for client to connect..."));
			f.setVisible(true);
			f.setMinimumSize(new Dimension(500, 100));
			connection = socket.accept();
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			f.dispose();
			JOptionPane.showMessageDialog(null, "Connected to client!");
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
			}
		}
	}
	
	/**
	 * Sends game state to client
	 * @param m message with game state
	 */
	public void updateClient(Message m) {
		try {
			while (out == null ) {} // only null in beginning of game. This validates moves
			out.writeObject(m.getInfo()); // taken by the server before the client connects 
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * closes sockets and data streams
	 */
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
	
	/**
	 * Sets host variable
	 * @param host hostname of socket (should be localhost)
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * Sets port variable
	 * @param port used in socket
	 */
	public void setPort(int port) {
		this.port = port;
	}
}
