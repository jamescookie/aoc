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
        for (int i = 0; i < space.size(); i++) {
            def row = space[i]
            for (int j = 0; j < row.size(); j++) {
                if (row[j]) {
                    if (!(point.x == i && point.y == j)) {
                        seen += tanLine(point, new Point(i, j))
                    }
                }
            }
        }
        return seen.unique().size()
    }

    private static Double tanLine(Point pointA, Point pointB) {
        def x = pointA.x - pointB.x
        def y = pointA.y - pointB.y
        return Math.atan2(x, y)
    }
}
