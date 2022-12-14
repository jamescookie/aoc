package aoc.y2022.day14

import aoc.Point

class Day14 {
    static part1(String inputString) {
        Set<Point> cave = createCave(inputString)
        Point source = new Point(500, 0)
        boolean abyss = false
        Point falling = new Point(source)
        int lowestY = (cave*.y).max() + 1
        HashMap<Point, Boolean> points = [:]
        cave.forEach { points.put(it, false) }
        while (!abyss) {
            Point next = nextPoint(falling, points)
            if (next == null) {
                points.put(falling, true)
                falling = new Point(source)
            } else if (next.y == lowestY) {
                abyss = true
            } else {
                falling = next
            }
        }

        return points.findAll { k, v -> v }.size()
    }

    static part2(String inputString) {
        Set<Point> cave = createCave(inputString)
        Point source = new Point(500, 0)
        boolean done = false
        Point falling = new Point(source)
        int lowestY = (cave*.y).max() + 2
        HashMap<Point, Boolean> points = [:]
        cave.forEach { points.put(it, false) }
        while (!done) {
            Point next = nextPoint(falling, points)
            if (next == null || next.y == lowestY) {
                points.put(falling, true)
                if (falling == source) break
                falling = new Point(source)
            } else {
                falling = next
            }
        }

        return points.findAll { k, v -> v }.size()
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

    static Point nextPoint(Point point, Map<Point, Boolean> points) {
        Point nextDown = new Point(point.x, point.y + 1)
        if (!points.containsKey(nextDown)) {
            return nextDown
        }
        Point nextDownLeft = new Point(point.x - 1, point.y + 1)
        if (!points.containsKey(nextDownLeft)) {
            return nextDownLeft
        }
        Point nextDownRight = new Point(point.x + 1, point.y + 1)
        if (!points.containsKey(nextDownRight)) {
            return nextDownRight
        }
        null
    }
}
