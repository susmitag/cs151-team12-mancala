package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.*;

class PlayScreenInternal {

	JPanel center;
    JPanel p2North;
    JPanel p1South;
    JPanel wStore;
    JPanel eStore;

	private JLayeredPane mainLayeredPane = new JLayeredPane();
	private JPanel board = new JPanel(new BorderLayout());

	public PlayScreenInternal() {

		center = new JPanel();
        p2North = new JPanel();
        p1South = new JPanel();
        wStore = new JPanel();
        eStore = new JPanel();

		center.setLayout(new GridLayout(2,6));
		try {
			Image img = ImageIO.read(getClass().getResource("mancala-stone.jpg"));

			for (int i = 0; i < 12; i++) {
				VisualHole vh = new VisualHole();
				vh.setIcon(new ImageIcon(img));
				center.add(vh);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

        p2North.setBackground(Color.gray);
        p1South.setBackground(Color.gray);
        p2North.add(new JLabel("Player 2"));
        p1South.add(new JLabel("Player 1"));

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
        mainLayeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
        mainLayeredPane.setPreferredSize(board.getPreferredSize());
	}

	JLayeredPane getMainComponent() {
		return mainLayeredPane;
	}

	private class VisualHole extends JButton
	{
		
		private VisualHole() {
			super (new Icon()
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
			this.setPreferredSize(new Dimension(60, 50));
			this.setBorder(BorderFactory.createEmptyBorder());
			this.setFocusPainted(false);
			this.setBackground(Color.GRAY);
		}
	}

	private class VisualStore extends JButton 
	{
		
		private VisualStore() {
			super (new Icon()
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
			this.setPreferredSize(new Dimension(100, 60));
			this.setBorder(BorderFactory.createEmptyBorder());
			this.setFocusPainted(false);
			this.setBackground(Color.GRAY);
		}
	}
}

public class PlayScreen 
{
	JFrame frame;
	public PlayScreen() {
		frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayScreenInternal playScreenInternal = new PlayScreenInternal();
		frame.add(playScreenInternal.getMainComponent());
		frame.setSize(1600, 800);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setMaximumSize(new Dimension(1100, 800));
		frame.setVisible(true);
		frame.pack();
	}
}