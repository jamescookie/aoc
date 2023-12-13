package aoc.y2023.day13

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day13Spec extends Specification {
    static def t1 = """#.##..##.
..#.##.#.
##......#
##......#
..#.##.#.
..##..##.
#.#.##.#.

#...##..#
#....#..#
..##..###
#####.##.
#####.##.
..##..###
#....#..#"""

    def "part1 warmup tests"() {
        expect:
        Day13.part1(input) == output

        where:
        input  | output
        t1     | 405
    }

    def "part1"() {
        expect:
        Day13.part1(InputReader.read("y2023/day13")) == 33735
    }

    def "part2 warmup tests"() {
        expect:
        Day13.part2(input) == output

        where:
        input  | output
        t1     | 400
    }

    def "part2"() {
        expect:
        Day13.part2(InputReader.read("y2023/day13")) == 26786 //too low
    }
}
