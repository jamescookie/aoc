package aoc.y2021.day12

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day12Spec extends Specification {
    static def t1 = """start-A
start-b
A-c
A-b
b-d
A-end
b-end"""
    static def t2 = """dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc"""
    static def t3 = """fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW"""

    def "part1 warmup tests"() {
        expect:
        Day12.part1(input) == output

        where:
        input  | output
        t1     | 10
        t2     | 19
        t3     | 226
    }

    def "part1"() {
        expect:
        Day12.part1(InputReader.read("y2021/day12")) == 3450
    }

    def "part2 warmup tests"() {
        expect:
        Day12.part2(input) == output

        where:
        input  | output
        t1     | 36
        t2     | 103
        t3     | 3509
    }

    def "part2"() {
        expect:
        Day12.part2(InputReader.read("y2021/day12")) == 96528
    }
}
