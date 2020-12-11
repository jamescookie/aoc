package aoc.y2020.day11

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day11Spec extends Specification {
    static def t1 = """L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL"""

    def "part1 warmup tests"() {
        expect:
        Day11.part1(input) == output

        where:
        input | output
        t1    | 37
    }

    def "part1"() {
        expect:
        Day11.part1(InputReader.read("y2020/day11")) == 2126
    }

    def "part2 warmup tests"() {
        expect:
        Day11.part2(input) == output

        where:
        input | output
        t1    | 26
    }

    def "part2"() {
        expect:
        Day11.part2(InputReader.read("y2020/day11")) == 1914
    }
}
