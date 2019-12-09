package edu.sjsu.cs.cs151.mancala.view.introAnimation;

import edu.sjsu.cs.cs151.mancala.view.PlayScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class creates a JFrame with the introduction animation 
 * 	and a start button on it
 */
public class IntroAnimation {

	private static final int letterWidth = 25;
	private static final int letterHeight = 75;
	private static final int iconWidth = 80;
	private static final int iconHeight = 300;


	private static Random rand = new Random();
	private static float r = rand.nextFloat();
	private static float g = rand.nextFloat();
	private static float b = rand.nextFloat();
	private JFrame frame;
	private JPanel main;
	private JPanel animationPanel;

	private static Color randomColor = new Color(r, g, b);

	/**
	 * Constructs a new IntroAnimation
	 * @param ps view to start after
	 */
	public IntroAnimation(PlayScreen ps) 
	{
		frame = new JFrame();
		main = new JPanel();
		animationPanel = new JPanel();
		frame.getContentPane().setBackground( Color.BLACK );
		main.setBackground( Color.BLACK );
		animationPanel.setBackground( Color.BLACK );
		main.setLayout(new BorderLayout());
		animationPanel.setLayout(new FlowLayout());
		main.setLayout(new BorderLayout());
		main.setPreferredSize(new Dimension(800,300));

		final MoveableShape m = new LetterM(0, 0, letterHeight, letterWidth);
		final MoveableShape a1 = new LetterA(0, 300, letterHeight, letterWidth);
		final MoveableShape n = new LetterN(0, 0, letterHeight, letterWidth);
		final MoveableShape c = new LetterC(0, 300, letterHeight, letterWidth);
		final MoveableShape a2 = new LetterA(0, 0, letterHeight, letterWidth);
		final MoveableShape l = new LetterL(0, 300, letterHeight, letterWidth);
		final MoveableShape a3 = new LetterA(0, 0, letterHeight, letterWidth);

		ShapeIcon icon1 = new ShapeIcon(m, iconWidth, iconHeight);
		final JLabel label1 = new JLabel(icon1);
		label1.setForeground(Color.GREEN);
		ShapeIcon icon2 = new ShapeIcon(a1, iconWidth, iconHeight);
		final JLabel label2 = new JLabel(icon2);
		label2.setForeground(Color.GREEN);
		ShapeIcon icon3 = new ShapeIcon(n, iconWidth, iconHeight);
		final JLabel label3 = new JLabel(icon3);
		label3.setForeground(Color.GREEN);
		ShapeIcon icon4 = new ShapeIcon(c, iconWidth, iconHeight);
		final JLabel label4 = new JLabel(icon4);
		label4.setForeground(Color.GREEN);
		ShapeIcon icon5 = new ShapeIcon(a2, iconWidth, iconHeight);
		final JLabel label5 = new JLabel(icon5);
		label5.setForeground(Color.GREEN);
		ShapeIcon icon6 = new ShapeIcon(l, iconWidth, iconHeight);
		final JLabel label6 = new JLabel(icon6);
		label6.setForeground(Color.GREEN);
		ShapeIcon icon7 = new ShapeIcon(a3, iconWidth, iconHeight);
		final JLabel label7 = new JLabel(icon7);
		label7.setForeground(Color.GREEN);

		JButton startButton = new JButton("Start Game");
		startButton.addActionListener(event ->
		{
			close();
			ps.frameSetVisible();
		});
		startButton.setBackground(Color.DARK_GRAY);
		startButton.setForeground(Color.WHITE);
		startButton.setFocusable(false);

		
		animationPanel.add(label1);
		animationPanel.add(label2);
		animationPanel.add(label3);
		animationPanel.add(label4);
		animationPanel.add(label5);
		animationPanel.add(label6);
		animationPanel.add(label7);
		
		main.add(animationPanel, BorderLayout.CENTER);
		main.add(startButton, BorderLayout.SOUTH);
		frame.add(main);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		final int delay = 50;
		Timer t = new Timer(delay, new
				ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (m.getY() != iconHeight / 2)
						{
							m.translate(0, 1);
							label1.repaint();
							a1.translate(0, -1);
							label2.repaint();
							n.translate(0, 1);
							label3.repaint();
							c.translate(0, -1);
							label4.repaint();
							a2.translate(0, 1);
							label5.repaint();
							l.translate(0, -1);
							label6.repaint();
							a3.translate(0, 1);
							label7.repaint();
						}
					}
				});
		t.start();
	}
	
	/**
	 * Closes the frame
	 */
	public void close() {
		frame.dispose();
	}
}

