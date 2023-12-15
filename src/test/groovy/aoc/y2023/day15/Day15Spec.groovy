package aoc.y2023.day15

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day15Spec extends Specification {
    static def t0 = """HASH"""
    static def t1 = """rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"""

    def "part1 warmup tests"() {
        expect:
        Day15.part1(input) == output

        where:
        input  | output
        t0     | 52
        t1     | 1320
    }

    def "part1"() {
        expect:
        Day15.part1(InputReader.read("y2023/day15")) == 502139
    }

    def "part2 warmup tests"() {
        expect:
        Day15.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day15.part2(InputReader.read("y2023/day15")) == 0
    }
}
