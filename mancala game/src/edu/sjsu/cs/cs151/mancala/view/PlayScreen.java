package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class PlayScreenInternal {

	JPanel center;
    JPanel p2North;
    JPanel p1South;
    JPanel wStore;
    JPanel eStore;

	private JLayeredPane mainLayeredPane = new JLayeredPane();
	private JPanelHole panelHole = new JPanelHole(1,1);

	public PlayScreenInternal() {

		center = new JPanel();
        p2North = new JPanel();
        p1South = new JPanel();
        wStore = new JPanel();
        eStore = new JPanel();
         

		mainLayeredPane.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(2,6));

		for (int i = 0; i < 12; i++)
			center.add(new VisualHole());

        p2North.setBackground(Color.gray);
        p1South.setBackground(Color.gray);
        center.setBackground(Color.red);
        
        wStore.setLayout(new BorderLayout());
        eStore.setLayout(new BorderLayout());
        wStore.add(BorderLayout.CENTER, new VisualStore());
        eStore.add(BorderLayout.CENTER, new VisualStore());
        
        p1South.setPreferredSize(new Dimension(300, 70));
        p2North.setPreferredSize(new Dimension(300, 70));
        center.setPreferredSize(new Dimension(600,400));
        center.setMaximumSize(new Dimension(500, 500));

		mainLayeredPane.add(BorderLayout.CENTER, center);
		mainLayeredPane.add(BorderLayout.WEST, wStore);
		mainLayeredPane.add(BorderLayout.EAST, eStore);
        mainLayeredPane.add(BorderLayout.NORTH, p2North);
        mainLayeredPane.add(BorderLayout.SOUTH, p1South);
	}

	JLayeredPane getMainComponent() {
		return mainLayeredPane;
	}

	class JPanelHole extends JPanel 
	{
		private int row;
		private int col;
		private JComponent seed = null;

		public JPanelHole(int row, int col){
			this.row = row;
			this.col = col;
			setLayout(new GridBagLayout());
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

		@Override
		public Component add(Component c) {
			seed = (JComponent) c;
			return super.add(c);
		}

		@Override
		public void remove(Component comp) {
			seed = null;
			super.remove(comp);
		}

		public JComponent getSeed() {
			return seed;
		}
	}

	private class VisualHole extends JButton 
	{
		
		private VisualHole() {
			super (new Icon()
					{
						public void paintIcon(Component c, Graphics g, int x, int y) {
							g.drawOval(10, 80, 80, 80);
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
		}
	}

	private class VisualStore extends JButton 
	{
		
		private VisualStore() {
			super (new Icon()
					{
						public void paintIcon(Component c, Graphics g, int x, int y) {
							g.drawOval(10, 100, 80, 160);
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
		frame.setVisible(true);
		frame.pack();
	}
}
