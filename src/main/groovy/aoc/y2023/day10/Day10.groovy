package aoc.y2023.day10

import aoc.Point

class Day10 {
    public static final List<String> VALID_UP = ['|', '7', 'F']
    public static final List<String> VALID_RIGHT = ['-', '7', 'J']
    public static final List<String> VALID_DOWN = ['|', 'L', 'J']
    public static final List<String> VALID_LEFT = ['-', 'L', 'F']
    public static def VALID = [
            'S': [VALID_UP, VALID_RIGHT, VALID_DOWN, VALID_LEFT],
            '|': [VALID_UP, [], VALID_DOWN, []],
            '-': [[], VALID_RIGHT, [], VALID_LEFT],
            'L': [VALID_UP, VALID_RIGHT, [], []],
            'J': [VALID_UP, [], [], VALID_LEFT],
            '7': [[], [], VALID_DOWN, VALID_LEFT],
            'F': [[], VALID_RIGHT, VALID_DOWN, []]
    ]

    static part1(String s) {
        def input = new Input(s)
        def loop = [input.start]
        Optional<Point> next = Optional.of(input.start)
        while (true) {
            next = nextPipe(input, next.get(), loop)
            if (next.isEmpty()) {
                break
            } else {
                loop << next.get()
            }
        }
        return (loop.size() / 2).intValue()
    }

    static part2(String s) {
        def input = new Input(s)
        def loop = [input.start]
        Optional<Point> next = Optional.of(input.start)
        while (true) {
            next = nextPipe(input, next.get(), loop)
            if (next.isEmpty()) {
                break
            } else {
                loop << next.get()
            }
        }
        def outside = []
        for (x in 0..<input.rows.size()) {
            def row = input.rows[x]
            for (y in 0..<row.size()) {
                def point = new Point(x, y)
                if (canGetOut(input, point, loop)) {
                    input.rows[x][y] = 'O'
                    outside << point
                }
            }
        }
        boolean allDone = false
        while (!allDone) {
            boolean found = false
            for (x in 0..<input.rows.size()) {
                def row = input.rows[x]
                for (y in 0..<row.size()) {
                    def point = new Point(x, y)
                    if (input.rows[x][y] == 'O') {
                        def neighbours = checkNeighbours(input, point)
                        if (neighbours) {
                            found = true
                            outside << neighbours
                        } else {
                            input.rows[x][y] = 'D'
                        }
                    }
                }
            }
            allDone = !found
        }
        def inside = []
        for (x in 0..<input.rows.size()) {
            def row = input.rows[x]
            for (y in 0..<row.size()) {
                def point = new Point(x, y)
                if (input.rows[x][y] == 'P') {
                    inside << point
                }
            }
        }
        return inside.size()
    }

    static boolean canGetOut(def input, Point point, def loop) {
        if (loop.contains(point)) {
            return false
        }
        def neighbours = Point.neighbours(input.rows, point)
        if (neighbours.size() < 4) {
            return true
        } else {
            for (Point neighbour in neighbours) {
                if (input.rows[neighbour.x][neighbour.y] == 'O') {
                    return true
                }
            }
            input.rows[point.x][point.y] = 'P'
        }
        return false
    }

    static List<Point> checkNeighbours(def input, Point point) {
        def neighbours = Point.neighbours(input.rows, point)
        def found = []
        for (Point neighbour in neighbours) {
            if (input.rows[neighbour.x][neighbour.y] == 'P') {
                found << neighbour
                input.rows[neighbour.x][neighbour.y] = 'O'
            }
        }
        return found
    }

    static Optional<Point> nextPipe(def input, Point currentPoint, List<Point> loop) {
        def points = neighbours(input.rows, currentPoint)
        String current = input.rows[currentPoint.x][currentPoint.y]
        Optional<Point> nextPoint = Optional.empty()
        for (i in 0..<points.size()) {
            nextPoint = points[i]
            if (nextPoint.isPresent() && !loop.contains(nextPoint.get())) {
                String nextPiece = input.rows[nextPoint.get().x][nextPoint.get().y]
                if (VALID[current][i].contains(nextPiece)) {
                    break
                } else {
                    nextPoint = Optional.empty()
                }
            }
        }
        return nextPoint
    }

    static class Input {
        List<List<String>> rows
        Point start

        Input(String s) {
            this.rows = s.tokenize('\n').collect { it.toCharArray() }
            for (x in 0..<rows.size()) {
                def row = rows[x]
                for (y in 0..<row.size()) {
                    if (row[y] == 'S') {
                        start = new Point(x, y)
                    }
                }
            }
        }
    }

    static List<Optional<Point>> neighbours(input, Point p) {
        List<Point> points = []
        if (p.x - 1 >= 0) {
            points << Optional.of(new Point(p.x - 1, p.y))
        } else {
            points << Optional.empty()
        }
        if (p.y + 1 < input[p.x].size()) {
            points << Optional.of(new Point(p.x, p.y + 1))
        } else {
            points << Optional.empty()
        }
        if (p.x + 1 < input.size()) {
            points << Optional.of(new Point(p.x + 1, p.y))
        } else {
            points << Optional.empty()
        }
        if (p.y - 1 >= 0) {
            points << Optional.of(new Point(p.x, p.y - 1))
        } else {
            points << Optional.empty()
        }
        return points
    }
}
