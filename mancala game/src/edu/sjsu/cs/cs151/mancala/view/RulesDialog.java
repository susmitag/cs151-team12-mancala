package edu.sjsu.cs.cs151.mancala.view;

import javax.swing.*;

public class RulesDialog extends JPanel
{
	public RulesDialog() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JLabel r1 = new JLabel("1) The mancala board consists of 2 rows of 6 holes and 2 stores (1 for each player).");
		JLabel r2 = new JLabel("2) Each hole starts with 4 marbles in it. Each player's store is empty.");
		JLabel r3 = new JLabel("3) The game begins with player1 choosing a hole on their side to sow the marbles from.");
		JLabel r4 = new JLabel("4) Moving counter-clockwise, the marbles from the selected hole are added 1 at a time to the followng holes until the marbles run out.");
		JLabel r5 = new JLabel("5) When sowing, a player's own store gets a marble, while the opponents store is skipped.");
		JLabel r6 = new JLabel("6) If the last marble ends in your store, you get a free turn (get to go again).");
		JLabel r7 = new JLabel("7) If the last marble is placed in an empty store, you capture all marbles in the opposite hole plus that marble.");
		JLabel r8 = new JLabel("8) Any marbles that are captured go into your store.");
		JLabel r9 = new JLabel("9) The game ends when all holes on any side are empty.");
		JLabel r10 = new JLabel("10) The player who still has marbles on their side when the game ends captures those marbles.");
		JLabel r11 = new JLabel("11) The player with the most marbles in their store wins.");
		
		add(r1);
		add(new JLabel(" "));
		add(r2);
		add(new JLabel(" "));
		add(r3);
		add(new JLabel(" "));
		add(r4);
		add(new JLabel(" "));
		add(r5);
		add(new JLabel(" "));
		add(r6);
		add(new JLabel(" "));
		add(r7);
		add(new JLabel(" "));
		add(r8);
		add(new JLabel(" "));
		add(r9);
		add(new JLabel(" "));
		add(r10);
		add(new JLabel(" "));
		add(r11);
	}
}
