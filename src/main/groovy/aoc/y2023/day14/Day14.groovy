package aoc.y2023.day14

import aoc.Point

class Day14 {
    public static char ASH = ('.' as char)
    public static char ROCK = ('#' as char)
    public static char BALL = ('O' as char)

    static part1(String s) {
        def input = new Input(s)
        input.tilt()
        long result = 0
        for (x in 0..<input.rows.size()) {
            for (y in 0..<input.rows[x].size()) {
                if (input.rows[x][y] == BALL) {
                    result += 1 * (input.rows.size() - x)
                }
            }
        }

        return result
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

        void tilt() {
            for (x in 0..<rows.size()) {
                for (y in 0..<rows[x].size()) {
                    if (rows[x][y] == ASH) {
                        moveBall(new Point(x, y), new Point(1, 0))
                    }
                }
            }
        }

        void moveBall(Point start, Point translation) {
            Point next = new Point(start)
            while (true) {
                next.translate(translation)
                if (next.x >= rows.size() || next.y >= rows[0].size()) {
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
