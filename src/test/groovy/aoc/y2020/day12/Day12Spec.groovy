package aoc.y2020.day12

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day12Spec extends Specification {
    static def t1 = """F10
N3
F7
R90
F11"""

    def "part1 warmup tests"() {
        expect:
        Day12.part1(input) == output

        where:
        input | output
        t1    | 25
    }

    def "part1"() {
        expect:
        Day12.part1(InputReader.read("y2020/day12")) == 923
    }

    def "part2 warmup tests"() {
        expect:
        Day12.part2(input) == output

        where:
        input | output
        t1    | 286
    }

    def "part2"() {
        expect:
        Day12.part2(InputReader.read("y2020/day12")) == 24769
    }
}
