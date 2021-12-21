package aoc.y2021.day21

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day21Spec extends Specification {
    static def t1 = """Player 1 starting position: 4
Player 2 starting position: 8
"""

    def "part1 warmup tests"() {
        expect:
        Day21.part1(input) == output

        where:
        input  | output
        t1     | 739785
    }

    def "part1"() {
        expect:
        Day21.part1(InputReader.read("y2021/day21")) == 597600
    }

    def "part2 warmup tests"() {
        expect:
        Day21.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day21.part2(InputReader.read("y2021/day21")) == 0
    }
}
