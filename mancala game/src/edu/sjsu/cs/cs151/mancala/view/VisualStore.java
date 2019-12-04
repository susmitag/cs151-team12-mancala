package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;

import edu.sjsu.cs.cs151.mancala.model.Board;

import java.awt.*;

/**
 * This class is a visual representation of a Store. It has all of the
 * 	functionality of a VisualHole, but does not send event messageg on click.
 */
public class VisualStore extends VisualHole
{
	private JPanel marbles = new JPanel();
	
	/**
	 * Constructs a VisualStore
	 * @param index position on the board
	 */
	public VisualStore(int index)
	{
		this.index = index;
		isHoleActive = false;
		jp = new JPanel(new BorderLayout());
		jp.setSize(100, 100);
		jp.setBounds(0, 0, 100, 400);
		jp.setBackground(Color.GRAY);
		
		// JButton with oval on it to represent store
		JButton jb = new JButton(new Icon()
			{
				public void paintIcon(Component c, Graphics g, int x, int y) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setStroke(new BasicStroke(5));
					g2.setColor(Color.black);
					g2.drawOval(10, 135, 80, 160);
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
		
		label = new JLabel(""+Board.INITIAL_STORE_MARBLE_COUNT);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(43, 320, 20, 20);
		
		String player;
		if (index == Board.PLAYER1_STORE_INDEX)
			player = "Player1";
		else
			player = "Player2";
		JLabel label1 = new JLabel(player);
		label1.setHorizontalTextPosition(JLabel.CENTER);
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setBounds(15, 100, 80, 20);
		
		jp.add(BorderLayout.CENTER, jb);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.GRAY);
		this.add(jp, JLayeredPane.DEFAULT_LAYER);
		this.add(label, JLayeredPane.MODAL_LAYER);
		this.add(label1, JLayeredPane.MODAL_LAYER);
		this.setVisible(true);
		mg = new MarbleGroup(Board.INITIAL_STORE_MARBLE_COUNT, index, true);
		this.add(mg, JLayeredPane.PALETTE_LAYER);
	}
}
