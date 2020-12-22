package aoc.y2020.day22

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day22Spec extends Specification {
    static def t1 = """Player 1:
9
2
6
3
1

Player 2:
5
8
4
7
10
"""
    static def t2 = """Player 1:
43
19

Player 2:
2
29
14
"""

    def "part1 warmup tests"() {
        expect:
        Day22.part1(input) == output

        where:
        input | output
        t1    | 306
    }

    def "part1"() {
        expect:
        Day22.part1(InputReader.read("y2020/day22")) == 33010
    }

    def "part2 warmup tests"() {
        expect:
        Day22.part2(input) == output

        where:
        input | output
        t1    | 291
        t2    | 105
    }

    def "part2"() {
        expect:
        Day22.part2(InputReader.read("y2020/day22")) == 32769
    }
}
