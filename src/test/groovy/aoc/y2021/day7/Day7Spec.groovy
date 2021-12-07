package aoc.y2021.day7

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day7Spec extends Specification {
    static def t1 = """16,1,2,0,4,2,7,1,2,14"""

    def "part1 warmup tests"() {
        expect:
        Day7.part1(input) == output

        where:
        input  | output
        t1     | 37
    }

    def "part1"() {
        expect:
        Day7.part1(InputReader.read("y2021/day7")) == 349357
    }

    def "part2 warmup tests"() {
        expect:
        Day7.part2(input) == output

        where:
        input  | output
        t1     | 168
    }

    def "part2"() {
        expect:
        Day7.part2(InputReader.read("y2021/day7")) == 96708205
    }
}
