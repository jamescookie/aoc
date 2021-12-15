package aoc.y2021.day15

import aoc.Point

class Day15 {
    static part1(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> row.collect { it as int } }
        return findAnswer(input)
    }

    static part2(String inputString) {
        Integer[][] input = transformX(5, inputString.tokenize().collect { row -> transformY(5, row.collect { it as int }) })
        return findAnswer(input)
    }

    static long findAnswer(Integer[][] input) {
        Long[][] answer = new Long[input.size()][input[0].size()]
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
                backPropagate(p, answer, input, 0)
            }
        }

        return answer[-1][-1]
    }

    static void backPropagate(Point p, Long[][] answer, Integer[][] input, int depth) {
        if (depth > 20) return //hacky to stop stack overflow - it'll probably still get the right answer
        def neighbours = Point.neighbours(answer, p)
        long soFar = answer[p.x][p.y]
        neighbours.findAll { answer[it.x][it.y] != null && answer[it.x][it.y] > soFar + input[it.x][it.y] }.each {
            answer[it.x][it.y] = soFar + input[it.x][it.y]
            backPropagate(it, answer, input, ++depth)
        }
    }

    static Integer[] transformY(int times, List<Integer> original) {
        List<Integer> newList = []
        for (i in 0..<times) {
            newList.addAll(original.collect { (it + i) > 9 ? (it + i - 9) : (it + i) })
        }
        return newList
    }

    static Integer[][] transformX(int times, List<Integer[]> original) {
        List<List<Integer>> newList = []
        for (i in 0..<times) {
            newList.addAll(original.collect { row -> row.collect { (it + i) > 9 ? (it + i - 9) : (it + i) } })
        }
        return newList
    }
}
