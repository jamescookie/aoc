package aoc.y2021.day13

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day13Spec extends Specification {
    static def t1 = """6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5"""

    def "part1 warmup tests"() {
        expect:
        Day13.part1(input) == output

        where:
        input  | output
        t1     | 17
    }

    def "part1"() {
        expect:
        Day13.part1(InputReader.read("y2021/day13")) == 842
    }

    def "part2 warmup tests"() {
        expect:
        Day13.part2(input) == output

        where:
        input  | output
        t1     | "O"
    }

    def "part2"() {
        expect:
        Day13.part2(InputReader.read("y2021/day13")) == "BFKRCJZU"
    }
}
