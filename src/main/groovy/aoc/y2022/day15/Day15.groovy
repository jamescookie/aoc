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

        return new IntRange(found*.from.min(), found*.to.max()).size() - excluded.findAll { it.y == y }*.x.size()
    }

    static part2(String inputString, int max) {
        def input = inputString.split(System.lineSeparator()).collect { line ->
            line -= 'Sensor at x=';
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect { new Point(it) }
        }

        long start = System.currentTimeMillis()
        for (y in 0..<max + 1) {
            if (y % 100000 == 0) println("$y: ${System.currentTimeMillis() - start}")
            Collection<MyRange> found = new ArrayList<>()
            for (i in 0..<input.size()) {
                findAllOnLine(input[i][0], Math.abs(input[i][0].x - input[i][1].x) + Math.abs(input[i][0].y - input[i][1].y), y, found, 0)
            }
            if (found.size() > 1) {
                return (((found*.to.min() + 1) * 4000000L) + y) as long
            }
        }

        return 0
    }

    static void findAllOnLine(Point p, int size, int y, Collection<MyRange> found, int minX) {
        def startY = y - p.y
        if (startY < -size) return
        if (startY > size + 1) return
        int amount = size - Math.abs(startY)
        int start = -amount + p.x - minX
        int end = amount + p.x - minX

        found << new MyRange(start, end)
        reduceRanges(found)
    }

    static void reduceRanges(Collection<MyRange> ranges) {
        if (ranges.size() == 1) return
        while (true) {
            boolean merge = false
            for (i in 0..<ranges.size() - 1) {
                for (j in i + 1..<ranges.size()) {
                    def range1 = ranges[i]
                    def range2 = ranges[j]
                    def merged = range1.merge(range2)
                    if (merged) {
                        merge = true
                        ranges.remove(range2)
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

        boolean contains(int x) {
            return x >= from && x <= to
        }

        boolean merge(MyRange other) {
            int end = other.to
            int start = other.from
            if (contains(start) && contains(end)) {
                return true
            } else if (start < from && contains(end)) {
                from = start
                return true
            } else if (contains(start) && to < end) {
                to = end
                return true
            } else if (start < from && to < end) {
                from = start
                to = end
                return true
            } else {
                return false
            }
        }
    }
}
