package aoc.y2021.day15

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day15Spec extends Specification {
    static def t1 = """1163751742
1381373672
2136511328
3694931569
7463417111
1319128137
1359912421
3125421639
1293138521
2311944581"""

    def "part1 warmup tests"() {
        expect:
        Day15.part1(input) == output

        where:
        input  | output
        t1     | 40
    }

    def "part1"() {
        expect:
        Day15.part1(InputReader.read("y2021/day15")) == 0
    }

    def "part2 warmup tests"() {
        expect:
        Day15.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day15.part2(InputReader.read("y2021/day15")) == 0
    }
}
