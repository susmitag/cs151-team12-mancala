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
		jp = new JPanel(new BorderLayout());
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
		//if (isHoleActive) this.add(mg, JLayeredPane.PALETTE_LAYER);
		this.add(mg, JLayeredPane.PALETTE_LAYER);
		this.add(new JPanel(true) {
			Font font = new Font("Serif", Font.PLAIN, 20);
			String s = "4";

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;

				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setFont(font);

				FontMetrics fm = g.getFontMetrics(font);

				int width = fm.stringWidth(s);

				Dimension d = getSize();

				//center String/text
				int cx = (d.width - width) / 2;
				int cy = (d.height - fm.getHeight()) / 2;

				g2.drawString(s, cx, cy);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(300, 100);
			}
		}, JLayeredPane.MODAL_LAYER);
	}
}
