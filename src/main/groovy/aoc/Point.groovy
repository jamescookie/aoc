package aoc

class Point {
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

    boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point pt = (Point) obj
            return (x == pt.x) && (y == pt.y)
        }
        return super.equals(obj)
    }
}
