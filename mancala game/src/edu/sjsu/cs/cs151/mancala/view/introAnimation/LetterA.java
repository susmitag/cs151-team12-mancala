package edu.sjsu.cs.cs151.mancala.view.introAnimation;

import java.awt.*;
import java.awt.geom.*;

public class LetterA implements MoveableShape {
    private int height;
    private int width;
    private int x;
    private int y;

    public LetterA (int x, int y, int height, int width) {
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
        Point2D.Double bleft = new Point2D.Double(x, y + height);
        Point2D.Double bright = new Point2D.Double(x + width, y + height);
        Point2D.Double tleft = new Point2D.Double(x + (width / 3), y);
        Point2D.Double tright = new Point2D.Double((x + width) - (width / 3), y);
        Point2D.Double mleft = new Point2D.Double(x + (width / 6), y + height/2);
        Point2D.Double mright = new Point2D.Double((x + width) - (width / 6), y + height/2);


        Line2D.Double left = new Line2D.Double(bleft, tleft);
        Line2D.Double right = new Line2D.Double(bright, tright);
        Line2D.Double tmid = new Line2D.Double(tleft, tright);
        Line2D.Double mmid = new Line2D.Double(mleft, mright);

        g.draw(left);
        g.draw(right);
        g.draw(tmid);
        g.draw(mmid);
    }

	public int getY() {
    	return y;
    }
}

