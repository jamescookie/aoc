package aoc.y2020.day21

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day21Spec extends Specification {
    static def t1 = """mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
trh fvjkl sbzzf mxmxvkd (contains dairy)
sqjhc fvjkl (contains soy)
sqjhc mxmxvkd sbzzf (contains fish)
"""

    def "part1 warmup tests"() {
        expect:
        Day21.part1(input) == output

        where:
        input  | output
        t1     | 5
    }

    def "part1"() {
        expect:
        Day21.part1(InputReader.read("y2020/day21")) == 2061
    }

    def "part2 warmup tests"() {
        expect:
        Day21.part2(input) == output

        where:
        input  | output
        t1     | "mxmxvkd,sqjhc,fvjkl"
    }

    def "part2"() {
        expect:
        Day21.part2(InputReader.read("y2020/day21")) == "cdqvp,dglm,zhqjs,rbpg,xvtrfz,tgmzqjz,mfqgx,rffqhl"
    }
}
