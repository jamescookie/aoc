package aoc.y2021.day14

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day14Spec extends Specification {
    static def t1 = """NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C"""

    def "part1 warmup tests"() {
        expect:
        Day14.part1(input, step) == output
        Day14.part2(input, step) == output

        where:
        input | step | output
        t1    | 1    | 1
        t1    | 2    | 5
        t1    | 3    | 7
        t1    | 4    | 18
        t1    | 5    | 33
        t1    | 10   | 1588
    }

    def "part1"() {
        expect:
        Day14.part1(InputReader.read("y2021/day14"), 10) == 2549
        Day14.part2(InputReader.read("y2021/day14"), 10) == 2549
    }

    def "part2 warmup tests"() {
        expect:
        Day14.part2(input, step) == output

        where:
        input | step | output
        t1    | 40   | 2188189693529
    }

    def "part2"() {
        expect:
        Day14.part2(InputReader.read("y2021/day14"), 40) == 2516901104210
    }
}
