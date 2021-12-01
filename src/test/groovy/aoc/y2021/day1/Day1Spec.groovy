package aoc.y2021.day1

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day1Spec extends Specification {
    static def t1 = """199
200
208
210
200
207
240
269
260
263"""

    def "part1 warmup tests"() {
        expect:
        Day1.part1(input) == output

        where:
        input  | output
        t1     | 7
    }

    def "part1"() {
        expect:
        Day1.part1(InputReader.read("y2021/day1")) == 1400
    }

    def "part2 warmup tests"() {
        expect:
        Day1.part2(input) == output

        where:
        input  | output
        t1     | 5
    }

    def "part2"() {
        expect:
        Day1.part2(InputReader.read("y2021/day1")) == 1429
    }
}
