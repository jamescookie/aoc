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

        return new IntRange(found*.getFrom().min(), found*.getTo().max()).size() - excluded.findAll { it.y == y }*.x.size()
    }

    static void findAllOnLine(Point p, int size, int y, List<MyRange> found, int minX) {
        def startY = y - p.y
        if (startY < -size) return
        if (startY > size + 1) return
        int amount = size - Math.abs(startY)
        int start = -amount + p.x - minX
        int end = amount + p.x - minX

        if (found.isEmpty()) found << new MyRange(start, end)
        Set<MyRange> newRanges = []
        found.forEach{newRanges.addAll(mergeRange(it, start, end))}
        found.clear()
        found.addAll(reduceRanges(newRanges))
    }

    static Collection<MyRange> reduceRanges(Collection<MyRange> ranges) {
        Set<MyRange> newRanges = new HashSet<>(ranges)
        while(true) {
            boolean merge = false
            for (i in 0..<newRanges.size() - 1) {
                for (j in i + 1..<newRanges.size()) {
                    def range1 = newRanges[i]
                    def range2 = newRanges[j]
                    def merged = mergeRange(range1, range2)
                    if (merged.size() == 1) {
                        merge = true
                        newRanges.remove(range1)
                        newRanges.remove(range2)
                        newRanges << merged[0]
                        break
                    }
                }
                if (merge) break
            }
            if (!merge) {
                break
            }
        }
        return newRanges
    }

    static List<MyRange> mergeRange(MyRange range1, MyRange range2) {
        return mergeRange(range1, range2.getFrom(), range2.getTo())
    }

    static List<MyRange> mergeRange(MyRange range, int start, int end) {
        if (range.contains(start) && range.contains(end)) {
            return [range]
        } else if (start < range.getFrom() && range.contains(end)) {
            return [new MyRange(start, range.getTo())]
        } else if (range.contains(start) && range.getTo() < end) {
            return [new MyRange(range.getFrom(), end)]
        } else if (start < range.getFrom() && range.getTo() < end) {
            return [new MyRange(start, end)]
        } else {
            return [new MyRange(start, end), range]
        }
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
            List<Range> found = []
            for (i in 0..<input.size()) {
                Point sensor = input[i][0]
                findAllOnLine(sensor, Math.abs(sensor.x - input[i][1].x) + Math.abs(sensor.y - input[i][1].y), y, found, 0)
            }
            if (found.size() > 1) {
                return (((found*.getTo().min() + 1) * 4000000L) + y) as long
            }
        }

        return 0
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
    }
}
