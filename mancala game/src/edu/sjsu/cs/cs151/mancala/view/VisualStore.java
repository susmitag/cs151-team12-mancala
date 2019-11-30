package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;

import edu.sjsu.cs.cs151.mancala.model.Board;

import java.awt.*;

public class VisualStore extends VisualHole
{
	JPanel marbles = new JPanel();
	MarbleGroup mg;
	
	public VisualStore(int index)
	{
		this.index = index;
		isHoleActive = false;
		JPanel jp = new JPanel(new BorderLayout());
		jp.setSize(100, 100);
		jp.setBounds(0, 0, 100, 400);
		jp.setBackground(Color.GRAY);
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
		
		jp.add(BorderLayout.CENTER, jb);
		
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.GRAY);
		this.add(jp, JLayeredPane.DEFAULT_LAYER);
		this.setVisible(true);
		mg = new MarbleGroup(Board.INITIAL_STORE_MARBLE_COUNT, index, true);
		if (isHoleActive) this.add(mg, JLayeredPane.PALETTE_LAYER);
	}
}
