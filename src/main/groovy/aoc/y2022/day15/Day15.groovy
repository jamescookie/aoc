package aoc.y2022.day15

import aoc.Point

class Day15 {
    static part1(String inputString, int y) {
        def input = inputString.split(System.lineSeparator()).collect { line ->
            line -= 'Sensor at x=';
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect { new Point(it) }
        }

        int minX = -4000000
        int maxX = 8000000
        boolean[] found = new boolean[(maxX - minX) + 1]

        for (i in 0..<input.size()) {
            Point sensor = input[i][0]
            Point beacon = input[i][1]
            int dx = Math.abs(sensor.x - beacon.x);
            int dy = Math.abs(sensor.y - beacon.y);
            findAllOnLine(sensor, dx + dy, y, found, minX)
        }
        Set<Point> excluded = input.collect { it[0] } + input.collect { it[1] }
        excluded.findAll { it.y == y }*.x.forEach { found[it - minX] = false }

        return found.findAll { it }.size()
    }

    static void findAllOnLine(Point p, int size, int interest, boolean[] found, int minX) {
        for (y in -size..<size + 1) {
            if (p.y + y != interest) continue
            int amount = size - Math.abs(y)
            for (x in -amount..<amount + 1) {
                int pos = p.x + x - minX
                if (pos < found.size() && pos >= 0) {
                    found[pos] = true
                }
            }
        }
    }

    static part2(String inputString, int max) {
        def input = inputString.split(System.lineSeparator()).collect { line ->
            line -= 'Sensor at x=';
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect { new Point(it) }
        }

        boolean[][] found = new boolean[max + 1]
        for (i in 0..<found.length) {
            boolean[] tmp = new boolean[max + 1]
            found[i] = tmp
        }

        for (i in 0..<input.size()) {
            Point sensor = input[i][0]
            Point beacon = input[i][1]
            int dx = Math.abs(sensor.x - beacon.x);
            int dy = Math.abs(sensor.y - beacon.y);
            for (j in 0..<max + 1) {
                findAllOnLine(sensor, dx + dy, j, found[j], 0)
            }
        }

        for (y in 0..<found.length) {
            boolean[] tmp = found[y]
            for (x in 0..<tmp.length) {
                if (!tmp[x]) {
                    return x * 4000000 + y
                }
            }
        }
        return 0
    }
}
