package aoc.y2022.day15

import aoc.Point

class Day15 {
    static part1(String inputString, int y) {
        def input = inputString.split(System.lineSeparator()).collect {line->
            line -= 'Sensor at x=';
            line -= ' closest beacon is at x='
            line = line.replaceAll(' y=', '')
            line.split(':').collect{new Point(it)}
        }

        HashSet<Point> none = new HashSet<>()

        for (i in 0..<input.size()) {
            Point sensor = input[i][0]
            Point beacon = input[i][1]
            int dx = Math.abs(sensor.x - beacon.x);
            int dy = Math.abs(sensor.y - beacon.y);
            Set<Point> inside = sensor.findAllInside(dx + dy)
            none.addAll(inside - beacon)
//            Point.print(input.collectEntries{[it[0], 'S' as Character]} + input.collectEntries{[it[1], 'B' as Character]} + x.collectEntries{[it, '#' as Character]})
        }

        return none.findAll {it.y == y}.size()
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }
}
