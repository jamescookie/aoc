package aoc.y2023.day17

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day17Spec extends Specification {
    static def t1 = """2413432311323
3215453535623
3255245654254
3446585845452
4546657867536
1438598798454
4457876987766
3637877979653
4654967986887
4564679986453
1224686865563
2546548887735
4322674655533"""

    def "part1 warmup tests"() {
        expect:
        Day17.part1(input) == output

        where:
        input  | output
        t1     | 102
    }

    def "part1"() {
        expect:
        Day17.part1(InputReader.read("y2023/day17")) == 720 //too high
    }

    def "part2 warmup tests"() {
        expect:
        Day17.part2(input) == output

        where:
        input  | output
        t1     | 0
    }

    def "part2"() {
        expect:
        Day17.part2(InputReader.read("y2023/day17")) == 0
    }
}
