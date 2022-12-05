package aoc.y2022.day3

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day3Spec extends Specification {
    static def t1 = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"""

    def "part1 warmup tests"() {
        expect:
        Day3.part1(input) == output

        where:
        input  | output
        t1     | 157
    }

    def "part1"() {
        expect:
        Day3.part1(InputReader.read("y2022/day3")) ==8176
    }

    def "part2 warmup tests"() {
        expect:
        Day3.part2(input) == output

        where:
        input  | output
        t1     | 70
    }

    def "part2"() {
        expect:
        Day3.part2(InputReader.read("y2022/day3")) == 2689
    }
}
