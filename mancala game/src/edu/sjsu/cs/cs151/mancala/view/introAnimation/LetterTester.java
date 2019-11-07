package edu.sjsu.cs.cs151.mancala.view.introAnimation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LetterTester {

private static final int letterWidth = 25;
private static final int letterHeight = 75;
private static final int space = 0;
private static final int iconWidth = 200;
private static final int iconHeight = 300;

	public static void main(String[] args) 
    {
        JFrame frame = new JFrame();    
        final MoveableShape m = new LetterM(0, 0, letterHeight, letterWidth);
        final MoveableShape a1 = new LetterA(0, 300, letterHeight, letterWidth);
        final MoveableShape n = new LetterN(0, 0, letterHeight, letterWidth);
        final MoveableShape c = new LetterC(0, 300, letterHeight, letterWidth);
        final MoveableShape a2 = new LetterA(0, 0, letterHeight, letterWidth);
        final MoveableShape l = new LetterL(0, 300, letterHeight, letterWidth);
        final MoveableShape a3 = new LetterA(0, 0, letterHeight, letterWidth);

        ShapeIcon icon1 = new ShapeIcon(m, iconWidth, iconHeight);
        final JLabel label1 = new JLabel(icon1);
        ShapeIcon icon2 = new ShapeIcon(a1, iconWidth, iconHeight);
        final JLabel label2 = new JLabel(icon2);
        ShapeIcon icon3 = new ShapeIcon(n, iconWidth, iconHeight);
        final JLabel label3 = new JLabel(icon3);
        ShapeIcon icon4 = new ShapeIcon(c, iconWidth, iconHeight);
        final JLabel label4 = new JLabel(icon4);
        ShapeIcon icon5 = new ShapeIcon(a2, iconWidth, iconHeight);
        final JLabel label5 = new JLabel(icon5);
        ShapeIcon icon6 = new ShapeIcon(l, iconWidth, iconHeight);
        final JLabel label6 = new JLabel(icon6);
        ShapeIcon icon7 = new ShapeIcon(a3, iconWidth, iconHeight);
        final JLabel label7 = new JLabel(icon7);

        frame.setLayout(new FlowLayout());
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        frame.add(label7);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        final int delay = 50;
        Timer t = new Timer(delay, new
                ActionListener() {
                    public void actionPerformed(ActionEvent event) {
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
                });
        t.start();
	}
}

