package aoc.y2023.day10

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day10Spec extends Specification {
    static def t1 = """-L|F7
7S-7|
L|7||
-L-J|
L|-JF"""
    static def t2 = """7-F7-
.FJ|7
SJLL7
|F--J
LJ.LJ"""
    static def t3 = """...........
.S-------7.
.|F-----7|.
.||.....||.
.||.....||.
.|L-7.F-J|.
.|..|.|..|.
.L--J.L--J.
..........."""
    static def t3a = """..........
.S------7.
.|F----7|.
.||....||.
.||....||.
.|L-7F-J|.
.|..||..|.
.L--JL--J.
.........."""
    static def t4 = """.F----7F7F7F7F-7....
.|F--7||||||||FJ....
.||.FJ||||||||L7....
FJL7L7LJLJ||LJ.L-7..
L--J.L7...LJS7F-7L7.
....F-J..F7FJ|L7L7L7
....L7.F7||L7|.L7L7|
.....|FJLJ|FJ|F7|.LJ
....FJL-7.||.||||...
....L---J.LJ.LJLJ..."""
    static def t5 = """FF7FSF7F7F7F7F7F---7
L|LJ||||||||||||F--J
FL-7LJLJ||||||LJL-77
F--JF--7||LJLJ7F7FJ-
L---JF-JLJ.||-FJLJJ7
|F|F-JF---7F7-L7L|7|
|FFJF7L7F-JF7|JL---7
7-L-JL7||F7|L7F-7F7|
L.L7LFJ|||||FJL7||LJ
L7JLJL-JLJLJL--JLJ.L"""

    def "part1 warmup tests"() {
        expect:
        Day10.part1(input) == output

        where:
        input  | output
        t1     | 4
        t2     | 8
    }

    def "part1"() {
        expect:
        Day10.part1(InputReader.read("y2023/day10")) == 6856
    }

    def "part2 warmup tests"() {
        expect:
        Day10.part2(input) == output

        where:
        input  | output
//        t1     | 1
//        t2     | 1
        t3     | 4
        t3a    | 4
        t4     | 8
        t5     | 10
    }

    def "part2"() {
        expect:
        Day10.part2(InputReader.read("y2023/day10")) == 779 // too high
    }
}
