package aoc.y2019.day3

import java.awt.Point

class FindPoints {
    static def findPoints(input) {
        def start = new Point(0,0)
        def points = [];
        input.split(',').each {
            def direction = it[0]
            int amount = Integer.parseInt(it[1..-1])
            def doWhat
            switch (direction) {
                case 'R':
                    doWhat = {x->x.translate(1, 0)}
                    break
                case 'U':
                    doWhat = {x->x.translate(0, 1)}
                    break
                case 'L':
                    doWhat = {x->x.translate(-1, 0)}
                    break
                case 'D':
                    doWhat = {x->x.translate(0, -1)}
                    break
            }
            for (int i = 0; i < amount; i++) {
                doWhat(start)
                points.add(start.location)
            }
        }
        return points
    }

    static def part1(input1, input2) {
        def points1 = findPoints(input1)
        def points2 = findPoints(input2)
        points1.intersect(points2).collect{Math.abs(0 - it.x) + Math.abs(0 - it.y)}.min()
    }

    static def part2(input1, input2) {
        def points1 = findPoints(input1)
        def points2 = findPoints(input2)
        points1.intersect(points2).collect{points1.indexOf(it) + 1 + points2.indexOf(it) + 1}.min()
    }
}
