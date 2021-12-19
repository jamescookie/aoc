package aoc.y2021.day19

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day19Spec extends Specification {
    static def t1 = """"""

    def "part1 warmup tests"() {
        expect:
        Day19.part1(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part1"() {
        expect:
        Day19.part1(InputReader.read("y2021/day19")) == 0
    }

    def "part2 warmup tests"() {
        expect:
        Day19.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day19.part2(InputReader.read("y2021/day19")) == 0
    }
}
