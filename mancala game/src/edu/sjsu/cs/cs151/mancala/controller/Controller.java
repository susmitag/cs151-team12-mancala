package edu.sjsu.cs.cs151.mancala.controller;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.*;
import edu.sjsu.cs.cs151.mancala.network.*;
import java.util.concurrent.*;

/**
 * Controller for game operations
 */
public class Controller 
{
	public static final int UNASSIGNED = -1;
	private LinkedBlockingQueue<Message> queue;
	private PlayScreen view;
	private Game model;
	private int gameType = UNASSIGNED;
	private Client client;
	private Server server;
	private ExecutorService service;
	
	/**
	 * Constructs a new controller
	 * @param queue queue to add messages to
	 * @param view game screen to update
	 * @param model model of inner game mechanics
	 */
	public Controller(LinkedBlockingQueue<Message> queue, PlayScreen view, Game model) {
		this.queue = queue;
		this.view = view;
		this.model = model;
	}
	
	/**
	 * Adds a view event to the queue
	 * @param m a message containing game information
	 */
	public void addViewEvent(Message m) {
		try {
			queue.put(m);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Updates view from the given message
	 * @param m message with game information
	 */
	public void updateView(Message m) {
		view.update(m);
	}
	
	/**
	 * Updates model based on message
	 * @param m message with game information
	 * @return new Message with updated game information
	 * @throws MancalaException
	 */
	public Message updateModel(Message m) throws MancalaException {
		model.sow(m.getInfo().getChosenHole());
		Message m2 = model.getGameState();
		return m2;
	}
	
	/**
	 * Get a reference to the client used to communicate with the server
	 * @return client reference to client object
	 */
	public Client getClient() {
		return client;
	}
	
	/**
	 * Sets game type and network information. This should only be 
	 * 	called once because it also gives the view a new Client and
	 * 	creates the client/server class if needed
	 * @param info information about the kind of game that will be run
	 */
	public void setup(SetupInfo info) {
		gameType = info.getGameType();
		service = Executors.newCachedThreadPool();
		if (gameType == SetupDialog.CONNECT_TO_GAME) {		// if we are a client, give view Client
			model = null; // remove references so it will be garbage collected
			client =  new Client(view, queue, info.getHost(), info.getPort());    // object to communicate with
			view.setClient(client);		
			service.execute(client);
		}
		else if (gameType == SetupDialog.NEW_NETWORK_GAME) {
			server = new Server(queue);
			view.setServer();
			server.setHost(info.getHost());
			server.setPort(info.getPort());
			service.execute(server);
		}
	}
	
	/**
	 * Sends the message to Client object so it can communicate with the server
	 * @param m message with game state
	 */
	public void sendEventAsClient(Message m) {
		client.addEvent(m);
	}
	
	/**
	 * Sends the message to Server object so it can communicate with the client
	 * @param m message with game state
	 */
	public void sendEventAsServer(Message m) {
		server.updateClient(m);
	}
	
	/**
	 * Returns a reference to the server
	 * @return server to communicate with the client with
	 */
	public Server getServer() {
		return server;
	}
	
	/**
	 * Returns game type. Synchronized to ensure it returns correct value
	 * @return current gameType
	 */
	public synchronized int getGameType() {
		return gameType;
	}
	
	/**
	 * Sets gameType. Synchronized to ensure it sets correct value
	 * @param type game type to set
	 */
	private synchronized void setGameType(int type) {
		gameType = type;
	}
	
	/**
	 * Disconnects server and client sockets
	 */
	public void disconnect() {
		if (client instanceof Client)
			client.close();
		if (server instanceof Server)
			server.close();
	}
}
