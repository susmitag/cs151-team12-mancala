package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

public class VisualHole extends JLayeredPane
{
		private int index;
		private MarbleGroup mg;
		private LinkedBlockingQueue<Message> queue;

		public VisualHole() {
			super();
		}
		
		public VisualHole(int index, LinkedBlockingQueue<Message> queue)
		{
			this.queue = queue;
			this.index = index;
			JPanel jp = new JPanel(new BorderLayout());
			jp.setSize(100, 100);
			jp.setBounds(0, 0, 100, 200);
			jp.setBackground(Color.GRAY);
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
			
			jb.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							System.out.println(index);
							queue.add(new Message(new GameInfo(index)));
						}
					});
			
			jp.add(BorderLayout.CENTER, jb);
			
			this.setPreferredSize(new Dimension(100, 100));
			this.setBackground(Color.GRAY);
			this.add(jp, JLayeredPane.DEFAULT_LAYER);
			this.setVisible(true);
			mg = new MarbleGroup(4);
			this.add(mg, JLayeredPane.PALETTE_LAYER);
		}
		
		public void setMarbleCount(int i) {
			mg = new MarbleGroup(i);
			this.add(mg, JLayeredPane.PALETTE_LAYER);
		}
}
