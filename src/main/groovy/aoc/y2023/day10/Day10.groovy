package aoc.y2023.day10

import aoc.Point

class Day10 {
    public static final String OUTSIDE = 'O'
    public static final String POTENTIAL = 'P'
    public static final List<String> DIAGONAL_UP = ['L', '7']
    public static final List<String> DIAGONAL_DOWN = ['F', 'J']
    public static final List<String> CORNERS = DIAGONAL_UP + DIAGONAL_DOWN
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
        return (new Input(s).loop.size() / 2).intValue()
    }

    static part2(String s) {
        def input = new Input(s)
        adjustStart(input.rows, input.loop)
        markExits(input.rows, input.loop)
        markOutsideOfLoop(input.rows, input.loop)
        markNeighbours(input.rows)
        return findInsideLoop(input.rows)
    }

    /**
     * Update starting piece to correct pipe
     */
    static def adjustStart(List<List<String>> input, List<Point> loop) {
        String first = input[loop[1].x][loop[1].y]
        String last = input[loop[-1].x][loop[-1].y]
        if (VALID_RIGHT.contains(first)) {
            if (VALID_DOWN.contains(last)) {
                input[loop[0].x][loop[0].y] = loop[1].x > loop[-1].x ? 'L' : 'F'
            } else {
                input[loop[0].x][loop[0].y] = '-'
            }
        } else if (VALID_DOWN.contains(first)) {
            if (VALID_LEFT.contains(last)) {
                input[loop[0].x][loop[0].y] = loop[1].x > loop[-1].x ? '7' : 'J'
            } else {
                input[loop[0].x][loop[0].y] = '|'
            }
        }
    }

    /**
     * Mark all outside locations (and those touching) as "O" (Outside)
     * Anything else that is not pipe is marked as "P" (Potential)
     */
    private static void markExits(List<List<String>> input, List<Point> loop) {
        for (x in 0..<input.size()) {
            for (y in 0..<input[x].size()) {
                def point = new Point(x, y)
                if (!loop.contains(point)) {
                    def neighbours = Point.neighbours(input, point)
                    if (neighbours.size() < 4 || neighbours.any { input[it.x][it.y] == OUTSIDE }) {
                        input[x][y] = OUTSIDE
                    } else {
                        input[x][y] = POTENTIAL
                    }
                }
            }
        }
    }

    /**
     * Mark locations on the _outside_ of the loop as "O"
     */
    private static void markOutsideOfLoop(List<List<String>> input, List<Point> loop) {
        Point point = null
        Integer side = null
        //Find a starting point (and which side) of the pipe that has an an outside location next to it
        for (int i = 0; i < loop.size(); i++) {
            point = loop[i]
            if (!CORNERS.contains(input[point.x][point.y])) {
                def neighbours = allNeighbours(input, point)
                for (j in 0..<neighbours.size()) {
                    def neighbour = neighbours[j]
                    if (!neighbour.isEmpty()) {
                        neighbour = neighbour.get()
                        if (input[neighbour.x][neighbour.y] == OUTSIDE) {
                            side = j
                            break
                        }
                    }
                }
            }
            if (side) break
        }
        if (!side) return
        int offset = loop.indexOf(point) + 1
        for (int i = offset; i < loop.size() + offset; i++) {
            point = loop[i >= loop.size() ? i - loop.size() : i]
            List<Optional<Point>> neighbours = allNeighbours(input, point)
            neighbours[side].ifPresent {
                if (input[it.x][it.y] == POTENTIAL) {
                    input[it.x][it.y] = OUTSIDE
                }
            }
            String current = input[point.x][point.y]
            if (CORNERS.contains(current)) {
                if (DIAGONAL_UP.contains(current)) {
                    switch (side) {
                        case 0:
                            side = 1; break;
                        case 1:
                            side = 0; break;
                        case 2:
                            side = 3; break;
                        case 3:
                            side = 2; break;
                    }
                } else {
                    switch (side) {
                        case 0:
                            side = 3; break;
                        case 1:
                            side = 2; break;
                        case 2:
                            side = 1; break;
                        case 3:
                            side = 0; break;
                    }
                }
                neighbours[side].ifPresent {
                    if (input[it.x][it.y] == POTENTIAL) {
                        input[it.x][it.y] = OUTSIDE
                    }
                }
            }
        }
    }

    /**
     * Find any "P" next to "O" and update it to "O"
     * If there are none for that location, mark as "D" (Done)
     */
    private static void markNeighbours(List<List<String>> rows) {
        boolean stillSomeToFind = true
        while (stillSomeToFind) {
            stillSomeToFind = false
            for (x in 0..<rows.size()) {
                for (y in 0..<rows[x].size()) {
                    if (rows[x][y] == OUTSIDE) {
                        for (Point neighbour in (Point.neighbours(rows, new Point(x, y)))) {
                            if (rows[neighbour.x][neighbour.y] == POTENTIAL) {
                                rows[neighbour.x][neighbour.y] = OUTSIDE
                                stillSomeToFind = true
                            }
                        }
                        if (!stillSomeToFind) {
                            rows[x][y] = 'D'
                        }
                    }
                }
            }
        }
    }

    /**
     * Find anything still marked as "P"
     */
    private static long findInsideLoop(List<List<String>> rows) {
        long inside = 0
        for (x in 0..<rows.size()) {
            for (y in 0..<rows[x].size()) {
                if (rows[x][y] == POTENTIAL) {
                    inside++
                }
            }
        }
        inside
    }

    static Optional<Point> nextPipe(List<List<String>> input, Point currentPoint, List<Point> loop) {
        def neighbours = allNeighbours(input, currentPoint)
        String current = input[currentPoint.x][currentPoint.y]
        Optional<Point> nextPoint = Optional.empty()
        for (i in 0..<neighbours.size()) {
            nextPoint = neighbours[i]
            if (nextPoint.isPresent() && !loop.contains(nextPoint.get())) {
                if (VALID[current][i].contains(input[nextPoint.get().x][nextPoint.get().y] as String)) {
                    break
                } else {
                    nextPoint = Optional.empty()
                }
            } else {
                nextPoint = Optional.empty()
            }
        }
        return nextPoint
    }

    static class Input {
        List<List<String>> rows
        Point start
        List<Point> loop = []

        Input(String s) {
            this.rows = s.tokenize('\n').collect { it.toCharArray().collect { it.toString() } }
            for (x in 0..<this.rows.size()) {
                def row = this.rows[x]
                for (y in 0..<row.size()) {
                    if (row[y] == 'S') {
                        start = new Point(x, y)
                    }
                }
            }
            loop = [start]
            Optional<Point> next = Optional.of(start)
            while (true) {
                next = nextPipe(rows, next.get(), loop)
                if (next.isEmpty()) {
                    break
                } else {
                    loop << next.get()
                }
            }
        }
    }

    /**
     * Find the neighbours of a Point in order (Up, Right, Down, Left)
     */
    static List<Optional<Point>> allNeighbours(List<List<String>> input, Point p) {
        List<Optional<Point>> points = []
        if (p.x - 1 >= 0) {
            points.add(Optional.of(new Point(p.x - 1, p.y)))
        } else {
            points.add(Optional.empty())
        }
        if (p.y + 1 < input[p.x].size()) {
            points.add(Optional.of(new Point(p.x, p.y + 1)))
        } else {
            points.add(Optional.empty())
        }
        if (p.x + 1 < input.size()) {
            points.add(Optional.of(new Point(p.x + 1, p.y)))
        } else {
            points.add(Optional.empty())
        }
        if (p.y - 1 >= 0) {
            points.add(Optional.of(new Point(p.x, p.y - 1)))
        } else {
            points.add(Optional.empty())
        }
        return points
    }
}
