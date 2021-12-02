package aoc.y2021.day2

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day2Spec extends Specification {
    static def t1 = """
forward 5
down 5
forward 8
up 3
down 8
forward 2
"""

    def "part1 warmup tests"() {
        expect:
        Day2.part1(input) == output

        where:
        input  | output
        t1     | 150
    }

    def "part1"() {
        expect:
        Day2.part1(InputReader.read("y2021/day2")) == 1561344
    }

    def "part2 warmup tests"() {
        expect:
        Day2.part2(input) == output

        where:
        input  | output
        t1     | 900
    }

    def "part2"() {
        expect:
        Day2.part2(InputReader.read("y2021/day2")) == 1848454425
    }
}
