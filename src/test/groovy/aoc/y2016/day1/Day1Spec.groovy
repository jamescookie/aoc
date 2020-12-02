package aoc.y2016.day1

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day1Spec extends Specification {

    def "part1 warmup tests"() {
        expect:
        Day1.part1(input) == output

        where:
        input            | output
        "R2, L3"         | 5
        "R2, R2, R2"     | 2
        "R5, L5, R5, R3" | 12
    }

    def "part1"() {
        expect:
        Day1.part1(InputReader.read("y2016/day1")) == 209
    }

    def "part2 warmup tests"() {
        expect:
        Day1.part2(input) == output

        where:
        input            | output
        "R8, R4, R4, R8" | 4
    }

    def "part2"() {
        expect:
        Day1.part2(InputReader.read("y2016/day1")) == 136
    }
}
