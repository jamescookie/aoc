package aoc

class Point implements Comparable<Point>, Cloneable {
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

    Point translate(Point d) {
        this.x += d.x
        this.y += d.y
        return this
    }

    void translateUndo(Point d) {
        this.x -= d.x
        this.y -= d.y
    }

    @Override
    Object clone() {
        new Point(x, y)
    }

    @Override
    int hashCode() {
        return x * y
    }

    @Override
    boolean equals(Object obj) {
        return obj instanceof Point ? (x == ((Point) obj).x) && (y == ((Point) obj).y) : false
    }

    String toString() {
        x + ',' + y
    }

    static boolean outOfBounds(input, Point p) {
        return p.x < 0 || p.y < 0 || p.x >= input.size() || p.y >= input[0].size()
    }

    static Set<Point> neighbours(input, Point p) {
        Set<Point> points = []
        if (p.y - 1 >= 0) points << new Point(p.x, p.y - 1)
        if (p.y + 1 < input[p.x].size()) points << new Point(p.x, p.y + 1)
        if (p.x - 1 >= 0) points << new Point(p.x - 1, p.y)
        if (p.x + 1 < input.size()) points << new Point(p.x + 1, p.y)
        return points
    }

    static Set<Point> neighboursWithDiagonals(input, Point p) {
        Set<Point> points = Point.neighbours(input, p)
        if (p.x - 1 >= 0 && p.y - 1 >= 0) points << new Point(p.x - 1, p.y - 1)
        if (p.x - 1 >= 0 && p.y + 1 < input[p.x].size()) points << new Point(p.x - 1, p.y + 1)
        if (p.x + 1 < input.size() && p.y - 1 >= 0) points << new Point(p.x + 1, p.y - 1)
        if (p.x + 1 < input.size() && p.y + 1 < input[p.x + 1].size()) points << new Point(p.x + 1, p.y + 1)
        return points
    }

    static Set<Point> pointsBetweenExcludingEnds(Point p1, Point p2) {
        def between = pointsBetween(p1, p2)
        between.remove(p1)
        between.remove(p2)
        return between
    }

    static Set<Point> pointsBetweenIncludingFirst(Point p1, Point p2) {
        def between = pointsBetween(p1, p2)
        between.remove(p2)
        return between
    }

    static Set<Point> pointsBetweenIncludingLast(Point p1, Point p2) {
        def between = pointsBetween(p1, p2)
        between.remove(p1)
        return between
    }

    static Set<Point> pointsBetweenIncludingEnds(Point p1, Point p2) {
        return pointsBetween(p1, p2)
    }

    private static Set<Point> pointsBetween(Point p1, Point p2) {
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
        return points
    }

    Set<Point> findAllInside(int size) {
        Set<Point> points = new HashSet<>()
        for (y in -size..<size + 1) {
            int amount = size - Math.abs(y)
            for (x in -amount..<amount + 1) {
                points << new Point(this.x + x, this.y + y)
            }
        }
        return points
    }

    @Override
    int compareTo(Point p2) {
        int i = this.x <=> p2.x
        if (i != 0) return i
        return this.y <=> p2.y
    }

    static void print(List<Point> points, inverted = true) {
        print(points.collectEntries { [it, '#' as Character] }, inverted)
    }

    static void print(Map<Point, Character> map, inverted = true) {
        Set<Point> points = map.keySet()
        int maxX = (points*.x).max() as int
        int maxY = (points*.y).max() as int
        int minX = (points*.x).min() as int
        int minY = (points*.y).min() as int
        StringBuilder sb = new StringBuilder()
        def defaultChar = '.' as Character

        for (y in (inverted ? (minY..<maxY + 1) : (maxY..minY))) {
            for (x in minX..<maxX + 1) {
                sb.append(map.getOrDefault(new Point(x, y), defaultChar))
            }
            sb.append(System.lineSeparator())
        }
        println sb.toString()
    }
}
