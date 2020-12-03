package aoc.y2020.day3

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day3Spec extends Specification {
    static String p1 = """..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#"""

    def "part1 warmup tests"() {
        expect:
        Day3.part1(input, 3, 1) == output

        where:
        input | output
        p1    | 7
    }

    def "part1"() {
        expect:
        Day3.part1(InputReader.read("y2020/day3"), 3, 1) == 205
    }

    def "part2 warmup tests"() {
        expect:
        Day3.part2(input, list) == output

        where:
        input | list                                     | output
        p1    | [[1, 1], [3, 1], [5, 1], [7, 1], [1, 2]] | 336
    }

    def "part2"() {
        expect:
        Day3.part2(InputReader.read("y2020/day3"), [[1, 1], [3, 1], [5, 1], [7, 1], [1, 2]]) == 3952146825
    }
}
