package aoc.y2023.day10

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day10Spec extends Specification {
    static def t1 = """-L|F7
7S-7|
L|7||
-L-J|
L|-JF"""
    static def t2 = """7-F7-
.FJ|7
SJLL7
|F--J
LJ.LJ"""

    def "part1 warmup tests"() {
        expect:
        Day10.part1(input) == output

        where:
        input  | output
        t1     | 4
        t2     | 8
    }

    def "part1"() {
        expect:
        Day10.part1(InputReader.read("y2023/day10")) == 6856 // too high
    }

    def "part2 warmup tests"() {
        expect:
        Day10.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day10.part2(InputReader.read("y2023/day10")) == 0
    }
}
