package aoc.y2023.day18

import aoc.Point

class Day18 {
    public static final Point NORTH = new Point(-1, 0)
    public static final Point WEST = new Point(0, -1)
    public static final Point SOUTH = new Point(1, 0)
    public static final Point EAST = new Point(0, 1)
    public static Map<String, Point> DIRECTIONS = [
            'U': NORTH,
            'D': SOUTH,
            'R': EAST,
            'L': WEST
    ]

    static part1(String s) {
        def input = new Input(s)
        def start = new Point(0, 0)
        def results = input.rows.inject([start]) { a, b -> a.addAll(b.apply(a[-1]));a }
        assert results[0] == start
        assert results[-1] == start
        assert results.pop() == start
        Point.print(results, false)
        def inside = fillIn(results, new Point(1,1))
        Point.print(inside, false)
        return results.size() + inside.size()
    }

    static part2(String s) {
        return new Input(s).rows
                .inject(0) { a, b -> a + b.size() }
    }

    static List<Point> fillIn(List<Point> points, Point start) {
        def results = []
        def stack = [start]
        int maxX = (points*.x).max() as int
        int maxY = (points*.y).max() as int
        int minX = (points*.x).min() as int
        int minY = (points*.y).min() as int

        while (!stack.isEmpty()) {
            Point next = stack.pop()
            if (!results.contains(next)) {
                results.add(next)
                def neighbours = Point.neighbours(maxX, maxY, minX, minY, next)
                for (Point neighbour in neighbours) {
                    if (!points.contains(neighbour)) {
                        stack.add(neighbour)
                    }
                }
            }
        }

        return results
    }

    static class Instruction {
        Point direction
        int count
        String colour

        Instruction(String s) {
            def tokenize = s.tokenize(' ')
            direction = DIRECTIONS.get(tokenize[0])
            count = tokenize[1] as int
            colour = tokenize[2][1..-2]
        }

        List<Point> apply(Point p) {
            p = new Point(p)
            List<Point> results = []
            for (i in 0..<count) {
                results << p.translate(direction).clone()
            }
            return results
        }
    }

    static class Input {
        List<Instruction> rows

        Input(String s) {
            rows = s.tokenize('\n').collect { new Instruction(it) }
        }
    }

}
