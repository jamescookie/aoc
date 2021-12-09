package aoc.y2021.day9

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day9Spec extends Specification {
    static def t1 = """2199943210
3987894921
9856789892
8767896789
9899965678"""

    def "part1 warmup tests"() {
        expect:
        Day9.part1(input) == output

        where:
        input  | output
        t1     | 15
    }

    def "part1"() {
        expect:
        Day9.part1(InputReader.read("y2021/day9")) == 506
    }

    def "part2 warmup tests"() {
        expect:
        Day9.part2(input) == output

        where:
        input  | output
        t1     | 1134
    }

    def "part2"() {
        expect:
        Day9.part2(InputReader.read("y2021/day9")) == 931200
    }
}
