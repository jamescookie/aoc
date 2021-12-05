package aoc

class Point implements Comparable<Point> {
    public int x
    public int y

    Point() {
        this(0, 0)
    }

    Point(Point p) {
        this(p.x, p.y)
    }

    Point(int x, int y) {
        this.x = x
        this.y = y
    }

    Point(String s) {
        (x, y) = s.tokenize(',').collect { Integer.parseInt(it) }
    }

    Point getLocation() {
        return new Point(x, y)
    }

    void setLocation(Point p) {
        setLocation(p.x, p.y)
    }

    void setLocation(int x, int y) {
        move(x, y)
    }

    void setLocation(double x, double y) {
        this.x = (int) Math.floor(x + 0.5)
        this.y = (int) Math.floor(y + 0.5)
    }

    void move(int x, int y) {
        this.x = x
        this.y = y
    }

    void translate(int dx, int dy) {
        this.x += dx
        this.y += dy
    }

    @Override
    int hashCode() {
        return x.hashCode() * y.hashCode()
    }

    @Override
    boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point pt = (Point) obj
            return (x == pt.x) && (y == pt.y)
        }
        return super.equals(obj)
    }

    String toString() {
        x + ',' + y
    }

    static Set<Point> pointsBetween(Point p1, Point p2) {
        int x1 = p1.x
        int y1 = p1.y
        int x2 = p2.x
        int y2 = p2.y
        Set<Point> points = []
        // delta of exact value and rounded value of the dependent variable
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                points << new Point(x, y);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                points << new Point(x, y);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        points.remove(p1)
        points.remove(p2)
        return points
    }

    @Override
    int compareTo(Point p2) {
        return Comparator.comparingInt({ p -> p.x })
                .thenComparingInt({ p -> p.y })
                .compare(this, p2)
    }
}
