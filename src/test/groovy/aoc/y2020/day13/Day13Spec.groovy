package aoc.y2020.day13

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day13Spec extends Specification {
    static def t1 = """939
7,13,x,x,59,x,31,19"""

    def "part1 warmup tests"() {
        expect:
        Day13.part1(input) == output

        where:
        input | output
        t1    | 295
    }

    def "part1"() {
        expect:
        Day13.part1(InputReader.read("y2020/day13")) == 205
    }

    def "part2 warmup tests"() {
        expect:
        Day13.part2a(input) == output

        where:
        input               | output
        "\n1,2,3,4"         | 1
        "\n1,2,3,5"         | 7
        "\n2,3,4,5"         | 2
        "\n17,x,13,19"      | 3417
        "\n67,7,59,61"      | 754018
        "\n1789,37,47,1889" | 1202161486
        t1                  | 1068781
    }

    def "part2"() {
        expect:
        Day13.part2a(InputReader.read("y2020/day13")) == 1
    }
}
