package aoc.y2022.day4

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day4Spec extends Specification {
    static def t1 = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8"""

    def "part1 warmup tests"() {
        expect:
        Day4.part1(input) == output

        where:
        input  | output
        t1     | 2
    }

    def "part1"() {
        expect:
        Day4.part1(InputReader.read("y2022/day4")) == 571
    }

    def "part2 warmup tests"() {
        expect:
        Day4.part2(input) == output

        where:
        input  | output
        t1     | 4
    }

    def "part2"() {
        expect:
        Day4.part2(InputReader.read("y2022/day4")) == 917
    }
}
