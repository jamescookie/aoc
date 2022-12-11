package aoc.y2022.day11

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day11Spec extends Specification {
    static def t1 = """Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1"""

    def "part1 warmup tests"() {
        expect:
        Day11.part1(input) == output

        where:
        input | output
        t1    | 10605
    }

    def "part1"() {
        expect:
        Day11.part1(InputReader.read("y2022/day11")) == 55944
    }

    def "part2 warmup tests"() {
        expect:
        Day11.part2(input, rounds) == output

        where:
        input | rounds | output
        t1    | 1      | 24
        t1    | 20     | 103 * 99
        t1    | 1000   | 5204 * 5192
        t1    | 10000  | 2713310158
    }

    def "part2"() {
        expect:
        Day11.part2(InputReader.read("y2022/day11"), 10000) == 15117269860
    }
}
