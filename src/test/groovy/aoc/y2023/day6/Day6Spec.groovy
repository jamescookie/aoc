package aoc.y2023.day6

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day6Spec extends Specification {
    static def t1 = """Time:      7  15   30
Distance:  9  40  200"""

    def "part1 warmup tests"() {
        expect:
        Day6.part1(input) == output

        where:
        input  | output
        t1     | 288
    }

    def "part1"() {
        expect:
        Day6.part1(InputReader.read("y2023/day6")) == 1195150
    }

    def "part2 warmup tests"() {
        expect:
        Day6.part2(input) == output

        where:
        input  | output
        t1     | 71503
    }

    def "part2"() {
        expect:
        Day6.part2(InputReader.read("y2023/day6")) == 0
    }
}
