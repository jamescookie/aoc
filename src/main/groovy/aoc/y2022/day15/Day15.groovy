package aoc.y2022.day15

import aoc.Point

class Day15 {
    static part1(String inputString, int y) {
        def input = inputString.split(System.lineSeparator()).collect { line ->
            line -= 'Sensor at x='
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect { new Point(it) }
        }
        Set<Point> excluded = input.collect { it[0] } + input.collect { it[1] }
        int minX = (excluded*.x).min() as int
        int maxX = (excluded*.x).max() as int
        int diff = maxX - minX
        minX -= diff
        List<MyRange> found = []

        for (i in 0..<input.size()) {
            Point sensor = input[i][0]
            Point beacon = input[i][1]
            int dx = Math.abs(sensor.x - beacon.x)
            int dy = Math.abs(sensor.y - beacon.y)
            findAllOnLine(sensor, dx + dy, y, found, minX)
        }

        return new IntRange(found*.from.min() as int, found*.to.max() as int).size() - excluded.findAll { it.y == y }*.x.size()
    }

    static part2(String inputString, int max) {
        def input = inputString.split(System.lineSeparator()).collect { line ->
            line -= 'Sensor at x='
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect { new Point(it) }
        }

        for (y in 0..<max + 1) {
            List<MyRange> found = new ArrayList<>()
            for (i in 0..<input.size()) {
                findAllOnLine(input[i][0], Math.abs(input[i][0].x - input[i][1].x) + Math.abs(input[i][0].y - input[i][1].y), y, found, 0)
            }
            if (found.size() > 1) {
                return (((found*.to.min() + 1) * 4000000L) + y) as long
            }
        }

        return 0
    }

    static void findAllOnLine(Point p, int size, int y, List<MyRange> found, int minX) {
        def startY = y - p.y
        if (startY < -size || startY >= size) return
        int amount = size - Math.abs(startY)
        int start = -amount + p.x - minX
        int end = amount + p.x - minX

        found << new MyRange(start, end)
        reduceRanges(found)
    }

    static void reduceRanges(List<MyRange> ranges) {
        if (ranges.size() == 1) return
        while (true) {
            boolean merge = false
            for (i in 0..<ranges.size() - 1) {
                for (j in i + 1..<ranges.size()) {
                    merge = ranges[i].merge(ranges[j])
                    if (merge) {
                        ranges.remove(j)
                        break
                    }
                }
                if (merge) break
            }
            if (!merge) {
                break
            }
        }
    }

    static class MyRange {
        int from
        int to

        MyRange(int start, int end) {
            from = start
            to = end
        }

        boolean merge(MyRange other) {
            if (other.to < from || other.from > to) {
                return false
            }
            if (to < other.to) {
                to = other.to
            }
            if (other.from < from) {
                from = other.from
            }
            return true
        }
    }
}
