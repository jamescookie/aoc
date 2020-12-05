package aoc.y2020.day5

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day5Spec extends Specification {

    def "part1 warmup tests"() {
        expect:
        Day5.part1(input) == output

        where:
        input                                | output
        "BFFFBBFRRR"                         | 567
        "FFFBBBFRRR"                         | 119
        "BBFFBBFRLL"                         | 820
        "BFFFBBFRRR\nFFFBBBFRRR\nBBFFBBFRLL" | 820
    }

    def "part1"() {
        expect:
        Day5.part1(InputReader.read("y2020/day5")) == 888
    }

    def "part2"() {
        expect:
        Day5.part2(InputReader.read("y2020/day5")) == 522
    }
}
