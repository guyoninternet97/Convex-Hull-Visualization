package hullVisual;

import java.awt.*;
import java.util.ArrayList;

public class ConvexHullFinder {
    private static ConvexHullFinder ourInstance = new ConvexHullFinder();
    PointBoard board;

    public static ConvexHullFinder getInstance() {
        return ourInstance;
    }

    private ConvexHullFinder() {
    }

    public void givePointBoard(PointBoard board) {
        this.board = board;
    }

    //Got some generic algorithm help from geeks for geeks
    public ArrayList<Point> solve(ArrayList<Point> points) {

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
        System.out.println(index);

        do {
            convexHull.add(points.get(index));
            int examiningPoint = (index + 1) % points.size();
            System.out.println(points.size());
            for (int i = 0; i < points.size(); i++) {
                if (getOrientation(points.get(index), points.get(i), points.get(examiningPoint)) == 2) {
                    examiningPoint = i;
                }

            }
            index = examiningPoint;
            System.out.println(index);
        } while (index != leftmostPointIndex);

        return convexHull;
    }

    //Used some code from geeks for geeks for this section, to help me understand the idea behind the algorithm.
    public int getOrientation(Point pointA, Point pointB, Point pointC) {
        int value = (pointB.y - pointA.y) * (pointC.x - pointB.x) -
                (pointB.x - pointA.x) * (pointC.y - pointB.y);
        if (value == 0) {
            return 0;
        }

        return (value > 0)? 1: 2;
    }
}
