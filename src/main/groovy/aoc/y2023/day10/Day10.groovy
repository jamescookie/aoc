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

    static Optional<Point> nextPipe(def input, Point currentPoint, List<Point> loop) {
        def points = neighbours(input.rows, currentPoint)
        String current = input.rows[currentPoint.y][currentPoint.x]
        Optional<Point> nextPoint = Optional.empty()
        for (i in 0..<points.size()) {
            nextPoint = points[i]
            if (nextPoint.isPresent() && !loop.contains(nextPoint.get())) {
                String nextPiece = input.rows[nextPoint.get().y][nextPoint.get().x]
                if (VALID[current][i].contains(nextPiece)) {
                    break
                } else {
                    nextPoint = Optional.empty()
                }
            }
        }
        return nextPoint
    }

    static part2(String s) {
        return new Input(s).rows
                .inject(0) { a, b -> a + b.size() }
    }

    static class Input {
        List<List<String>> rows
        Point start

        Input(String s) {
            this.rows = s.tokenize('\n').collect { it.toCharArray() }
            for (y in 0..<rows.size()) {
                def row = rows[y]
                for (x in 0..<row.size()) {
                    if (row[x] == 'S') {
                        start = new Point(x, y)
                    }
                }
            }
        }
    }

    static List<Optional<Point>> neighbours(input, Point p) {
        List<Point> points = []
        if (p.y - 1 >= 0) {
            points << Optional.of(new Point(p.x, p.y - 1))
        } else {
            points << Optional.empty()
        }
        if (p.x + 1 < input.size()) {
            points << Optional.of(new Point(p.x + 1, p.y))
        } else {
            points << Optional.empty()
        }
        if (p.y + 1 < input[p.x].size()) {
            points << Optional.of(new Point(p.x, p.y + 1))
        } else {
            points << Optional.empty()
        }
        if (p.x - 1 >= 0) {
            points << Optional.of(new Point(p.x - 1, p.y))
        } else {
            points << Optional.empty()
        }
        return points
    }
}
