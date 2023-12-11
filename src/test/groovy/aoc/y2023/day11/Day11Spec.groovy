package aoc.y2023.day11

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day11Spec extends Specification {
    static def t1 = """...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#....."""

    def "part1 warmup tests"() {
        expect:
        Day11.part1(input) == output

        where:
        input | output
        t1    | 374
    }

    def "part1"() {
        expect:
        Day11.part1(InputReader.read("y2023/day11")) == 9918828
    }

    def "part2 warmup tests"() {
        expect:
        Day11.part2(input, size) == output

        where:
        input | size | output
        t1    | 2    | 374
        t1    | 10   | 1030
        t1    | 100  | 8410
    }

    def "part2"() {
        expect:
        Day11.part2(InputReader.read("y2023/day11"), 1000000) == 692506533832
    }
}
