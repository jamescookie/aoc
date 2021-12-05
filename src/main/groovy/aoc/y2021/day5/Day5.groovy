package aoc.y2021.day5

import aoc.Line
import aoc.Point

class Day5 {
    static part1(String inputString) {
        String[] input = inputString.tokenize('\n')
        def lines = input.collect { createLine(it) }
        lines = lines.findAll { it.p1.x == it.p2.x || it.p1.y == it.p2.y }

        def x = lines.collect {l1 ->
            lines.collect {l2 ->
                if (l1 == l2 || !l1.intersectsLine(l2)) {
                    []
                } else {
                    l1.points.intersect(l2.points)
                }
            }
        }

        return x.flatten().toSet().size()
    }

    protected static Line createLine(String input) {
        def tokenize = input.tokenize(' -> ')
        new Line(new Point(tokenize[0]), new Point(tokenize[1]))
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { Integer.parseInt(it) }
        return null
    }


}
