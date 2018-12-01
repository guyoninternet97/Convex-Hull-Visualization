package hullVisual;

import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
class ConvexHullFinder {
    private static ConvexHullFinder ourInstance = new ConvexHullFinder();
    private PointBoard board;
    private ArrayList<Pair<Point, Point>> pairs = new ArrayList<>();


    static ConvexHullFinder getInstance() {
        return ourInstance;
    }

    private ConvexHullFinder() {
    }

    void givePointBoard(PointBoard board) {
        this.board = board;
    }

    //Got some generic algorithm help from geeks for geeks
    ArrayList<Point> solve(PointBoard board) {
        pairs.clear();
        board.clearHullPoints();
        ArrayList<Point> points = board.getPointList();

        ArrayList<Point> convexHull = new ArrayList<>();

        if (points.size() <= 3) {
            return points;
        }

        Point leftmostPoint = points.get(0);
        int leftmostPointIndex = 0;
        for (int i = 0; i < points.size(); i++) {
            if (leftmostPoint.x < points.get(i).x) {
                leftmostPoint = points.get(i);
                leftmostPointIndex = i;
            }
        }

        int index = leftmostPointIndex;
        do {
            convexHull.add(points.get(index));
            int examiningPoint = (index + 1) % points.size();
            for (int i = 0; i < points.size(); i++) {
                if (getOrientation(points.get(index), points.get(i), points.get(examiningPoint)) == 2) {
                    examiningPoint = i;
                }

            }
            pairs.add(new Pair<>(points.get(examiningPoint), points.get(index)));
            index = examiningPoint;

        } while (index != leftmostPointIndex);
        board.giveLines(pairs);
        board.shouldDrawLines = true;

        return convexHull;
    }

    //Used some code from geeks for geeks for this section, to help me understand the idea behind the algorithm.
    private int getOrientation(Point pointA, Point pointB, Point pointC) {
        int value = (pointB.y - pointA.y) * (pointC.x - pointB.x) -
                (pointB.x - pointA.x) * (pointC.y - pointB.y);
        if (value == 0) {
            return 0;
        }

        return (value > 0)? 1: 2;
    }
}
