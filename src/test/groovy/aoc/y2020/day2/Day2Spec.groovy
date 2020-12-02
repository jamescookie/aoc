package aoc.y2020.day2

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day2Spec extends Specification {

    def "part1 warmup tests"() {
        expect:
        Day2.part1(input) == output

        where:
        input                                          | output
        "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc" | 2
    }

    def "part1"() {
        expect:
        Day2.part1(InputReader.read("y2020/day2")) == 383
    }

    def "part2 warmup tests"() {
        expect:
        Day2.part2(input) == output

        where:
        input                                          | output
        "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc" | 1
    }

    def "part2"() {
        expect:
        Day2.part2(InputReader.read("y2020/day2")) == 272
    }
}
