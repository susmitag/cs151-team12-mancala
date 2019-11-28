package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.model.Board;

public class PlayScreen 
{
	private PlayScreen instance; 
	private LinkedBlockingQueue<Message> queue;
	private JFrame frame;
	private PlayScreenInternal playScreenInternal;
	
	private PlayScreen(LinkedBlockingQueue<Message> queue) 
	{
		this.queue = queue;
		frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playScreenInternal = new PlayScreenInternal();
		frame.add(playScreenInternal.getMainComponent());
		frame.setSize(1600, 800);
		frame.setMinimumSize(new Dimension(800, 550));
		frame.setMaximumSize(new Dimension(1100, 800));
		frame.setVisible(true);
		frame.pack();
	}
	
	public static PlayScreen init(LinkedBlockingQueue<Message> queue) {
		PlayScreen ps = new PlayScreen(queue);
		return ps;
	}
		
	public void close() {
		frame.dispose();
	}
	 
	public void update(Message m) {
		playScreenInternal.updateMarbleCount(m.getInfo());
	}
	
	private class PlayScreenInternal 
	{

		private JPanel center;
		private JPanel p2North;
		private JPanel p1South;
		private JPanel wStore;
		private JPanel eStore;
		private JPanel options;

		private JLayeredPane mainLayeredPane = new JLayeredPane();
		private JPanel board = new JPanel(new BorderLayout());
		private VisualHole[] holes;

		private PlayScreenInternal() 
		{
			holes = new VisualHole[Board.AMOUNT_OF_HOLES];
			center = new JPanel();
			p2North = new JPanel();
			p1South = new JPanel();
			wStore = new JPanel();
			eStore = new JPanel();
			options = new JPanel();

			center.setLayout(new GridLayout(2,6));
			
			//This is to ensure the holes have the correct index 
			//	corresponding with the index used in the model
			for (int i = 12; i > 6; i--) {
				VisualHole vh = new VisualHole(i, queue);
				holes[i] = vh;
				center.add(vh);
			}
			for (int i = 0; i < 6; i++) {
				VisualHole vh = new VisualHole(i, queue);
				holes[i] = vh;
				center.add(vh);
			}

			p2North.setBackground(Color.DARK_GRAY);
			p1South.setBackground(Color.DARK_GRAY);
			JLabel p1 = new JLabel("Player 1");
			JLabel p2 = new JLabel("Player 2");
			p1.setForeground(Color.white);
			p2.setForeground(Color.white);
			p2North.add(p2);
			p1South.add(p1);

			wStore.setLayout(new BorderLayout());
			eStore.setLayout(new BorderLayout());
			
			VisualStore p1Store = new VisualStore(Board.PLAYER1_STORE_INDEX);
			VisualStore p2Store = new VisualStore(Board.PLAYER2_STORE_INDEX);
			holes[Board.PLAYER1_STORE_INDEX] = p1Store;
			holes[Board.PLAYER2_STORE_INDEX] = p2Store;
			wStore.add(BorderLayout.CENTER, p2Store);
			eStore.add(BorderLayout.CENTER, p1Store);
			
			p1South.setPreferredSize(new Dimension(300, 70));
			p2North.setPreferredSize(new Dimension(300, 70));
			center.setPreferredSize(new Dimension(600,400));
			center.setMaximumSize(new Dimension(500, 500));

			board.add(BorderLayout.CENTER, center);
			board.add(BorderLayout.WEST, wStore);
			board.add(BorderLayout.EAST, eStore);
			board.add(BorderLayout.NORTH, p2North);
			board.add(BorderLayout.SOUTH, p1South);
			board.setSize(board.getPreferredSize());
			board.setLocation(0, 0);
			
			options.setLayout(new BorderLayout());
			JButton instructions = new JButton("?");
			instructions.setPreferredSize(new Dimension(55,55));
			instructions.setBackground(Color.lightGray);
			JButton quit = new JButton("X");
			quit.setPreferredSize(new Dimension(55,55));		
			quit.setBackground(Color.lightGray);
			options.add(BorderLayout.EAST, instructions);
			options.add(BorderLayout.WEST, quit);
			options.setPreferredSize(new Dimension(110,55));
			options.setBounds(mainLayeredPane.getWidth(), mainLayeredPane.getHeight(), 110, 55);
			
			mainLayeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
			mainLayeredPane.setPreferredSize(board.getPreferredSize());
			mainLayeredPane.add(options, JLayeredPane.PALETTE_LAYER);
		}

		JLayeredPane getMainComponent() {
			return mainLayeredPane;
		}

		public void updateMarbleCount(GameInfo g) {
			for (int i = 0; i < Board.AMOUNT_OF_HOLES; i++) {
				holes[i].setMarbleCount(g.getMarbleCounts()[i]);
			}
			updatePlayerLabelForeground(g);
		}

		public JLabel getPlayerLabel(int player) {
			if(player == 0)
				return (JLabel)p1South.getComponent(0);
			else
				return (JLabel)p2North.getComponent(0);
		}

		public void updatePlayerLabelForeground(GameInfo g) {
			if(g.getTurnChanged()){
				JLabel label = getPlayerLabel(g.getPlayerWithTurn());
				label.setForeground(Color.YELLOW);
				int otherPlayer = (g.getPlayerWithTurn() + 1) % 2;
				label = getPlayerLabel(otherPlayer);
				label.setForeground(Color.WHITE);
			}
		}

		private class SowMouseAdapter extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
				Point p = e.getPoint();
				Component c = board.getComponentAt(p);
			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		}
	}
}