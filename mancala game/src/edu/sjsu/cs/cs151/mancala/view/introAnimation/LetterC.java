package edu.sjsu.cs.cs151.mancala.view.introAnimation;

import java.awt.*;
import java.awt.geom.*;

/**
 * Creates a movable C to be displayed in an animation
 */
public class LetterC implements MoveableShape {
    private int height;
    private int width;
    private int x;
    private int y;

    /**
     * Creates a LetterC
     * @param x initial x location
     * @param y initial y location
     * @param height letter height
     * @param width letter width
     */
    public LetterC (int x, int y, int height, int width) {
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
        Point2D.Double upperLeft = new Point2D.Double(x, y + height / 7);
        Point2D.Double lowerLeft = new Point2D.Double(x, (y + height) - height / 7);
        Point2D.Double tleft = new Point2D.Double(x + width / 3, y);
        Point2D.Double bleft = new Point2D.Double(x + width / 3, y + height);
        Point2D.Double tright = new Point2D.Double(x + width, y);
        Point2D.Double bright = new Point2D.Double(x + width, y + height);

        Line2D.Double left = new Line2D.Double(upperLeft, lowerLeft);
        Line2D.Double tangle = new Line2D.Double(upperLeft, tleft);
        Line2D.Double bangle = new Line2D.Double(lowerLeft, bleft);
        Line2D.Double top = new Line2D.Double(tleft, tright);
        Line2D.Double bottom = new Line2D.Double(bleft, bright);

        g.draw(left);
        g.draw(tangle);
        g.draw(bangle);
        g.draw(top);
        g.draw(bottom);
    }
    
	public int getY() {
    	return y;
    }
}

