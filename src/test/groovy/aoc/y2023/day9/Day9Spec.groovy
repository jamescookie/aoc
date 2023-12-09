package aoc.y2023.day9

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day9Spec extends Specification {
    static def t1 = """0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45"""

    def "part1 warmup tests"() {
        expect:
        Day9.part1(input) == output

        where:
        input  | output
        t1     | 114
    }

    def "part1"() {
        expect:
        Day9.part1(InputReader.read("y2023/day9")) == 1953784198
    }

    def "part2 warmup tests"() {
        expect:
        Day9.part2(input) == output

        where:
        input  | output
        t1     | 2
    }

    def "part2"() {
        expect:
        Day9.part2(InputReader.read("y2023/day9")) == 957
    }
}
