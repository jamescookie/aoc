package aoc.y2021.day16

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day16Spec extends Specification {
    static def t1 = """"""

    def "part1 warmup tests"() {
        expect:
        Day16.part1(input) == output

        where:
        input                            | output
//        "D2FE28"                         | 6
        "38006F45291200"                 | 1 + 6 + 2
//        "EE00D40C823060"                 | 7 + 2 + 4 + 1
//        "8A004A801A8002F478"             | 16
//        "620080001611562C8802118E34"     | 12
//        "C0015000016115A2E0802F182340"   | 23
//        "A0016C880162017C3686B18A3D4780" | 31
    }

    def "part1"() {
        expect:
        Day16.part1(InputReader.read("y2021/day16")) == 866
    }

    def "part2 warmup tests"() {
        expect:
        Day16.part2(input) == output

        where:
        input                        | output
        "C200B40A82"                 | 3
        "04005AC33890"               | 54
        "880086C3E88112"             | 7
        "CE00C43D881120"             | 9
        "D8005AC2A8F0"               | 1
        "38006F45291200"             | 1
        "F600BC2D8F"                 | 0
        "9C005AC2F8F0"               | 0
        "9C0141080250320F1802104A08" | 1
    }

    def "part2"() {
        expect:
        Day16.part2(InputReader.read("y2021/day16")) == 1392637195518
    }
}
