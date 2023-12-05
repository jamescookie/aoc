package aoc.y2023.day5

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day5Spec extends Specification {
    static def t1 = """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""

    def "part1 warmup tests"() {
        expect:
        Day5.part1(input) == output

        where:
        input | output
        t1    | 35
    }

    def "part1"() {
        expect:
        Day5.part1(InputReader.read("y2023/day5")) == 199602917
    }

    def "part2 warmup tests"() {
        expect:
        Day5.part2(input) == output

        where:
        input | output
        t1    | 46
    }

    def "find ranges"() {
        expect:
        Day5.findRanges(entries.collect { new Day5.Entry(it) }, input.collect { new Day5.BigRange(it) }).sort() == output.collect { new Day5.BigRange(it) }.sort()

        where:
        entries                 | input            | output
        ['50 98 2', '52 50 48'] | ['0-99']         | ['0-49', '52-98', '50-51']
        ['50 98 2', '52 50 48'] | ['79-92']        | ['81-94']
        ['10 15 5']             | ['0-19']         | ['0-14', '10-14']
        ['0 69 1', '1 0 69']    | ['60-70']        | ['61-69', '0-0', '70-70']
        ['100 150 10']          | ['0-0', '10-20'] | ['0-0', '10-20']
        ['10 15 5']             | ['0-30']         | ['0-14', '10-15', '20-30']
        ['10 15 5']             | ['0-12']         | ['0-12']
        ['10 15 5']             | ['0-17']         | ['0-14', '10-12']
        ['10 15 5']             | ['20-30']        | ['20-30']
        ['10 15 5']             | ['17-30']        | ['12-15', '20-30']
    }

    def "overlap"() {
        expect:
        new Day5.BigRange(input).overlaps(new Day5.BigRange(input2)) == output
        new Day5.BigRange(input2).overlaps(new Day5.BigRange(input)) == output

        where:
        input  | input2  | output
        '5-10' | '5-10'  | true
        '5-10' | '0-7'   | true
        '5-10' | '7-8'   | true
        '5-10' | '8-12'  | true
        '5-10' | '0-4'   | false
        '5-10' | '11-15' | false
    }

    def "part2"() {
        expect:
        Day5.part2(InputReader.read("y2023/day5")) == 2254686
    }
}
