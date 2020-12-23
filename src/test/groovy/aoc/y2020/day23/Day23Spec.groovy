package aoc.y2020.day23

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day23Spec extends Specification {
    static def t1 = "389125467"

    def "part1 warmup tests"() {
        expect:
        Day23.part1(input, moves) == output

        where:
        input | moves | output
        t1    | 10    | 92658374
        t1    | 100   | 67384529
    }

    def "part1"() {
        expect:
        Day23.part1(InputReader.read("y2020/day23"), 100) == 35827964
    }

    def "part2 warmup tests"() {
        expect:
        Day23.part2(input) == output

        where:
        input | output
        t1    | 149245887792
    }

    def "part2"() {
        expect:
        Day23.part2(InputReader.read("y2020/day23")) == 0
    }
}
