package aoc.y2023.day16

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day16Spec extends Specification {
    static def t1 = """.|...\\....
|.-.\\.....
.....|-...
........|.
..........
.........\\
..../.\\\\..
.-.-/..|..
.|....-|.\\
..//.|...."""

    def "part1 warmup tests"() {
        expect:
        Day16.part1(input) == output

        where:
        input  | output
        t1     | 46
    }

    def "part1"() {
        expect:
        Day16.part1(InputReader.read("y2023/day16")) == 7415
    }

    def "part2 warmup tests"() {
        expect:
        Day16.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day16.part2(InputReader.read("y2023/day16")) == 0
    }
}
