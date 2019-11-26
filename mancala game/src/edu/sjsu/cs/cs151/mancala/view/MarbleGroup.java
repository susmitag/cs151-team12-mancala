package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MarbleGroup extends JPanel
{
	int n;
	
	public MarbleGroup(int n) {
		System.out.println(n);
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
