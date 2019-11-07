package edu.sjsu.cs.cs151.mancala.view.introAnimation;

import java.awt.*;
import java.awt.geom.*;

public class LetterM implements MoveableShape {
    private int height;
    private int width;
    private int x;
    private int y;

    public LetterM (int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void translate(int dx, int dy) {
        x+=dx;
        y+=dy;
    }

    public void draw(Graphics2D g)
    {
        Point2D.Double tleft = new Point2D.Double(x, y);
        Point2D.Double bleft = new Point2D.Double(x, y + height);
        Point2D.Double tright = new Point2D.Double(x + width, y);
        Point2D.Double bright = new Point2D.Double(x + width, y + height);
        Point2D.Double mid = new Point2D.Double((x + width) / 2, y + height / 2);

        Line2D.Double leftSide = new Line2D.Double(tleft, bleft);
        Line2D.Double rightSide = new Line2D.Double(tright, bright);
        Line2D.Double leftAngle = new Line2D.Double(tleft, mid);
        Line2D.Double rightAngle = new Line2D.Double(tright, mid);

        g.draw(leftSide);
        g.draw(rightSide);
        g.draw(leftAngle);
        g.draw(rightAngle);
    }

	public int getY() {
    	return y;
    }
}

