package aoc.y2021.day3

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day3Spec extends Specification {
    static def t1 = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010"""

    def "part1 warmup tests"() {
        expect:
        Day3.part1(input) == output

        where:
        input  | output
        t1     | 198
    }

    def "part1"() {
        expect:
        Day3.part1(InputReader.read("y2021/day3")) == 775304
    }

    def "part2 warmup tests"() {
        expect:
        Day3.part2(input) == output

        where:
        input  | output
        t1     | 230
    }

    def "part2"() {
        expect:
        Day3.part2(InputReader.read("y2021/day3")) == 1370737
    }
}
