package aoc.y2020.day15

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day15Spec extends Specification {

    def "part1 warmup tests"() {
        expect:
        Day15.part1(input, 2020) == output
        Day15.part2(input, 2020) == output

        where:
        input   | output
        "0,3,6" | 436
        "1,3,2" | 1
        "2,1,3" | 10
        "1,2,3" | 27
        "2,3,1" | 78
        "3,2,1" | 438
        "3,1,2" | 1836
    }

    def "part1"() {
        expect:
        Day15.part1(InputReader.read("y2020/day15"), 2020) == 475
        Day15.part2(InputReader.read("y2020/day15"), 2020) == 475
    }

    def "part2 warmup tests"() {
        expect:
        Day15.part2(input, 30000000) == output

        where:
        input   | output
        "0,3,6" | 175594
        "1,3,2" | 2578
        "2,1,3" | 3544142
        "1,2,3" | 261214
        "2,3,1" | 6895259
        "3,2,1" | 18
        "3,1,2" | 362
    }

    def "part2"() {
        expect:
        Day15.part2(InputReader.read("y2020/day15"), 30000000) == 11261
    }
}
