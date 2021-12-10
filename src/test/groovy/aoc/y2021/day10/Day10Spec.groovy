package aoc.y2021.day10

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day10Spec extends Specification {
    static def t1 = """
[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]
"""

    def "part1 warmup tests"() {
        expect:
        Day10.part1(input) == output

        where:
        input  | output
        t1     | 26397
    }

    def "part1"() {
        expect:
        Day10.part1(InputReader.read("y2021/day10")) == 294195
    }

    def "part2 warmup tests"() {
        expect:
        Day10.part2(input) == output

        where:
        input  | output
        t1     | 288957
    }

    def "part2"() {
        expect:
        Day10.part2(InputReader.read("y2021/day10")) == 3490802734
    }
}
