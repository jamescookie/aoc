package aoc.y2023.day2

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day2Spec extends Specification {
    static def t1 = """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
"""

    def "part1 warmup tests"() {
        expect:
        Day2.part1(input, ['red': 12, 'green': 13, 'blue': 14]) == output

        where:
        input  | output
        t1     | 8
    }

    def "part1"() {
        expect:
        Day2.part1(InputReader.read("y2023/day2"), ['red': 12, 'green': 13, 'blue': 14]) == 2528
    }

    def "part2 warmup tests"() {
        expect:
        Day2.part2(input) == output

        where:
        input  | output
        t1     | 2286
    }

    def "part2"() {
        expect:
        Day2.part2(InputReader.read("y2023/day2")) == 67363
    }
}
