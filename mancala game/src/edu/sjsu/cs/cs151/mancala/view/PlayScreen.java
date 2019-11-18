package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class PlayScreenInternal {

	JPanel center;
    JPanel p2North;
    JPanel p1South;

	private JLayeredPane mainLayeredPane = new JLayeredPane();

	public PlayScreenInternal() {

		center = new JPanel();
        p2North = new JPanel();
        p1South = new JPanel();

		mainLayeredPane.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3,6));

		for (int i = 0; i < 18; i++) {
            if (i < 6)
                center.add(new VisualHole(-1,-1));
            else
                center.add(new VisualHole(0,0));
		}

        p2North.setBackground(Color.gray);
        p1South.setBackground(Color.gray);
        center.setBackground(Color.red);
        p1South.setPreferredSize(new Dimension(300, 70));
        p2North.setPreferredSize(new Dimension(300, 70));
        center.setPreferredSize(new Dimension(500,500));
        center.setMaximumSize(new Dimension(500, 500));

		mainLayeredPane.add(BorderLayout.CENTER, center);
		mainLayeredPane.add(BorderLayout.WEST, new VisualStore(0, 200));
		mainLayeredPane.add(BorderLayout.EAST, new VisualStore(mainLayeredPane.getWidth(), 200));
        mainLayeredPane.add(BorderLayout.NORTH, p2North);
        mainLayeredPane.add(BorderLayout.SOUTH, p1South);
	}

	JLayeredPane getMainComponent() {
		return mainLayeredPane;
	}
	
	private class VisualHole extends JComponent {
		
		int x;
		int y;
		int r;
		
		private VisualHole(int x, int y) {
            if (x == -1 && y == -1) {
                this.x = 0;
                this.y = 0;
                r = 0;
            }
            else {
                this.x = x;
                this.y = y;
                r = 80;
            }
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