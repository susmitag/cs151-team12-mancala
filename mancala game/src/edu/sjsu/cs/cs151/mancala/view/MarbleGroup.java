package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class MarbleGroup extends JPanel
{
	private int count;
	private boolean isStore;
	
	public MarbleGroup(int n, boolean isStore) {
		count = n;
		this.isStore = isStore;
		this.setPreferredSize(new Dimension(100,100));
		this.setVisible(true);
		this.setBackground(Color.GRAY);
		if (isStore)
			this.setBounds(25, 95, 100, 300);
		else
			this.setBounds(25, 95, 100, 100);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		Random r = new Random();
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLUE);
		Ellipse2D.Double marble;
		if (!isStore) 
		{
			int x = 0, y = 0;
			for (int i = 0; i < count; i++,x = r.nextInt(35),y = r.nextInt(35)) {
				marble = new Ellipse2D.Double(x, y, 20, 20);
				g2.setPaint(Color.BLUE);
				g2.fill(marble);
				g2.setPaint(Color.BLACK);
				g2.draw(marble);
			}
		}
		else 
		{
			int x = 0, y = 80;
			for (int i = 0; i < count; i++,x = r.nextInt(35),y = r.nextInt(50) + 80) {
				marble = new Ellipse2D.Double(x, y, 20, 20);
				g2.setPaint(Color.BLUE);
				g2.fill(marble);
				g2.setPaint(Color.BLACK);
				g2.draw(marble);
			}
		
		}
	}
	
	public boolean isStore() {
		return isStore;
	}
}
