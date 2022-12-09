package aoc.y2022.day9

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day9Spec extends Specification {
    static def t1 = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2"""

    def "part1 warmup tests"() {
        expect:
        Day9.part1(input) == output

        where:
        input | output
        t1    | 13
    }

    def "part1"() {
        expect:
        Day9.part1(InputReader.read("y2022/day9")) == 6745
    }

    def "part2 warmup tests"() {
        expect:
        Day9.part2(input) == output

        where:
        input                                        | output
        t1                                           | 1
        "R 5\nU 8\nL 8\nD 3\nR 17\nD 10\nL 25\nU 20" | 36
    }

    def "part2"() {
        expect:
        Day9.part2(InputReader.read("y2022/day9")) == 2793
    }
}
