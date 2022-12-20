package aoc.y2022.day17

import aoc.Point

class Day17 {

    static List<List<Point>> shapes = [
            [new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)],
            [new Point(0, 1), new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 1)],
            [new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1), new Point(2, 2)],
            [new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)],
            [new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)]
    ]

    static part1(String input, int count) {
        int shapePointer = 0
        int instructionPointer = 0
        HashSet<Point> cave = new HashSet<>()

        for (i in 0..<count) {
            def shape = shapes[shapePointer].collect { it.clone() as Point }
            int highestPoint = (cave*.y.max() ?: 0) as int
            shape*.translate(2, highestPoint + 4)
            boolean falling = true
            while (falling) {
                Point translate
                if (instructionPointer >= input.size()) {
                    instructionPointer = 0
                }
                if (input[instructionPointer++] == '>') {
                    translate = new Point(1, 0)
                    if (shape*.x.max() < 6) {
                        shape*.translate(translate)
                    }
                } else {
                    translate = new Point(-1, 0)
                    if (shape*.x.min() > 0) {
                        shape*.translate(translate)
                    }
                }
                if (hasCollided(shape, cave)) {
                    shape*.translateUndo(translate)
                }
                translate = new Point(0, -1)
                shape*.translate(translate)
                if (hasCollided(shape, cave) || shape*.y.min() < 1) {
                    falling = false
                    shape*.translateUndo(translate)
                    cave.addAll(shape)
                }
            }

            shapePointer++
            if (shapePointer >= shapes.size()) {
                shapePointer = 0
            }
//            Point.print(cave, false)
        }
        return cave*.y.max()
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static boolean hasCollided(List<Point> points1, Set<Point> points2) {
        points1.intersect(points2).size() > 0
    }
}
