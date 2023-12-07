package aoc.y2023.day7

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day7Spec extends Specification {
    static def t1 = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""

    def "part1 warmup tests"() {
        expect:
        Day7.part1(input) == output

        where:
        input  | output
        t1     | 6440
    }

    def "part1"() {
        expect:
        Day7.part1(InputReader.read("y2023/day7")) == 246912307
    }

    def "part2 warmup tests"() {
        expect:
        Day7.part2(input) == output

        where:
        input  | output
        t1     | 5905
    }

    def "part2"() {
        expect:
        Day7.part2(InputReader.read("y2023/day7")) == 246894760
    }
}
