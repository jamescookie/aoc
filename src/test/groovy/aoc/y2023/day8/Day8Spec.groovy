package aoc.y2023.day8

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day8Spec extends Specification {
    static def t1 = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)"""
    static def t2 = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)"""
    static def t3 = """LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)"""

    def "part1 warmup tests"() {
        expect:
        Day8.part1(input) == output

        where:
        input | output
        t1    | 2
        t2    | 6
    }

    def "part1"() {
        expect:
        Day8.part1(InputReader.read("y2023/day8")) == 14893
    }

    def "part2 warmup tests"() {
        expect:
        Day8.part2(input) == output

        where:
        input | output
        t3    | 6
    }

    def "part2"() {
        expect:
        Day8.part2(InputReader.read("y2023/day8")) == 10241191004509
    }
}
