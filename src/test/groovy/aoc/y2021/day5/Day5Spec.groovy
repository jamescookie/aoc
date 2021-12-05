package aoc.y2021.day5

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day5Spec extends Specification {
    static def t1 = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2"""

    def "part1 warmup tests"() {
        expect:
        Day5.part1(input) == output

        where:
        input  | output
        t1     | 5
    }

    def "part1"() {
        expect:
        Day5.part1(InputReader.read("y2021/day5")) == 7414
    }

    def "part2 warmup tests"() {
        expect:
        Day5.part2(input) == output

        where:
        input  | output
        t1     | 12
    }

    def "part2"() {
        expect:
        Day5.part2(InputReader.read("y2021/day5")) == 19676
    }
}
