package aoc.y2021.day15

import aoc.Point

import java.util.concurrent.atomic.AtomicLong

class Day15 {
    static part1(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> row.collect { it as int } }

        AtomicLong bestSoFar = new AtomicLong((input[0..-2]*.getAt(0).sum() + input[-1].sum()) as long)
        findPath(new Point(0,0), [], -input[0][0], input, bestSoFar)
        return bestSoFar.getPlain()
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static void findPath(Point current, List<Point> pointsSoFar, long totalSoFar, Integer[][] board, AtomicLong bestSoFar) {
        if (pointsSoFar.contains(current)) {
            return
        }
        pointsSoFar << current
        totalSoFar += board[current.x][current.y]
        if (totalSoFar > bestSoFar) {
            return
        }
        if (current.x == (board.size() - 1) && current.y == (board[current.x].size() - 1)) {
            bestSoFar.set(totalSoFar)
        } else {
             Point.neighbours(board, current).collect{findPath(it, new ArrayList<>(pointsSoFar), totalSoFar, board, bestSoFar)}.min()
        }
    }
}
