package aoc.y2022.day8

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day8Spec extends Specification {
    static def t1 = """30373
25512
65332
33549
35390"""

    def "part1 warmup tests"() {
        expect:
        Day8.part1(input) == output

        where:
        input  | output
        t1     | 21
    }

    def "part1"() {
        expect:
        Day8.part1(InputReader.read("y2022/day8")) == 1688
    }

    def "part2 warmup tests"() {
        expect:
        Day8.part2(input) == output

        where:
        input  | output
        t1     | 8
    }

    def "part2"() {
        expect:
        Day8.part2(InputReader.read("y2022/day8")) == 410400
    }
}
