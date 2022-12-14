package aoc.y2022.day14

import aoc.Point

class Day14 {
    static part1(String inputString) {
        Set<Point> cave = createCave(inputString)
        Point source = new Point(500, 0)
        boolean abyss = false
        Point falling = new Point(source)
        int lowestY = (cave*.y).max() + 1
        HashSet<Point> points = new HashSet<>(cave)
        while (!abyss) {
            Point next = nextPoint(falling, points)
            if (next == null) {
                points.add(falling)
                falling = new Point(source)
            } else if (next.y == lowestY) {
                abyss = true
            } else {
                falling = next
            }
        }

        return points.size() - cave.size()
    }

    static part2(String inputString) {
        Set<Point> cave = createCave(inputString)
        Point source = new Point(500, 0)
        boolean done = false
        Point falling = new Point(source)
        int lowestY = (cave*.y).max() + 2
        HashSet<Point> points = new HashSet<>(cave)
        while (!done) {
            Point next = nextPoint(falling, points)
            if (next == null || next.y == lowestY) {
                points.add(falling)
                if (falling == source) break
                falling = new Point(source)
            } else {
                falling = next
            }
        }

        return points.size() - cave.size()
    }

    static Set<Point> createCave(String inputString) {
        List<List<Point>> input = inputString.split(System.lineSeparator()).collect { chain -> chain.split(' -> ').collect { new Point(it) } }
        Set<Point> cave = []
        input.each { points ->
            for (i in 0..<points.size() - 1) {
                cave.addAll(Point.pointsBetweenIncludingEnds(points[i], points[i + 1]))
            }
        }
        cave
    }

    static Point nextPoint(Point point, Collection<Point> points) {
        Point nextDown = new Point(point.x, point.y + 1)
        if (!points.contains(nextDown)) {
            return nextDown
        }
        Point nextDownLeft = new Point(point.x - 1, point.y + 1)
        if (!points.contains(nextDownLeft)) {
            return nextDownLeft
        }
        Point nextDownRight = new Point(point.x + 1, point.y + 1)
        if (!points.contains(nextDownRight)) {
            return nextDownRight
        }
        null
    }
}
