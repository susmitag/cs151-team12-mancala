package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.model.Board;
import edu.sjsu.cs.cs151.mancala.network.Client;

/**
 * This class is a visual representation of a Hole.
 */
public class VisualHole extends JLayeredPane
{
		protected int index;
		protected boolean isHoleActive;
		protected MarbleGroup mg;
		private LinkedBlockingQueue<Message> queue;
		protected JPanel jp;
		protected JLabel label;
	    private Client networkInstance = null;
		private boolean isServer;

		/*
		 * If no arguments, calls JLayeredPane constructor.
		 */
		public VisualHole() {
			super();
		}
		
		/**
		 * Constructs a VisualHole
		 * @param index position on board
		 * @param queue BlockingQueue to add even messages to
		 */
		public VisualHole(int index, LinkedBlockingQueue<Message> queue)
		{
			this.queue = queue;
			this.index = index;
			isHoleActive = false;
			jp = new JPanel(new BorderLayout());
			jp.setSize(100, 100);
			jp.setBounds(0, 0, 100, 200);
			jp.setBackground(Color.GRAY);
			
			// draw circle on button to represent hole
			JButton jb = new JButton(new Icon()
				{
					public void paintIcon(Component c, Graphics g, int x, int y) {
						Graphics2D g2 = (Graphics2D)g;
						g2.setStroke(new BasicStroke(5));
						g2.setColor(Color.black);
						g2.drawOval(10, 80, 80, 80);
					}

					public int getIconHeight() {
						return 80;
					}

					public int getIconWidth() {
						 return 80;
					}
				});
			jb.setPreferredSize(new Dimension(100, 100));
			jb.setBorder(BorderFactory.createEmptyBorder());
			jb.setFocusPainted(false);
			jb.setBackground(Color.GRAY);
			jb.setFocusable(false);
			
			// on click, send game state to controller
			jb.addActionListener(event ->
					{
						if(networkInstance != null){
							queue.add(new Message(new GameInfo(index), isServer));
						} else {
							queue.add(new Message(new GameInfo(index)));
						}
					});

			label = new JLabel(""+Board.INITIAL_HOLE_MARBLE_COUNT);
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.setHorizontalAlignment(JLabel.CENTER);
			
			jp.add(BorderLayout.CENTER, jb);
			jp.add(BorderLayout.SOUTH, label);
			this.setPreferredSize(new Dimension(100, 100));
			this.setBackground(Color.GRAY);
			this.add(jp, JLayeredPane.DEFAULT_LAYER);
			this.setVisible(true);
			mg = new MarbleGroup(4, index, false);
			this.add(mg, JLayeredPane.PALETTE_LAYER);
		}

		public void setNetworkInstance (Client networkInstance) {
			this.networkInstance = networkInstance;
		}

		public void setIsServer(boolean isServer) {
			this.isServer = isServer;
		}

		public boolean getIsServer () {
			return isServer;
		}
		
		/**
		 * Sets this Hole's marble count and paints that many marbles.
		 * @param i number of marbles to paint
		 */
		public void setMarbleCount(int i) {
			if (label != null)
				label.setText(""+i);		// we also set the label to show the correct marble count
			boolean isStore;
			if (mg == null) {
				isStore = true;
			}
			else {
				isStore = mg.isStore();
				//if (isHoleActive) this.remove(mg);
				this.remove(mg);
			}
			mg = new MarbleGroup(i, index, isStore);
			this.add(mg, JLayeredPane.PALETTE_LAYER);
		}

		/**
		 * Sets Hole's active state
		 * @param state state to set
		 */
		public void setHoleActive (boolean state) {
		    isHoleActive = state;
        }
}
