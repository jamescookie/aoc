package aoc.y2023.day14

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day14Spec extends Specification {
    static def t1 = """O....#....
O.OO#....#
.....##...
OO.#O....O
.O.....O#.
O.#..O.#.#
..O..#O..O
.......O..
#....###..
#OO..#...."""

    def "part1 warmup tests"() {
        expect:
        Day14.part1(input) == output

        where:
        input  | output
        t1     | 136
    }

    def "part1"() {
        expect:
        Day14.part1(InputReader.read("y2023/day14")) == 109424
    }

    def "part2 warmup tests"() {
        expect:
        Day14.part2(input) == output

        where:
        input  | output
        t1     | 64
    }

    def "part2"() {
        expect:
        Day14.part2(InputReader.read("y2023/day14")) == 102509
    }
}
