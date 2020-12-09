package aoc.y2020.day9

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day9Spec extends Specification {
    static def t1 = (1..25).collect { "$it" }.join("\n")
    static def t2 = """35\n20\n15\n25\n47\n40\n62\n55\n65\n95\n102\n117\n150\n182\n127\n219\n299\n277\n309\n576"""

    def "part1 warmup tests"() {
        expect:
        Day9.part1(input, preamble) == output

        where:
        input       | preamble | output
        t1 + "\n27" | 25       | 0
        t1 + "\n65" | 25       | 65
        t1 + "\n49" | 25       | 0
        t2          | 5        | 127
    }

    def "part1"() {
        expect:
        Day9.part1(InputReader.read("y2020/day9"), 25) == 36845998
    }

    def "part2 warmup tests"() {
        expect:
        Day9.part2(input, preamble) == output

        where:
        input       | preamble | output
        t2          | 5        | 62
    }

    def "part2"() {
        expect:
        Day9.part2(InputReader.read("y2020/day9"), 25) == 4830226
    }
}
