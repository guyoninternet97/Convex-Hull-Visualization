package hullVisual;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class PointBoard {

    Stack<Point> points;

    public PointBoard() {
        this.points = new Stack<>();
    }

    public void paintPoints(Graphics g) {
        g.setColor(Color.black);
        for (Point point : points) {
            g.fillOval(point.x, point.y, 6, 6);
        }
    }

    public boolean addPoint(Point newPoint) {
        if (!points.contains(newPoint)) {
            this.points.push(newPoint);
        } else {
            return false;
        }
        return true;
    }

    public Point removeLastPoint() {
        return points.pop();
    }

    public void removePoint(int x, int y) {
        Point removePoint = null;
        for (Point point : points) {
            System.out.println("Examined Point: " + point.x + ", " + point.y);
            System.out.println("Clicked point: " + x + ", " + y);
            if (Math.abs(point.getX() - x) < 10 && Math.abs(point.getY() - y) < 10) {
                System.out.println("what");
                removePoint = new Point(point.x, point.y);
            }
        }

        if (removePoint != null) {
            points.remove(removePoint);
        }
    }

    public ArrayList<Point> getPointList() {
        return new ArrayList<>(points);
    }
}
