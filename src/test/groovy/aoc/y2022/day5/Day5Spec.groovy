package aoc.y2022.day5

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day5Spec extends Specification {
    static def t1 = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"""

    def "part1 warmup tests"() {
        expect:
        Day5.part1(input) == output

        where:
        input  | output
        t1     | "CMZ"
    }

    def "part1"() {
        expect:
        Day5.part1(InputReader.read("y2022/day5")) == "VWLCWGSDQ"
    }

    def "part2 warmup tests"() {
        expect:
        Day5.part2(input) == output

        where:
        input  | output
        t1     | "MCD"
    }

    def "part2"() {
        expect:
        Day5.part2(InputReader.read("y2022/day5")) == "TCGLQSLPW"
    }
}
