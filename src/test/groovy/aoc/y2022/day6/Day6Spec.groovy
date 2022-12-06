package aoc.y2022.day6

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day6Spec extends Specification {

    def "part1 warmup tests"() {
        expect:
        Day6.part1(input) == output

        where:
        input                               | output
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb"    | 7
        "bvwbjplbgvbhsrlpgdmjqwftvncz"      | 5
        "nppdvjthqldpwncqszvftbrmjlhg"      | 6
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" | 10
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"  | 11
    }

    def "part1"() {
        expect:
        Day6.part1(InputReader.read("y2022/day6")) == 1582
    }

    def "part2 warmup tests"() {
        expect:
        Day6.part2(input) == output

        where:
        input | output
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb"    | 19
        "bvwbjplbgvbhsrlpgdmjqwftvncz"      | 23
        "nppdvjthqldpwncqszvftbrmjlhg"      | 23
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" | 29
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"  | 26
    }

    def "part2"() {
        expect:
        Day6.part2(InputReader.read("y2022/day6")) == 3588
    }
}
