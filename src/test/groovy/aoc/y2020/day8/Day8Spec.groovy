package aoc.y2020.day8

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day8Spec extends Specification {
    static String i1 = """nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
"""

    def "part1 warmup tests"() {
        expect:
        Day8.part1(input) == output

        where:
        input | output
        i1    | 5
    }

    def "part1"() {
        expect:
        Day8.part1(InputReader.read("y2020/day8")) == 1723
    }

    def "part2 warmup tests"() {
        expect:
        Day8.part2(input) == output

        where:
        input | output
        i1    | 8
    }

    def "part2"() {
        expect:
        Day8.part2(InputReader.read("y2020/day8")) == 846
    }
}
