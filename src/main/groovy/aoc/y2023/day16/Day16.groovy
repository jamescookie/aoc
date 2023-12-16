package aoc.y2023.day16

import aoc.Point

class Day16 {
    public static final Point NORTH = new Point(-1, 0)
    public static final Point EAST = new Point(0, 1)
    public static final Point SOUTH = new Point(1, 0)
    public static final Point WEST = new Point(0, -1)
    public static char MIRROR1 = ('/' as char)
    public static char MIRROR2 = ('\\' as char)
    public static char SPLITTER1 = ('-' as char)
    public static char SPLITTER2 = ('|' as char)
    public static char EMPTY = ('.' as char)
    public static Map<Character, Map<Point, List<Point>>> DIRECTIONS = [
            (EMPTY)    : [(NORTH): [NORTH], (EAST): [EAST], (SOUTH): [SOUTH], (WEST): [WEST]],
            (MIRROR1)  : [(NORTH): [EAST], (EAST): [NORTH], (SOUTH): [WEST], (WEST): [SOUTH]],
            (MIRROR2)  : [(NORTH): [WEST], (EAST): [SOUTH], (SOUTH): [EAST], (WEST): [NORTH]],
            (SPLITTER1): [(NORTH): [EAST, WEST], (EAST): [EAST], (SOUTH): [EAST, WEST], (WEST): [WEST]],
            (SPLITTER2): [(NORTH): [NORTH], (EAST): [NORTH, SOUTH], (SOUTH): [SOUTH], (WEST): [NORTH, SOUTH]]
    ]

    static part1(String s) {
        def input = new Input(s)
        Set<Point> points = findBeam(input.rows, new Point(0, 0), EAST, [])
        return points.size()
    }

    static part2(String s) {
        return new Input(s).rows
                .inject(0) { a, b -> a + b.size() }
    }

    static class Input {
        List<List<Character>> rows

        Input(String s) {
            rows = s.tokenize('\n').collect { it.toCharArray() }
        }
    }

    static Set<Point> findBeam(List<List<Character>> rows, Point next, Point direction, def alreadyHit) {
        Set<Point> points = []
        if (!Point.outOfBounds(rows, next) && !alreadyHit.contains([(next): direction])) {
            char current
            while (true) {
                points.add(new Point(next))
                current = rows[next.x][next.y]
                if (current != EMPTY) {
                    if (alreadyHit.contains([(next): direction])) {
                        break
                    }
                    alreadyHit << [(next): direction]
                }
                List<Point> newDirections = DIRECTIONS.get(current).get(direction)
                if (newDirections.size() == 1) {
                    direction = newDirections[0]
                    next.translate(direction)
                    if (Point.outOfBounds(rows, next)) {
                        break
                    }
                } else {
                    for (Point newDirection in newDirections) {
                        points.addAll(findBeam(rows, new Point(next).translate(newDirection), newDirection, alreadyHit))
                    }
                    break
                }
            }
        }

        return points
    }
}
