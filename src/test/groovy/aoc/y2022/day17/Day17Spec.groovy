package aoc.y2022.day17

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day17Spec extends Specification {
    static def t1 = """>>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"""

    def "part1 warmup tests"() {
        expect:
        Day17.part1(input, count) == output

        where:
        input | count | output
        t1    | 1     | 1
        t1    | 2     | 4
        t1    | 10    | 17
        t1    | 2022  | 3068
    }

    def "part1"() {
        expect:
        Day17.part1(InputReader.read("y2022/day17"), 2022) == 0
    }

    def "part2 warmup tests"() {
        expect:
        Day17.part2(input) == output

        where:
        input | output
        t1    | 0
    }

    def "part2"() {
        expect:
        Day17.part2(InputReader.read("y2022/day17")) == 0
    }
}
