package aoc.y2019.day10

import java.awt.*

class MonitoringStation {
    def space
    def max
    static def gcd(m, n) { m = m.abs(); n = n.abs(); n == 0 ? m : m%n == 0 ? n : gcd(n, m % n) }
    static def lcd(m, n) { Math.abs(m * n) / gcd(m, n) }

    MonitoringStation(String input) {
        space = input.split().collect { it.collect { asteroid -> asteroid == '#' ? 1 : 0 } }
        max = [input.size(), input[0].size()].max()
    }

    int best() {
        def max = 0
        for (int i = 0; i < space.size(); i++) {
            def row = space[i]
            for (int j = 0; j < row.size(); j++) {
                if (row[j]) {
                    row[j] = howManySeen(new Point(i, j))
                    if (row[j] > max) {
                        max = row[j]
                    }
                }
            }
        }
        return max
    }

    def howManySeen(point) {
        def seen = []
        for (int i = 1; i <= max; i++) {
            def points = findPoints(point, i)?.findAll { it.x >= 0 && it.y >= 0 && space[it.x] && space[it.x][it.y] }
            points.removeIf {blockedBy(point, it, seen)}
            seen += points
        }
        return seen.size()
    }

    static def findPoints(Point point, int distance) {
        Point corner1 = point.clone()
        Point corner2 = point.clone()
        Point corner3 = point.clone()
        Point corner4 = point.clone()
        corner1.translate(-distance, distance)
        corner2.translate(distance, distance)
        corner3.translate(distance, -distance)
        corner4.translate(-distance, -distance)
        return pointsBetween(corner1.clone(), corner2, 1, 0) +
                pointsBetween(corner2.clone(), corner3, 0, -1) +
                pointsBetween(corner3.clone(), corner4, -1, 0) +
                pointsBetween(corner4.clone(), corner1, 0, 1)
    }

    static def pointsBetween(Point pointA, Point pointB, int x, int y) {
        def points = []
        while (pointA != pointB) {
            points.add(pointA.clone())
            pointA.translate(x, y)
        }
        return points
    }

    static def blockedBy(Point original, Point newPoint, seen) {
        def myLine = tanLine(original, newPoint)

        return seen.find{tanLine(original, it) == myLine} != null
    }

    private static Double tanLine(Point pointA, Point pointB) {
        def x = pointA.x - pointB.x
        def y = pointA.y - pointB.y
        return Math.atan2(x, y)
    }
}
