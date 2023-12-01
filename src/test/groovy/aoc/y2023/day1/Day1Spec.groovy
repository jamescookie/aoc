package aoc.y2023.day1

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day1Spec extends Specification {
    static def t1 = """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""
    static def t2 = """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"""

    def "part1 warmup tests"() {
        expect:
        Day1.part1(input) == output

        where:
        input  | output
        t1     | 142
    }

    def "part1"() {
        expect:
        Day1.part1(InputReader.read("y2023/day1")) == 54953
    }

    def "part2 warmup tests"() {
        expect:
        Day1.part2(input) == output

        where:
        input  | output
        t2     | 281
    }

    def "part2"() {
        expect:
        Day1.part2(InputReader.read("y2023/day1")) == 53868
    }
}
