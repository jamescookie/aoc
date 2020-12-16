package aoc.y2020.day16

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day16Spec extends Specification {
    static def t1 = """class: 1-3 or 5-7
row: 6-11 or 33-44
seat: 13-40 or 45-50

your ticket:
7,1,14

nearby tickets:
7,3,47
40,4,50
55,2,20
38,6,12
"""
    static def t2 = """class: 0-1 or 4-19
row: 0-5 or 8-19
seat: 0-13 or 16-19

your ticket:
11,12,13

nearby tickets:
3,9,18
15,1,5
5,14,9"""
    static def t3 = """row 2: 0-1 or 4-19
row: 0-5 or 8-19
seat: 0-13 or 16-19

your ticket:
11,12,13

nearby tickets:
3,9,18
15,1,5
5,14,9"""

    def "part1 warmup tests"() {
        expect:
        Day16.part1(input) == output

        where:
        input | output
        t1    | 71
    }

    def "part1"() {
        expect:
        Day16.part1(InputReader.read("y2020/day16")) == 23122
    }

    def "part2 warmup tests"() {
        expect:
        Day16.part2(input, what) == output

        where:
        input | what    | output
        t2    | "row"   | 11
        t2    | "class" | 12
        t2    | "seat"  | 13
        t3    | "row"   | 132
    }

    def "part2"() {
        expect:
        Day16.part2(InputReader.read("y2020/day16"), "departure") == 362974212989
    }
}
