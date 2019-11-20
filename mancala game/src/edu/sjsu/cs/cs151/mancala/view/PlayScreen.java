package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class PlayScreen 
{
	private JFrame frame;
	
	public PlayScreen() 
	{
		frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayScreenInternal playScreenInternal = new PlayScreenInternal();
		frame.add(playScreenInternal.getMainComponent());
		frame.setSize(1600, 800);
		frame.setMinimumSize(new Dimension(800, 550));
		frame.setMaximumSize(new Dimension(1100, 800));
		frame.setVisible(true);
		frame.pack();
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

		private PlayScreenInternal() 
		{

			center = new JPanel();
			p2North = new JPanel();
			p1South = new JPanel();
			wStore = new JPanel();
			eStore = new JPanel();
			options = new JPanel();

			center.setLayout(new GridLayout(2,6));
			for (int i = 0; i < 12; i++)
				center.add(new VisualHole());

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
			wStore.add(BorderLayout.CENTER, new VisualStore());
			eStore.add(BorderLayout.CENTER, new VisualStore());
			
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
	}

	private class VisualHole extends JLayeredPane
	{
		private VisualHole()
		{
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
			
			jp.add(BorderLayout.CENTER, jb);
			
			this.setPreferredSize(new Dimension(100, 100));
			this.setBackground(Color.GRAY);
			this.add(jp, JLayeredPane.DEFAULT_LAYER);
			this.setVisible(true);
			MarbleGroup m = new MarbleGroup(4);
			this.add(m, JLayeredPane.PALETTE_LAYER);
		}
	}
	
		private class VisualStore extends JLayeredPane
		{
			JPanel marbles = new JPanel();
			private VisualStore()
			{
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
			}
	}
	
	private class MarbleGroup extends JPanel
	{
		int n;
		
		private MarbleGroup(int n) {
			this.n = n;
			this.setPreferredSize(new Dimension(100,100));
			this.setVisible(true);
			this.setBackground(Color.GRAY);
			this.setBounds(25, 95, 100, 100);
			this.setOpaque(false);
		}
		
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(Color.BLUE);
			int x = 0, y = 0;
			Ellipse2D.Double marble;
			for (int i = 0; i < n; i++,x+=10,y+=10) {
				marble = new Ellipse2D.Double(x, y, 20, 20);
				g2.setPaint(Color.BLUE);
				g2.fill(marble);
				g2.setPaint(Color.BLACK);
				g2.draw(marble);
			}
		}
	}
}