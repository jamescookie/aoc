package aoc.y2022.day9

import aoc.Point

class Day9 {
    static Map<String, Point> TRANSLATE = ['U': new Point(1, 0), 'D': new Point(-1, 0), 'L': new Point(0, -1), 'R': new Point(0, 1)]

    static part1(String inputString) {
        List input = inputString.split('\n').collect { x -> [TRANSLATE[x.split(' ')[0]], x.split(' ')[1] as int] }
        def head = new Point(0, 0)
        def tail = new Point(0, 0)
        Set<Point> visited = []
        for (i in 0..<input.size()) {
            Point direction = input[i][0]
            int amount = input[i][1]
            for (j in 0..<amount) {
                head.translate(direction.x, direction.y)
                moveTailToHead(tail, head)
                visited << new Point(tail)
            }
        }

        return visited.size()
    }

    static part2(String inputString) {
        List input = inputString.split('\n').collect { x -> [TRANSLATE[x.split(' ')[0]], x.split(' ')[1] as int] }
        List<Point> snake = [new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)]
        Set<Point> visited = []
        for (i in 0..<input.size()) {
            Point direction = input[i][0]
            int amount = input[i][1]
            for (j in 0..<amount) {
                snake[0].translate(direction.x, direction.y)
                for (k in 1..<snake.size()) {
                    moveTailToHead(snake[k], snake[k - 1])
                }
//                printout(snake)
                visited << new Point(snake[-1])
            }
        }

        return visited.size()
    }

    static void moveTailToHead(Point tail, Point head) {
        def between = Point.pointsBetweenExcludingEnds(tail, head)
        if (between.size() == 0) return

        if (head.x == tail.x || head.y == tail.y) {
            tail.move(between[0].x, between[0].y)
        } else {
            if (Math.abs(head.x - tail.x) == 1) {
                tail.move(head.x, tail.y)
            } else if (Math.abs(head.y - tail.y) == 1) {
                tail.move(tail.x, head.y)
            } else {
                tail.move(between[0].x, between[0].y)
            }
            moveTailToHead(tail, head)
        }
    }

    static void printout(ArrayList<Point> points) {
        for (x in 10..<-1) {
            def row = []
            for (y in 0..<10) {
                if (points.contains(new Point(x, y))) {
                    row << points.indexOf(new Point(x, y))
                } else {
                    row << '.'
                }
            }
            println(row.join())
        }
        println()
    }
}
