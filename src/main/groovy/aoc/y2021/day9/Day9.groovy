package aoc.y2021.day9

import aoc.Point

class Day9 {
    static part1(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> (row as List).collect { it as int } }
        int answer = 0

        for (x in 0..<input.size()) {
            for (y in 0..<input[x].size()) {
                int current = input[x][y]
                if (Point.neighbours(input, new Point(x, y)).every { p -> current < input[p.x][p.y] }) {
                    answer += current + 1
                }
            }
        }
        return answer
    }

    static part2(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> (row as List).collect { it as int } }
        def answers = []

        for (x in 0..<input.size()) {
            for (y in 0..<input[x].size()) {
                if (input[x][y] != 9) {
                    answers << findBasin(input, x, y)
                }
            }
        }

        return answers.sort()[-3..-1].inject { a, b -> a * b }
    }

    static int findBasin(Integer[][] input, int x, int y) {
        int answer = 1
        input[x][y] = 9
        Point.neighbours(input, new Point(x, y)).each { p ->
            if (input[p.x][p.y] != 9) answer += findBasin(input, p.x, p.y)
        }
        return answer
    }
}
