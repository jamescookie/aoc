package aoc.y2023.day17

import aoc.Point

class Day17 {
    public static final Point EAST = new Point(0, 1)
    public static final Point SOUTH = new Point(1, 0)
    public static final Point NORTH = new Point(-1, 0)
    public static final Point WEST = new Point(0, -1)
    static def RULES = [

    ]

    static part1(String s) {
        def input = new Input(s)
        return findBestRoute(input.rows)
    }

    static part2(String s) {
        return new Input(s).rows
                .inject(0) { a, b -> a + b.size() }
    }

    static class Input {
        List<List<Integer>> rows

        Input(String s) {
            rows = s.tokenize('\n')*.collect { it as int }
        }
    }

    static int findBestRoute(List<List<Integer>> rows) {
        int best = (rows.collect { it.sum() }.sum() / (rows.size() * rows[0].size())) * (rows.size() + rows[0].size())
        List<Point> bestRoute = []
        Point end = new Point(rows.size() - 1, rows[0].size() - 1)
        def stack = [new RouteChoice(new Point(0, 0), rows, [], 0)]

        while (!stack.isEmpty()) {
            RouteChoice choice = stack.pop()
            if (choice.point == end) {
                if (choice.value < best) {
                    best = choice.value
                    bestRoute = choice.previous
                    Point.print(bestRoute, false)
                    println('----')
                }
            } else if (choice.value < best) {
                for (Point neighbour in choice.neighbours) {
                    def next = new RouteChoice(neighbour, rows, choice.previous, choice.value)
                    if (stack.contains(next)) {
                        def existing = stack.find { next == it }
                        if (next.neighbours.minus(existing.previous).containsAll(existing.neighbours.minus(next.previous))) {
                            if (existing.value > next.value) {
                                stack.remove(existing)
                                stack.add(next)
                            }
                        } else {
                            stack.add(next)
                        }
                    } else {
                        stack.add(next)
                    }
                }
            }
        }

        return best
    }

    static class RouteChoice {
        int value
        Point point
        Set<Point> neighbours
        List<Point> previous

        RouteChoice(Point point, List<List<Integer>> rows, List<Point> previous, int value) {
            this.previous = new ArrayList<>(previous)
            this.previous.add(point)
            this.value = value + rows[point.x][point.y]
            this.point = point
            this.neighbours = Point.neighbours(rows, point)
            this.neighbours.removeIf { this.previous.contains(it) }
            if (previous.size() > 3) {
                this.neighbours.removeIf { neighbour ->
                    def diff = point.diff(neighbour)
                    def diffs = (0..<3).collect { i -> this.previous[-2 - i].diff(this.previous[-1 - i]) }
                    def every = diffs.every { (diff == it) }
                    return every
                }
            }
        }

        @Override
        boolean equals(Object obj) {
            return obj instanceof RouteChoice ? (point.x == ((RouteChoice) obj).point.x) && (point.y == ((RouteChoice) obj).point.y) : false
        }
    }
}
