package aoc.y2022.day16

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day16Spec extends Specification {
    static def t1 = """Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
Valve BB has flow rate=13; tunnels lead to valves CC, AA
Valve CC has flow rate=2; tunnels lead to valves DD, BB
Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
Valve EE has flow rate=3; tunnels lead to valves FF, DD
Valve FF has flow rate=0; tunnels lead to valves EE, GG
Valve GG has flow rate=0; tunnels lead to valves FF, HH
Valve HH has flow rate=22; tunnel leads to valve GG
Valve II has flow rate=0; tunnels lead to valves AA, JJ
Valve JJ has flow rate=21; tunnel leads to valve II"""

    def "part1 valve test"() {
        given:
        Map<String, Day16.Valve> valves = [:]
        t1.split(System.lineSeparator()).each { new Day16.Valve(it, valves) }

        expect:
        path == valves[start].bestPath(target, [], valves)

        where:
        start | target | path
        'AA'  | 'BB'   | ['AA', 'BB']
        'AA'  | 'CC'   | ['AA', 'DD', 'CC']
        'AA'  | 'DD'   | ['AA', 'DD']
        'AA'  | 'EE'   | ['AA', 'DD', 'EE']
        'AA'  | 'FF'   | ['AA', 'DD', 'EE', 'FF']
        'AA'  | 'GG'   | ['AA', 'DD', 'EE', 'FF', 'GG']
        'AA'  | 'HH'   | ['AA', 'DD', 'EE', 'FF', 'GG', 'HH']
        'AA'  | 'II'   | ['AA', 'II']
        'AA'  | 'JJ'   | ['AA', 'II', 'JJ']
        'BB'  | 'AA'   | ['BB', 'AA']
        'DD'  | 'JJ'   | ['DD', 'AA', 'II', 'JJ']
        'JJ'  | 'HH'   | ['JJ', 'II', 'AA', 'DD', 'EE', 'FF', 'GG', 'HH']
    }

    def "part1 warmup tests"() {
        expect:
        Day16.part1(input) == output

        where:
        input | output
        t1    | 1651
    }

    def "part1"() {
        expect:
        Day16.part1(InputReader.read("y2022/day16")) == 1986
    }

    def "part2 warmup tests"() {
        expect:
        Day16.part2(input) == output

        where:
        input | output
        t1    | 1707
    }

    def "part2"() {
        expect:
        Day16.part2(InputReader.read("y2022/day16")) == 0
    }
}
