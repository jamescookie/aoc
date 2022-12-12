package aoc.y2022.day12

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day12Spec extends Specification {
    static def t1 = """Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi"""

    def "part1 warmup tests"() {
        expect:
        Day12.part1(input) == output

        where:
        input  | output
        t1     | 31
    }

    def "part1"() {
        expect:
        Day12.part1(InputReader.read("y2022/day12")) == 352
    }

    def "part2 warmup tests"() {
        expect:
        Day12.part2(input) == output

        where:
        input  | output
        t1     | 29
    }

    def "part2"() {
        expect:
        Day12.part2(InputReader.read("y2022/day12")) == 345
    }
}
