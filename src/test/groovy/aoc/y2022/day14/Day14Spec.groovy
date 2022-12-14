package aoc.y2022.day14

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day14Spec extends Specification {
    static def t1 = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9"""

    def "part1 warmup tests"() {
        expect:
        Day14.part1(input) == output

        where:
        input  | output
        t1     | 24
    }

    def "part1"() {
        expect:
        Day14.part1(InputReader.read("y2022/day14")) == 745
    }

    def "part2 warmup tests"() {
        expect:
        Day14.part2(input) == output

        where:
        input  | output
        t1     | 93
    }

    def "part2"() {
        expect:
        Day14.part2(InputReader.read("y2022/day14")) == 0
    }
}
