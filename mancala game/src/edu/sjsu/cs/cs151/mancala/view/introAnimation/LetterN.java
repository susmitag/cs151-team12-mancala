package edu.sjsu.cs.cs151.mancala.view.introAnimation;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * Creates a moveable N to be displayed in an animation
 */
public class LetterN implements MoveableShape {
    private int height;
    private int width;
    private int x;
    private int y;

	/**
     * Creates a LetterN
     * @param x initial x location
     * @param y initial y location
     * @param height letter height
     * @param width letter width
     */
    public LetterN (int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /**
     * Moves letter in the appropriate direction
     * @param dx amount to move in the x direction
     * @param dy amount to move in the y direction
     */
    public void translate(int dx, int dy) {
        x+=dx;
        y+=dy;
    }

    /**
     * Draws letter
     * @param g graphics to draw with
     */
    public void draw(Graphics2D g)
    {
        Point2D.Double bleft = new Point2D.Double(x, y + height);
        Point2D.Double tleft = new Point2D.Double(x, y);
        Point2D.Double bright = new Point2D.Double(x + width, y + height);
        Point2D.Double tright = new Point2D.Double(x + width, y);

        Line2D.Double left = new Line2D.Double(bleft, tleft);
        Line2D.Double right = new Line2D.Double(bright, tright);
        Line2D.Double angle = new Line2D.Double(tleft, bright);

        g.draw(left);
        g.draw(right);
        g.draw(angle);
    }

	public int getY() {
    	return y;
    }
}

