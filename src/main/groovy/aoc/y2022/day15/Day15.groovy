package aoc.y2022.day15

import aoc.Point

class Day15 {
    static part1(String inputString, int y) {
        def input = inputString.split(System.lineSeparator()).collect {line->
            line -= 'Sensor at x=';
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect{new Point(it)}
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
        excluded.findAll{it.y == y}*.x.forEach{found[it-minX]=false}

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



    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }
}
/*

    static part2(String inputString, int max) {
        def input = inputString.split(System.lineSeparator()).collect { line ->
            line -= 'Sensor at x=';
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect { new Point(it) }
        }

        Set<Point> excluded = input.collect { it[0] } + input.collect { it[1] }
        int minX = (excluded*.x).min() as int
        int maxX = (excluded*.x).max() as int
        List<Boolean> found = new ArrayList<>((maxX - minX) + 1)

        for (i in 0..<input.size()) {
            Point sensor = input[i][0]
            Point beacon = input[i][1]
            int dx = Math.abs(sensor.x - beacon.x);
            int dy = Math.abs(sensor.y - beacon.y);
            find(sensor, dx + dy, 0, max, excluded, found)
        }

        if (found.size() == 1)
            return (found[0].x * 4000000) + found[0].y
        else
            return null
    }

    static void find(Point p, int size, int min, int max, Set<Point> excluded, Set<Point> found) {
        for (y in -size..<size + 1) {
            if (p.y + y > max) continue
            if (p.y + y < min) continue
            int amount = size - Math.abs(y)
            for (x in -amount..<amount + 1) {
                if (p.x + x > max) continue
                if (p.x + x < min) continue
                def point = new Point(p.x + x, p.y + y)
                if (!excluded.contains(point)) {
                    if (found.contains(point)) {
                        found.remove(point)
                        excluded << point
                    } else {
                        found << point
                    }
                }
            }
        }
    }
 */
