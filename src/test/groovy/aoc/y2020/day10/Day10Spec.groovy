package aoc.y2020.day10

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day10Spec extends Specification {
    static def t1 = """16\n10\n15\n5\n1\n11\n7\n19\n6\n12\n4"""
    static def t2 = """28\n33\n18\n42\n31\n14\n46\n20\n48\n47\n24\n23\n49\n45\n19\n38\n39\n11\n1\n32\n25\n35\n8\n17\n7\n9\n4\n2\n34\n10\n3"""

    def "part1 warmup tests"() {
        expect:
        Day10.part1(input) == output

        where:
        input | output
        t1    | 35
        t2    | 220
    }

    def "part1"() {
        expect:
        Day10.part1(InputReader.read("y2020/day10")) == 1984
    }

    def "part2 warmup tests"() {
        expect:
        Day10.part2(input) == output

        where:
        input                       | output
        "3\n6"                      | 1
        "3\n5\n6"                   | 2
        "1\n2\n3"                   | 4
        "1\n2\n3\n4"                | 6
        "1\n2\n3\n5"                | 5
        "2\n3\n5\n6"                | 4
        "1\n2\n3\n5\n6"             | 9
        "1\n2\n3\n4\n5\n6"          | 24
        "1\n2\n3\n4\n5\n6\n7\n8\n9" | 144
        t1                          | 8
//        t2                          | 19208
    }

    def "part2"() {
        expect:
        Day10.part2(InputReader.read("y2020/day10")) == 1
    }
}
