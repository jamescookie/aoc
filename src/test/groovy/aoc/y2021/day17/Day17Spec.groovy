package aoc.y2021.day17

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day17Spec extends Specification {
    static def t1 = """target area: x=20..30, y=-10..-5"""

    def "part1 warmup tests"() {
        expect:
        Day17.part1(input) == output

        where:
        input  | output
        t1     | 45
    }

    def "part1"() {
        expect:
        Day17.part1(InputReader.read("y2021/day17")) == 4560
    }

    def "part2 warmup tests"() {
        expect:
        Day17.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day17.part2(InputReader.read("y2021/day17")) == 0
    }
}
