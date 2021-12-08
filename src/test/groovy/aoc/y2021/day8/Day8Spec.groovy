package aoc.y2021.day8

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day8Spec extends Specification {
    static def t0 = """
acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf
"""
    static def t1 = """
be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
"""

    def "part1 warmup tests"() {
        expect:
        Day8.part1(input, [1,4,7,8]) == output

        where:
        input  | output
        t1     | 8 + 6 + 5 + 7
    }

    def "part1"() {
        expect:
        Day8.part1(InputReader.read("y2021/day8"), [1,4,7,8]) == 255
    }

    def "part2 warmup tests"() {
        expect:
        Day8.part2(input) == output

        where:
        input  | output
        t0     | 5353
        t1     | 8394+9781+1197+9361+4873+8418+4548+1625+8717+4315
    }

    def "part2"() {
        expect:
        Day8.part2(InputReader.read("y2021/day8")) == 982158
    }
}
