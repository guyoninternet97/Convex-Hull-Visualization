package hullVisual;

import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;


class PointBoard {

    private Stack<Point> points;
    private Stack<Point> hull;
    boolean shouldDrawLines = false;
    private ArrayList<Pair<Point, Point>> lines = new ArrayList<>();


    /***
     * Constructor
     */
    PointBoard() {
        this.points = new Stack<>();
        this.hull = new Stack<>();
    }

    /***
     * Give the board a set of lines to draw, which are the hull
     * @param lines The lines which represent the hull
     */
    void giveLines(ArrayList<Pair<Point, Point>> lines) {
        this.lines = lines;
    }

    /***
     * Paint the points on the board
     * @param g The graphics used to draw the board
     */
    void paintPoints(Graphics g) {
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

    /***
     * Draw any lines which are a part of a current hull
     * @param g The current graphics on which to draw
     */
    private void drawLines(Graphics g) {
        for (Pair<Point, Point> pointPair : lines) {
            g.setColor(Color.BLACK);
            g.drawLine(pointPair.getKey().x, pointPair.getKey().y, pointPair.getValue().x, pointPair.getValue().y);
        }
    }

    /***
     * Add a point to the current board
     * @param newPoint The point to add
     */
    void addPoint(Point newPoint) {
        this.points.push(newPoint);
    }

    /***
     * Remove the most recently added point
     */
    void removeLastPoint() {
        points.pop();
    }

    /***
     * Remove the point at an x,y location from the board
     * @param x X location of the point to remove
     * @param y Y location of the point to remove
     */
    void removePoint(int x, int y) {
        Point removePoint = null;
        for (Point point : points) {
            if (Math.abs(point.getX() - x) < 10 && Math.abs(point.getY() - y) < 10) {
                removePoint = new Point(point.x, point.y);
            }
        }

        if (removePoint != null) {
            points.remove(removePoint);
        }
    }

    /***
     * Returns an ArrayList which contains all of the points stored by {@code this}
     * @return The ArrayList containing the points in {@code this}
     */
    ArrayList<Point> getPointList() {
        return new ArrayList<>(points);
    }


    /***
     * Set a point to be considered part of the hull
     * @param point The point to be considered part of the hull
     */
    void setHullPoint(Point point) {
        hull.add(point);
    }

    /***
     * Clear the hull
     */
    void clearHullPoints() {
        hull.clear();
    }
}
