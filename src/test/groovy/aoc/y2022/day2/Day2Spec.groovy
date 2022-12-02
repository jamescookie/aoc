package aoc.y2022.day2

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day2Spec extends Specification {
    static def t1 = """A Y
B X
C Z"""

    def "part1 warmup tests"() {
        expect:
        Day2.part1(input) == output

        where:
        input  | output
        t1     | 15
    }

    def "part1"() {
        expect:
        Day2.part1(InputReader.read("y2022/day2")) == 13446
    }

    def "part2 warmup tests"() {
        expect:
        Day2.part2(input) == output

        where:
        input  | output
        t1     | 12
    }

    def "part2"() {
        expect:
        Day2.part2(InputReader.read("y2022/day2")) == 13509
    }
}
