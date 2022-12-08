package aoc.y2022.day8

import aoc.Point

class Day8 {
    static part1(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row->row.collect {Integer.parseInt(it)} }
        int visible = 0
        for (x in 0..<input.length) {
            def row = input[x]
            for (y in 0..<row.length) {
                boolean isVisible = false

                def point = new Point(x, y)
                def value = input[x][y]
                def neighbours = Point.neighbours(input, point)
                if (neighbours.size() < 4) {
                    isVisible = true
                }
                if (!isVisible) {
                    isVisible = isVisible || values(input, Point.pointsBetween(point, new Point(x, 0)) + new Point(x, 0)).findAll{it >= value}.size() == 0
                    isVisible = isVisible || values(input, Point.pointsBetween(point, new Point(0, y)) + new Point(0, y)).findAll{it >= value}.size() == 0
                    isVisible = isVisible || values(input, Point.pointsBetween(point, new Point(x, input.length))).findAll{it >= value}.size() == 0
                    isVisible = isVisible || values(input, Point.pointsBetween(point, new Point(row.length, y))).findAll{it >= value}.size() == 0
                }
                if (isVisible) {
                    visible++
                }
            }
        }
        return visible
    }

    static part2(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row->row.collect {Integer.parseInt(it)} }
        int bestScore = 0
        for (x in 0..<input.length) {
            def row = input[x]
            for (y in 0..<row.length) {
                boolean onEdge = false

                def point = new Point(x, y)
                def value = input[x][y]
                def score = 0
                def neighbours = Point.neighbours(input, point)
                if (neighbours.size() < 4) {
                    onEdge = true
                }
                if (!onEdge) {
                    score =
                        findScore(values(input, Point.pointsBetween(point, new Point(x, 0)) + new Point(x, 0)), value) *
                        findScore(values(input, Point.pointsBetween(point, new Point(0, y)) + new Point(0, y)), value) *
                        findScore(values(input, Point.pointsBetween(point, new Point(x, input.length))), value) *
                        findScore(values(input, Point.pointsBetween(point, new Point(row.length, y))), value)
                    if (score > bestScore)
                        bestScore = score
                }
            }
        }
        return bestScore
    }

    static List<Integer> values(Integer[][] input, Collection<Point> points) {
        return points.collect {input[it.x][it.y]}
    }

    static int findScore(Collection<Integer> list, int value) {
        int score = 0
        for (i in 0..<list.size()) {
            score++
            if (list[i] >= value) break
        }
        return score
    }
}
