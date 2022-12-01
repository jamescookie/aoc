package aoc.y2022.day1

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day1Spec extends Specification {
    static def t1 = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"""

    def "part1 warmup tests"() {
        expect:
        Day1.part1(input) == output

        where:
        input  | output
        t1     | 24000
    }

    def "part1"() {
        expect:
        Day1.part1(InputReader.read("y2022/day1")) == 72017
    }

    def "part2 warmup tests"() {
        expect:
        Day1.part2(input) == output

        where:
        input  | output
        t1     | 45000
    }

    def "part2"() {
        expect:
        Day1.part2(InputReader.read("y2022/day1")) == 212520
    }
}
