package aoc.y2021.day13

import aoc.Point

class Day13 {
    static part1(String inputString) {
        String[] input = inputString.split('\n\n')
        Set<Point> points = input[0].tokenize().collect { new Point(it) }
        List instructions = (input[1].tokenize() - ['fold', 'along']).collect { it.split('=') }
        points = fold(instructions[0], points)
        return points.size()
    }

    static part2(String inputString) {
        String[] input = inputString.split('\n\n')
        Set<Point> points = input[0].tokenize().collect { new Point(it) }
        List instructions = (input[1].tokenize() - ['fold', 'along']).collect { it.split('=') }
        for (i in 0..<instructions.size()) {
            points = fold(instructions[i], points)
        }

        for (int j = 0; j < (points*.y).max() + 1; j++) {
            for (int i = 0; i < (points*.x).max() + 1; i++) {
                if (points.contains(new Point(i, j))) {
                    print('#')
                } else {
                    print(' ')
                }
            }
            println()
        }
        return "O"
    }

    protected static Set<Point> fold(String[] instruction, Set<Point> points) {
        boolean horizontal = instruction[0] == 'y'
        int line = instruction[1] as int
        Set<Point> newPoints = []
        for (i in 0..<points.size()) {
            Point p = points[i]
            if (horizontal) {
                if (p.y > line) {
                    newPoints << new Point(p.x, line - (p.y - line))
                } else {
                    newPoints << p
                }
            } else {
                if (p.x > line) {
                    newPoints << new Point(line - (p.x - line), p.y)
                } else {
                    newPoints << p
                }
            }
        }
        newPoints
    }
}
