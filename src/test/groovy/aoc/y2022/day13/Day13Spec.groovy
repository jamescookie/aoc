package aoc.y2022.day13

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day13Spec extends Specification {
    static def t1 = """[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]"""

    def "part1 warmup tests"() {
        expect:
        Day13.part1(input) == output

        where:
        input                                                   | output
        t1                                                      | 13
        "[[[[],3],[5,[1],[8,5],10,[5,8]]],[],[1]]\n[[9,[4,9]]]" | 1
    }

    def "part1"() {
        expect:
        Day13.part1(InputReader.read("y2022/day13")) == 6086
    }

    def "part2 warmup tests"() {
        expect:
        Day13.part2(input) == output

        where:
        input | output
        t1    | 140
    }

    def "part2"() {
        expect:
        Day13.part2(InputReader.read("y2022/day13")) == 27930
    }
}
