package aoc.y2020.day17

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day17Spec extends Specification {
    static def t1 = """.#.
..#
###"""

    def "part1 warmup tests"() {
        expect:
        Day17.part1(input) == output

        where:
        input  | output
        t1     | 112
    }

    def "part1"() {
        expect:
        Day17.part1(InputReader.read("y2020/day17")) == 237
    }

    def "part2 warmup tests"() {
        expect:
        Day17.part2(input) == output

        where:
        input  | output
        t1     | 848
    }

    def "part2"() {
        expect:
        Day17.part2(InputReader.read("y2020/day17")) == 2448
    }
}
