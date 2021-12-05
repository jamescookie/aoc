package aoc

import java.awt.geom.Line2D

class Line {
    Point p1
    Point p2
    List<Point> points

    Line(Point p1, Point p2) {
        this.p1 = p1
        this.p2 = p2
        this.points = [p1] + Point.pointsBetween(p1, p2) + [p2]
    }

    boolean intersectsLine(Line l2) {
        return new Line2D.Double(p1.x, p1.y, p2.x, p2.y).intersectsLine(new Line2D.Double(l2.p1.x, l2.p1.y, l2.p2.x, l2.p2.y))
    }

    boolean equals(Object obj) {
        if (obj instanceof Line) {
            Line l2 = (Line) obj
            return (p1 == l2.p1 && p2 == l2.p2) || (p1 == l2.p2 && p2 == l2.p1)
        }
        return super.equals(obj)
    }
}
