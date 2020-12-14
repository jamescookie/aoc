package aoc.y2020.day14

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day14Spec extends Specification {
    static def t1 = """mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
mem[8] = 11
mem[7] = 101
mem[8] = 0"""
    static def t2 = """mask = 000000000000000000000000000000X1001X
mem[42] = 100
mask = 00000000000000000000000000000000X0XX
mem[26] = 1"""


    def "part1 warmup tests"() {
        expect:
        Day14.part1(input) == output

        where:
        input | output
        t1    | 165
    }

    def "part1"() {
        expect:
        Day14.part1(InputReader.read("y2020/day14")) == 9879607673316
    }

    def "part2 warmup tests"() {
        expect:
        Day14.part2(input) == output

        where:
        input | output
        t2| 208
    }

    def "part2"() {
        expect:
        Day14.part2(InputReader.read("y2020/day14")) == 3435342392262
    }
}
