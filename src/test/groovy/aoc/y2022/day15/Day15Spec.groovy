package aoc.y2022.day15

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day15Spec extends Specification {
    static def t1 = """Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3"""

    def "part1 warmup tests"() {
        expect:
        Day15.part1(input, y) == output

        where:
        input | y  | output
        t1    | 10 | 26
        t1    | 9  | 25
    }

    def "part1"() {
        expect:
        Day15.part1(InputReader.read("y2022/day15"), 2000000) == 5144286
    }

    def "part2 warmup tests"() {
        expect:
        Day15.part2(input, 20) == output

        where:
        input | output
        t1    | 56000011
    }

    def "part2"() {
        expect:
        Day15.part2(InputReader.read("y2022/day15"), 4000000) == 10229191267339
    }
}
