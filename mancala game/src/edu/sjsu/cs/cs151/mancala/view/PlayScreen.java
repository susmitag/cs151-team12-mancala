package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.model.Board;

/*
 * This class represents the main window for the Mancala game.
 */
public class PlayScreen 
{
	private PlayScreen instance;
	private LinkedBlockingQueue<Message> queue; 
	private JFrame frame;				// main window
	private JPanel center;				// center panel with holes
	private JPanel p2North;				// top of board (with player2 label)
	private JPanel p1South;				// bottom of board (with player1 label)
	private JPanel wStore;				// left store, belongs to player2
	private JPanel eStore;				// right store, belongs to player1
	private JPanel options;				// buttons to quit and for rules dialog
	private JLayeredPane main= new JLayeredPane(); // consists of board plus options
	private JPanel board = new JPanel(new BorderLayout()); // entire playing board
	private VisualHole[] holes;
	
	/**
	 * Private constructor, nobody should create multiple instances. 
	 * This constructor creates the main view window
	 * @param queue queue to add event Messages to
	 */
	private PlayScreen(LinkedBlockingQueue<Message> queue) 
	{
		this.queue = queue;
		frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		holes = new VisualHole[Board.AMOUNT_OF_HOLES];
		center = new JPanel();
		p2North = new JPanel();
		p1South = new JPanel();
		wStore = new JPanel();
		eStore = new JPanel();
		options = new JPanel();
		center.setLayout(new GridLayout(2,6));
		
		// creating holes
		for (int i = 12; i > 6; i--) {
			VisualHole vh = new VisualHole(i, queue);		//This is to ensure the holes have the correct index 	
			holes[i] = vh;									//	corresponding with the index used in the model
			center.add(vh);
		}
		for (int i = 0; i < 6; i++) {
			VisualHole vh = new VisualHole(i, queue);
			holes[i] = vh;
			center.add(vh);
		}

		// creating player banners 
		p2North.setBackground(Color.DARK_GRAY);
		p1South.setBackground(Color.DARK_GRAY);
		JLabel p1 = new JLabel("Player 1");
		JLabel p2 = new JLabel("Player 2");
		p1.setForeground(Color.YELLOW);
		p2.setForeground(Color.white);
		p2North.add(p2);
		p1South.add(p1);

		// creating stores
		wStore.setLayout(new BorderLayout());
		eStore.setLayout(new BorderLayout());		
		VisualStore p1Store = new VisualStore(Board.PLAYER1_STORE_INDEX);
		VisualStore p2Store = new VisualStore(Board.PLAYER2_STORE_INDEX);
		holes[Board.PLAYER1_STORE_INDEX] = p1Store;
		holes[Board.PLAYER2_STORE_INDEX] = p2Store;
		wStore.add(BorderLayout.CENTER, p2Store);
		eStore.add(BorderLayout.CENTER, p1Store);
		
		// set sizes of main board JPanel
		p1South.setPreferredSize(new Dimension(300, 70));
		p2North.setPreferredSize(new Dimension(300, 70));
		center.setPreferredSize(new Dimension(600,400));
		center.setMaximumSize(new Dimension(500, 500));

		// assembling components to create game board 
		board.add(BorderLayout.CENTER, center);
		board.add(BorderLayout.WEST, wStore);
		board.add(BorderLayout.EAST, eStore);
		board.add(BorderLayout.NORTH, p2North);
		board.add(BorderLayout.SOUTH, p1South);
		board.setSize(board.getPreferredSize());
		board.setLocation(0, 0);
		
		// creating rules and quit buttons
		options.setLayout(new BorderLayout());
		JButton instructions = new JButton("?");
		instructions.setPreferredSize(new Dimension(55,55));
		instructions.setBackground(Color.lightGray);
		instructions.addActionListener(event -> 
			new JOptionPane().showMessageDialog(null, new RulesDialog(), "Mancala Rules", JOptionPane.INFORMATION_MESSAGE));
		JButton quit = new JButton("X");
		quit.setPreferredSize(new Dimension(55,55));		
		quit.setBackground(Color.lightGray);
		options.add(BorderLayout.EAST, instructions);
		options.add(BorderLayout.WEST, quit);
		options.setPreferredSize(new Dimension(110,55));
		options.setBounds(main.getWidth(), main.getHeight(), 110, 55);
		
		// the gameboard is on back layer, options are in front
		main.add(board, JLayeredPane.DEFAULT_LAYER);
		main.setPreferredSize(board.getPreferredSize());
		main.add(options, JLayeredPane.PALETTE_LAYER);
		
		frame.add(main);
		frame.setSize(1600, 800);
		frame.setMinimumSize(new Dimension(800, 550));
		frame.setMaximumSize(new Dimension(1100, 800));
		frame.pack();
	}

	public void frameSetVisible() {
		frame.setVisible(true);
	}
	
	/**
	 * Given a LinkedBlockingQueue, this method constructs a new view. This should only be called once.
	 * @param queue queue to add event messages to
	 * @return reference to this PlayScreen
	 */
	public static PlayScreen init(LinkedBlockingQueue<Message> queue) {
		PlayScreen ps = new PlayScreen(queue);
		return ps;
	}
		
	/**
	 * Closes main frame
	 */
	public void close() {
		frame.dispose();
	}
	 
	/**
	 * Given an event message, updates view accordingly
	 * @param m message with game information
	 */
	public void update(Message m) {
		updateState(m.getInfo());
	}
	
	/**
	 * Displays the winner of the game in a popup dialog
	 * @param winnerIndex the store index of the player who won
	 */
	public void displayWinner(int winnerIndex) {
		String winner;
		if (winnerIndex == Board.PLAYER1_STORE_INDEX)
			winner = "Player1";
		else 
			winner = "Player2";
		GameoverDialog go = new GameoverDialog(winner);
	}
	
	/**
	 * Updates view based on the information given
	 * @param g GameInfo object with current game state
	 */
	private void updateState(GameInfo g) {
		if (!g.getGameEnded()) {
			for (int i = 0; i < Board.AMOUNT_OF_HOLES; i++) {
				holes[i].setMarbleCount(g.getMarbleCounts()[i]);
				holes[i].setHoleActive(g.getActiveStates()[i]);
			}
			updatePlayerLabelForeground(g);
		} else {
			for (int i = 0; i < Board.AMOUNT_OF_HOLES; i++) {
				holes[i].setMarbleCount(g.getMarbleCounts()[i]);
				holes[i].setHoleActive(g.getActiveStates()[i]);
			}
			JLabel popup = new JLabel("Game Ended");
			main.add(popup, JLayeredPane.POPUP_LAYER);
		}
	}

	/**
	 * Returns the JLabel associated with each player
	 * @param player an integer representing the player (0 or 1)
	 * @return JLabel associated with that player
	 */
	private JLabel getPlayerLabel(int player) {
		if(player == 0)
			return (JLabel)p1South.getComponent(0);
		else
			return (JLabel)p2North.getComponent(0);
	}

	/**
	 * Changes color of player JLabel depending on whose turn it is
	 * @param g GameInfo with turn information
	 */
	public void updatePlayerLabelForeground(GameInfo g) {
		if(g.getTurnChanged()){
			JLabel label = getPlayerLabel(g.getPlayerWithTurn());
			label.setForeground(Color.YELLOW);
			int otherPlayer = (g.getPlayerWithTurn() + 1) % 2;
			label = getPlayerLabel(otherPlayer);
			label.setForeground(Color.WHITE);
		}
	}

}