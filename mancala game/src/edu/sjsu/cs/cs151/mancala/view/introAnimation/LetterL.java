package edu.sjsu.cs.cs151.mancala.view.introAnimation;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class LetterL implements MoveableShape {
    private int height;
    private int width;
    private int x;
    private int y;

    public LetterL (int x, int y, int height, int width) {
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
        Point2D.Double top = new Point2D.Double(x, y);
        Point2D.Double bleft = new Point2D.Double(x, y + height);
        Point2D.Double bright = new Point2D.Double(x + width, y + height);

        Line2D.Double left = new Line2D.Double(top, bleft);
        Line2D.Double bottom = new Line2D.Double(bleft, bright);

        g.draw(left);
        g.draw(bottom);
    }
    
    public int getY() {
    	return y;
    }
}

