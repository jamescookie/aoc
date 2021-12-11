package aoc.y2021.day11

import aoc.Point

class Day11 {
    static part1(String inputString, int steps) {
        Integer[][] input = inputString.tokenize().collect { row -> row.collect { it as int } }
        int answer = 0
        for (step in 0..<steps) {
            answer += findFlashers(input).size()
        }
        return answer
    }

    static part2(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> row.collect { it as int } }
        int step = 0
        while (true) {
            step++
            if (findFlashers(input).size() == 100) {
                return step
            }
        }
    }

    protected static List findFlashers(Integer[][] input) {
        def flashed = []
        for (x in 0..<input.size()) {
            for (y in 0..<input[0].size()) {
                input[x][y] += 1
                checkFlash(input, x, y, flashed)
            }
        }
        flashed.each { p -> input[p.x][p.y] = 0 }
        flashed
    }

    protected static List<Point> checkFlash(Integer[][] input, int x, int y, List<Point> flashed) {
        def point = new Point(x, y)
        if (input[x][y] > 9) {
            if (flashed.contains(point)) return flashed
            flashed << point
            Point.neighboursWithDiagonals(input, point).each { p ->
                input[p.x][p.y] += 1
                checkFlash(input, p.x, p.y, flashed)
            }
        }
        return flashed
    }
}
