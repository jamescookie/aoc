package aoc.y2021.day11

import aoc.Point

class Day11 {
    static part1(String inputString, int steps) {
        Integer[][] input = inputString.tokenize().collect { row -> row.collect { it as int } }
        int answer = 0
        for (step in 0..<steps) {
            for (x in 0..<input.size()) {
                for (y in 0..<input[0].size()) {
                    input[x][y] += 1
                }
            }
            def flashed = []
            for (x in 0..<input.size()) {
                for (y in 0..<input[0].size()) {
                    if (input[x][y] > 9) {
                        flash(input, x, y, flashed)
                    }
                }
            }
            for (x in 0..<input.size()) {
                for (y in 0..<input[0].size()) {
                    if (input[x][y] > 9) {
                        input[x][y] = 0
                        answer++
                    }
                }
            }
        }
        return answer
    }

    static part2(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> row.collect { it as int } }
        int step = 0
        while (true) {
            int answer = 0
            for (x in 0..<input.size()) {
                for (y in 0..<input[0].size()) {
                    input[x][y] += 1
                }
            }
            def flashed = []
            for (x in 0..<input.size()) {
                for (y in 0..<input[0].size()) {
                    if (input[x][y] > 9) {
                        flash(input, x, y, flashed)
                    }
                }
            }
            for (x in 0..<input.size()) {
                for (y in 0..<input[0].size()) {
                    if (input[x][y] > 9) {
                        input[x][y] = 0
                        answer++
                    }
                }
            }
            step++
            if (answer == 100) {
                return step
            }
        }
    }


    protected static List<Point> flash(Integer[][] input, int x, int y, List<Point> flashed) {
        def point = new Point(x, y)
        if (flashed.contains(point)) return flashed
        flashed << point
        def neighbours = Point.neighboursWithDiagonals(input, point)
        neighbours.each { p ->
            input[p.x][p.y] += 1
            if (input[p.x][p.y] > 9) {
                flash(input, p.x, p.y, flashed)
            }
        }
        return flashed
    }
}
