package hullVisual;

import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class PointBoard {

    Stack<Point> points;
    Stack<Point> hull;
    public boolean shouldDrawLines = false;
    ArrayList<Pair<Point, Point>> lines = new ArrayList<>();


    public PointBoard() {
        this.points = new Stack<>();
        this.hull = new Stack<>();
    }

    public void giveLines(ArrayList<Pair<Point, Point>> lines) {
        this.lines = lines;
    }

    public void paintPoints(Graphics g) {
        g.setColor(Color.black);
        for (Point point : points) {
            if (hull.contains(point)) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }
            g.fillOval(point.x, point.y, 6, 6);
        }

        if (shouldDrawLines) {
            drawLines(g);
        }
    }

    private void drawLines(Graphics g) {
        for (Pair<Point, Point> pointPair : lines) {
            System.out.println("Point Pair: " + pointPair.getKey() + ", " + pointPair.getValue());
            g.setColor(Color.BLACK);
            g.drawLine(pointPair.getKey().x, pointPair.getKey().y, pointPair.getValue().x, pointPair.getValue().y);
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

    public void setHullPoint(Point point) {
        hull.add(point);
    }

    public void removeHullPoint(Point point) {
        if (hull.contains(point)) {
            hull.remove(point);
        }
    }

    public void clearHullPoints() {
        hull.clear();
    }
}
