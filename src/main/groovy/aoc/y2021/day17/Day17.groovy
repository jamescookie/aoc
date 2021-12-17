package aoc.y2021.day17

import aoc.Point

class Day17 {
    static part1(String inputString) {
        String[] input = (inputString.trim() - "target area: ").split(', ')
        def (minX, maxX) = (input[0] =~ /x=([-]?\d+)\.\.([-]?\d+)/).findAll().first().findAll { it != input[0] }.collect { Integer.parseInt(it) }
        def (minY, maxY) = (input[1] =~ /y=([-]?\d+)\.\.([-]?\d+)/).findAll().first().findAll { it != input[1] }.collect { Integer.parseInt(it) }

        List<Point> targetArea = []
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                targetArea << new Point(x, y)
            }
        }

        int answer = 0
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                Point probe = new Point(0, 0)
                List<Point> trajectory = move([probe], x, y, targetArea, maxX, minY)
                if (trajectory) {
                    answer = [answer, trajectory.collect{it.y}.max()].max()
                }
            }
        }

        return answer
    }

    static List<Point> move(List<Point> soFar, int x, int y, List<Point> targetArea, int maxX, int minY) {
        Point newPoint = new Point(soFar[-1])
        newPoint.translate(x, y)
        if (newPoint.x > maxX || newPoint.y < minY) {
            return []
        } else {
            soFar << newPoint
            if (targetArea.contains(newPoint)) {
                return soFar
            } else {
                move(soFar, x == 0 ? 0 : (x - 1), y - 1, targetArea, maxX, minY)
            }
        }
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }
}
