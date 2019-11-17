package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class PlayScreen 
{
	
	JFrame frame;
	JPanel main;
	JPanel center;
	JPanel p2North;
	JPanel p1South;
	
	public PlayScreen() 
	{
		frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		main = new JPanel();
		center = new JPanel();
		p2North = new JPanel();
		p1South = new JPanel();
		
		main.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(2,6));
		for (int i = 0; i < 12; i++)
			center.add(new VisualHole(0,0));			

		p2North.setBackground(Color.gray);
		p1South.setBackground(Color.gray);
		center.setBackground(Color.gray);

		main.add(BorderLayout.CENTER, center);
		main.add(BorderLayout.WEST, new VisualStore(0, 200));
		main.add(BorderLayout.EAST, new VisualStore(main.getWidth(), 200));
		main.add(BorderLayout.NORTH, p2North);
		main.add(BorderLayout.SOUTH, p1South);
		center.setPreferredSize(new Dimension(300,300));
		center.setMaximumSize(new Dimension(300, 300));
		frame.add(main);
		frame.setSize(1200, 800);
		frame.setVisible(true);
		frame.pack();
	}
	
	private class VisualHole extends JComponent 
	{
		int x;
		int y;
		int r;
		
		private VisualHole(int x, int y) {
			this.x = x;
			this.y = y;
			r = 80;
		}

		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setPaint(Color.blue);
			g2.setStroke(new BasicStroke(2.5f));
			g2.draw(new Ellipse2D.Double(x,y, r, r));
		}
	}

	private class VisualStore extends JComponent 
	{
		int x;
		int y;
		int h;
		int w;
		
		private VisualStore(int x, int y) {
			this.x = x;
			this.y = y;
			h = 140;
			w = 70;
		}

		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setPaint(Color.blue);
			g2.setStroke(new BasicStroke(2.5f));
			g2.draw(new Ellipse2D.Double(x,y, w, h));
		}

		public Dimension getPreferredSize() {
		    return new Dimension(w, h);
        }
	}
}