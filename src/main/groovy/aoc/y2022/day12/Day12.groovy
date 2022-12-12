package aoc.y2022.day12

import aoc.Point

class Day12 {
    static part1(String inputString) {
        Character[][] input = inputString.tokenize().collect { it.toCharArray() }

        Point start = null
        Point end = null
        List<Point> best = []
        for (x in 0..<input.size()) {
            def row = input[x]
            for (y in 0..<row.size()) {
                if (input[x][y] == 'S') {
                    input[x][y] = 'a'
                    start = new Point(x, y)
                } else if (input[x][y] == 'E') {
                    input[x][y] = 'z'
                    end = new Point(x, y)
                }
                best << new Point(x, y)
            }
        }

        List<Point> current = []
        findPath(input, start, current, end, best)

        return best.size()
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static void findPath(Character[][] input, Point point, List<Point> current, Point end, List<Point> best) {
        if (current.contains(point)) {
            return
        }
        if (current.size() >= best.size()) {
            return
        }
        if (point == end) {
            best.clear()
            best.addAll(current)
            println "best = $best"
            return
        }
        current << point
        char value = input[point.x][point.y]
        def neighbours = Point.neighbours(input, point).findAll {input[it.x][it.y] - value == 1 || input[it.x][it.y] - value == 0}
        for (i in 0..<neighbours.size()) {
            findPath(input, neighbours[i], new ArrayList<Point>(current), end, best)
        }
    }
}
