package aoc.y2020.day1

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day1Spec extends Specification {

    def "part1 warmup tests"() {
        expect:
        Day1.part1(input, 2020) == output

        where:
        input                              | output
        "1010\n1010"                       | 1020100
        "1721\n979\n366\n299\n675\n1456\n" | 514579
    }

    def "part1"() {
        expect:
        Day1.part1(InputReader.read("y2020/day1"), 2020) == 224436
    }

    def "part2 warmup tests"() {
        expect:
        Day1.part2(input, 2020) == output

        where:
        input                              | output
        "1010\n1009\n1"                    | 1019090
        "1721\n979\n366\n299\n675\n1456\n" | 241861950
    }

    def "part2"() {
        expect:
        Day1.part2(InputReader.read("y2020/day1"), 2020) == 303394260
    }
}
