package aoc.y2021.day11

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day11Spec extends Specification {
    static def t0 = """11111
19991
19191
19991
11111"""
    static def t1 = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526"""

    def "part1 warmup tests"() {
        expect:
        Day11.part1(input, steps) == output

        where:
        input | steps | output
        t0    | 1     | 9
        t1    | 1     | 0
        t1    | 2     | 35
        t1    | 100   | 1656
    }

    def "part1"() {
        expect:
        Day11.part1(InputReader.read("y2021/day11"), 100) == 0
    }

    def "part2 warmup tests"() {
        expect:
        Day11.part2(input) == output

        where:
        input | output
        t1    | 195
    }

    def "part2"() {
        expect:
        Day11.part2(InputReader.read("y2021/day11")) == 298
    }
}
