package aoc.y2021.day15

import aoc.Point

class Day15 {
    static part1(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> row.collect { it as int } }
        Long[][] answer = inputString.tokenize().collect { row -> row.collect { null as Long } }


        for (x in 0..<input.size()) {
            def row = input[x]
            for (y in 0..<row.size()) {
                Point p = new Point(x, y)
                def neighbours = Point.neighbours(answer, p)
                long soFar = input[x][y]
                if (x == 0 && y == 0) {
                    soFar = 0
                } else {
                    soFar += neighbours.collect { answer[it.x][it.y] }.findAll { it != null }.min()
                }
                answer[x][y] = soFar
                backPropagate(p, answer, input)
            }
        }

        return answer[-1][-1]
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static void backPropagate(Point p, Long[][] answer, Integer[][] input) {
        def neighbours = Point.neighbours(answer, p)
        long soFar = answer[p.x][p.y]
        neighbours.findAll { answer[it.x][it.y] != null && answer[it.x][it.y] > soFar + input[it.x][it.y] }.each {
            answer[it.x][it.y] = soFar + input[it.x][it.y]
            backPropagate(it, answer, input)
        }
    }
}
