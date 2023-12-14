package aoc.y2023.day14

import aoc.Point
import aoc.Utils

class Day14 {
    public static char ASH = ('.' as char)
    public static char ROCK = ('#' as char)
    public static char BALL = ('O' as char)
    public static final Point NORTH = new Point(1, 0)
    public static final Point WEST = new Point(0, 1)
    public static final Point SOUTH = new Point(-1, 0)
    public static final Point EAST = new Point(0, -1)

    static part1(String s) {
        def input = new Input(s)
        input.tilt(NORTH)
        return input.totalLoad()
    }

    static part2(String s) {
        def input = new Input(s)
        List<Long> data = []
        Map<Integer, List<Long>> result = null
        def cycles = 1000000000
        for (i in 0..<cycles) {
            input.tilt(NORTH)
            input.tilt(WEST)
            input.tilt(SOUTH)
            input.tilt(EAST)
            data.add(input.totalLoad())
            if (result = Utils.checkPattern(data, 10)) {
                break
            }
        }
        if (result) {
            def set = result.entrySet()[0]
            return set.value[((cycles - 1 - set.key) % set.value.size())]
        } else {
            return input.totalLoad()
        }
    }

    static class Input {
        List<List<Character>> rows
        int totalRows
        int totalCols

        Input(String s) {
            rows = s.tokenize('\n').collect { it.toCharArray() }
            totalRows = rows.size() - 1
            totalCols = rows[0].size() - 1
        }

        long totalLoad() {
            long result = 0
            for (x in 0..totalRows) {
                for (y in 0..totalCols) {
                    if (rows[x][y] == BALL) {
                        result += 1 * (rows.size() - x)
                    }
                }
            }
            return result
        }

        void tilt(Point direction) {
            int startX = direction.x >= 0 ? 0 : totalRows
            int endX = direction.x >= 0 ? totalRows : 0
            int startY = direction.y >= 0 ? 0 : totalCols
            int endY = direction.y >= 0 ? totalCols : 0
            for (x in startX..endX) {
                for (y in startY..endY) {
                    if (rows[x][y] == ASH) {
                        moveBall(new Point(x, y), direction)
                    }
                }
            }
        }

        void moveBall(Point start, Point translation) {
            Point next = new Point(start)
            while (true) {
                next.translate(translation)
                if (next.x > totalRows || next.y > totalCols || next.x < 0 || next.y < 0) {
                    return
                }
                if (rows[next.x][next.y] == BALL) {
                    rows[next.x][next.y] = ASH
                    rows[start.x][start.y] = BALL
                    return
                } else if (rows[next.x][next.y] == ROCK) {
                    return
                }
            }
        }
    }
}
