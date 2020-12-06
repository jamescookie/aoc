package aoc.y2020.day6

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day6Spec extends Specification {
    static String t1 = """abcx
abcy
abcz"""
    static String t2 = """abc

a
b
c

ab
ac

a
a
a
a

b"""

    def "part1 warmup tests"() {
        expect:
        Day6.part1(input) == output

        where:
        input  | output
        t1| 6
        t2| 11
    }

    def "part1"() {
        expect:
        Day6.part1(InputReader.read("y2020/day6")) == 6903
    }

    def "part2 warmup tests"() {
        expect:
        Day6.part2(input) == output

        where:
        input  | output
        t1| 3
        t2| 6
    }

    def "part2"() {
        expect:
        Day6.part2(InputReader.read("y2020/day6")) == 3493
    }
}
