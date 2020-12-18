package aoc.y2020.day18

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day18Spec extends Specification {
    static def t1 = "((2 + 2 * 6 + 3 + 8) * 5 + 7) * (9 + (7 + 2)) + ((9 * 9 * 6) + 7 * 6 * 5) * ((5 * 8 * 6 * 7 * 6 + 5) + 8 + 5 * 9 + (9 * 5) + (4 + 7 + 9 + 4)) + (5 * 6 + 7 + 8 * 7 * 3) * 2"

    def "part1 warmup tests"() {
        expect:
        Day18.part1(input) == output

        where:
        input                                             | output
        "1 + 2 * 3 + 4 * 5 + 6"                           | 71
        "1 + (2 * 3) + (4 * (5 + 6))"                     | 51
        "2 * 3 + (4 * 5)"                                 | 26
        "5 + (8 * 3 + 9 + 3 * 4 * 3)"                     | 437
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"       | 12240
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2" | 13632
        """1 + 2 * 3 + 4 * 5 + 6
        "1 + (2 * 3) + (4 * (5 + 6))"""                   | 122
    }

    def "part1"() {
        expect:
        Day18.part1(InputReader.read("y2020/day18")) == 1408133923393
    }

    def "part2 warmup tests"() {
        expect:
        Day18.part2(input) == output

        where:
        input                                             | output
        "1 + 2 * 3 + 4 * 5 + 6"                           | 231
        "1 + (2 * 3) + (4 * (5 + 6))"                     | 51
        "2 * 3 + (4 * 5)"                                 | 46
        "5 + (8 * 3 + 9 + 3 * 4 * 3)"                     | 1445
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"       | 669060
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2" | 23340
        """1 + 2 * 3 + 4 * 5 + 6
        "1 + (2 * 3) + (4 * (5 + 6))"""                   | 282
        t1                                                | 12083328L * 2889318L
        "1 + 2 * 3 + 4 * 5 + 6"                           | 231
        "1 + (2 * 3) + 4 * 5 + 6"                         | 121
        "(4 + 7 * (2 * 7) + 7 * 6 * (5 * 9)) * 2"         | 124740
    }

    def "part2"() {
        expect:
        Day18.part2(InputReader.read("y2020/day18")) == 314455761823725
    }
}
