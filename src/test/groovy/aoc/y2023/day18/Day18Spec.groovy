package aoc.y2023.day18

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day18Spec extends Specification {
    static def t1 = """R 6 (#70c710)
D 5 (#0dc571)
L 2 (#5713f0)
D 2 (#d2c081)
R 2 (#59c680)
D 2 (#411b91)
L 5 (#8ceee2)
U 2 (#caa173)
L 1 (#1b58a2)
U 2 (#caa171)
R 2 (#7807d2)
U 3 (#a77fa3)
L 2 (#015232)
U 2 (#7a21e3)"""

    def "part1 warmup tests"() {
        expect:
        Day18.part1(input) == output

        where:
        input  | output
        t1     | 62
    }

    def "part1"() {
        expect:
        Day18.part1(InputReader.read("y2023/day18")) == 74074 //too high
    }

    def "part2 warmup tests"() {
        expect:
        Day18.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day18.part2(InputReader.read("y2023/day18")) == 0
    }
}
