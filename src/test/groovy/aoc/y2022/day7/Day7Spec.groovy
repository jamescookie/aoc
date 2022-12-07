package aoc.y2022.day7

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day7Spec extends Specification {
    static def t1 = """\$ cd /
\$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
\$ cd a
\$ ls
dir e
29116 f
2557 g
62596 h.lst
\$ cd e
\$ ls
584 i
\$ cd ..
\$ cd ..
\$ cd d
\$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k"""

    def "part1 warmup tests"() {
        expect:
        Day7.part1(input) == output

        where:
        input  | output
        t1     | 95437
    }

    def "part1"() {
        expect:
        Day7.part1(InputReader.read("y2022/day7")) == 1432936
    }

    def "part2 warmup tests"() {
        expect:
        Day7.part2(input) == output

        where:
        input  | output
        t1     | 24933642
    }

    def "part2"() {
        expect:
        Day7.part2(InputReader.read("y2022/day7")) == 272298
    }
}
