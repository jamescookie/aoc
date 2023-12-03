package aoc.y2023.day3

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day3Spec extends Specification {
    static def t1 = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...\$.*....
.664.598.."""

    def "part1 warmup tests"() {
        expect:
        Day3.part1(input) == output

        where:
        input  | output
        t1     | 4361
    }

    def "part1"() {
        expect:
        Day3.part1(InputReader.read("y2023/day3")) == 521601
    }

    def "part2 warmup tests"() {
        expect:
        Day3.part2(input) == output

        where:
        input  | output
        t1     | 467835
    }

    def "part2"() {
        expect:
        Day3.part2(InputReader.read("y2023/day3")) == 80694070
    }
}
