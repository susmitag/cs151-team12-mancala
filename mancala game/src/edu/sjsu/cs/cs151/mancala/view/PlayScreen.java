package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class PlayScreenInternal {

	JPanel main;
	JPanel center;
	JPanel holePanel[];

	public PlayScreenInternal() {

		main = new JPanel();
		center = new JPanel();

		main.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(2,6));
		int x = 0;
		int y = 0;
		for (int i = 0; i < 12; i++,x++,y++) {
			center.add(new VisualHole(x,y));
		}

		main.add(BorderLayout.CENTER, center);
		main.add(BorderLayout.WEST, new VisualStore(0, 0));
		main.add(BorderLayout.EAST, new VisualStore(main.getWidth(), 0));
	}

	JPanel getMainComponent() {
		return main;
	}
	
	private class VisualHole extends JComponent {
		
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

	private class VisualStore extends JComponent {
		
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

public class PlayScreen {
	JFrame frame;
	public PlayScreen() {
		frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayScreenInternal playScreenInternal = new PlayScreenInternal();
		frame.add(playScreenInternal.getMainComponent());
		frame.setSize(1200, 800);
		frame.setVisible(true);
		frame.pack();
	}
}