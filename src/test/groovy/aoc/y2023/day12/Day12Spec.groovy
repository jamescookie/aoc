package aoc.y2023.day12

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day12Spec extends Specification {
    // 1,     4, 1,  1,    4,     10
    // 1, 16384, 1, 16, 2500, 506250
    static def t1 = """???.### 1,1,3
.??..??...?##. 1,1,3
?#?#?#?#?#?#?#? 1,3,1,6
????.#...#... 4,1,1
????.######..#####. 1,6,5
?###???????? 3,2,1"""

    def "part1 warmup tests"() {
        expect:
        Day12.part1(input) == output

        where:
        input  | output
        t1     | 21
    }

    def "part1"() {
        expect:
        Day12.part1(InputReader.read("y2023/day12")) == 7090
    }

    def "part2 warmup tests"() {
        expect:
        Day12.part2(input) == output

        where:
        input  | output
        t1     | 525152
    }

    def "part2"() {
        expect:
        Day12.part2(InputReader.read("y2023/day12")) == 6792010726878
    }
}
