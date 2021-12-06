package aoc.y2021.day6

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day6Spec extends Specification {
    static def t1 = """3,4,3,1,2"""

    def "part1 warmup tests"() {
        expect:
        Day6.part1(input, days) == output

        where:
        input | days | output
        t1    | 1    | 5
        t1    | 2    | 6
        t1    | 3    | 7
        t1    | 18   | 26
        t1    | 80   | 5934
        t1    | 256  | 26984457539
    }

    def "part1"() {
        expect:
        Day6.part1(InputReader.read("y2021/day6"), 80) == 362639
    }

    def "part2"() {
        expect:
        Day6.part1(InputReader.read("y2021/day6"), 256) == 1639854996917
    }
}
