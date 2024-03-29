package aoc.y2019.day5

import aoc.y2019.IntCode
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day5Spec extends Specification {
    def puzzleInput = [3, 225, 1, 225, 6, 6, 1100, 1, 238, 225, 104, 0, 1102, 57, 23, 224, 101, -1311, 224, 224, 4, 224, 1002, 223, 8, 223, 101, 6, 224, 224, 1, 223, 224, 223, 1102, 57, 67, 225, 102, 67, 150, 224, 1001, 224, -2613, 224, 4, 224, 1002, 223, 8, 223, 101, 5, 224, 224, 1, 224, 223, 223, 2, 179, 213, 224, 1001, 224, -469, 224, 4, 224, 102, 8, 223, 223, 101, 7, 224, 224, 1, 223, 224, 223, 1001, 188, 27, 224, 101, -119, 224, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 7, 224, 1, 223, 224, 223, 1, 184, 218, 224, 1001, 224, -155, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 7, 224, 1, 224, 223, 223, 1101, 21, 80, 224, 1001, 224, -101, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 1, 224, 1, 224, 223, 223, 1101, 67, 39, 225, 1101, 89, 68, 225, 101, 69, 35, 224, 1001, 224, -126, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 1, 224, 1, 224, 223, 223, 1102, 7, 52, 225, 1102, 18, 90, 225, 1101, 65, 92, 225, 1002, 153, 78, 224, 101, -6942, 224, 224, 4, 224, 102, 8, 223, 223, 101, 6, 224, 224, 1, 223, 224, 223, 1101, 67, 83, 225, 1102, 31, 65, 225, 4, 223, 99, 0, 0, 0, 677, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1105, 0, 99999, 1105, 227, 247, 1105, 1, 99999, 1005, 227, 99999, 1005, 0, 256, 1105, 1, 99999, 1106, 227, 99999, 1106, 0, 265, 1105, 1, 99999, 1006, 0, 99999, 1006, 227, 274, 1105, 1, 99999, 1105, 1, 280, 1105, 1, 99999, 1, 225, 225, 225, 1101, 294, 0, 0, 105, 1, 0, 1105, 1, 99999, 1106, 0, 300, 1105, 1, 99999, 1, 225, 225, 225, 1101, 314, 0, 0, 106, 0, 0, 1105, 1, 99999, 1007, 226, 226, 224, 102, 2, 223, 223, 1005, 224, 329, 1001, 223, 1, 223, 108, 677, 226, 224, 1002, 223, 2, 223, 1005, 224, 344, 1001, 223, 1, 223, 1007, 677, 677, 224, 1002, 223, 2, 223, 1005, 224, 359, 1001, 223, 1, 223, 1107, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 374, 1001, 223, 1, 223, 8, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 389, 101, 1, 223, 223, 8, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 404, 1001, 223, 1, 223, 1008, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 419, 1001, 223, 1, 223, 107, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 434, 101, 1, 223, 223, 7, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 449, 1001, 223, 1, 223, 1107, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 464, 1001, 223, 1, 223, 1107, 226, 677, 224, 1002, 223, 2, 223, 1005, 224, 479, 1001, 223, 1, 223, 8, 677, 226, 224, 1002, 223, 2, 223, 1006, 224, 494, 1001, 223, 1, 223, 1108, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 509, 101, 1, 223, 223, 1008, 677, 677, 224, 1002, 223, 2, 223, 1006, 224, 524, 1001, 223, 1, 223, 1008, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 539, 1001, 223, 1, 223, 1108, 677, 677, 224, 102, 2, 223, 223, 1005, 224, 554, 101, 1, 223, 223, 108, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 569, 101, 1, 223, 223, 1108, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 584, 1001, 223, 1, 223, 108, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 599, 1001, 223, 1, 223, 1007, 226, 677, 224, 102, 2, 223, 223, 1005, 224, 614, 1001, 223, 1, 223, 7, 226, 677, 224, 102, 2, 223, 223, 1006, 224, 629, 1001, 223, 1, 223, 107, 226, 226, 224, 102, 2, 223, 223, 1005, 224, 644, 101, 1, 223, 223, 7, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 659, 101, 1, 223, 223, 107, 677, 677, 224, 1002, 223, 2, 223, 1005, 224, 674, 1001, 223, 1, 223, 4, 223, 99, 226]

    def "part1 tests"() {
        given:
        def code = new IntCode(null, input)
        code.process()

        expect:
        code.programme == output

        where:
        input                 | output
        [1002, 4, 3, 4, 33]   | [1002, 4, 3, 4, 99]
        [1101, 100, -1, 4, 0] | [1101, 100, -1, 4, 99]
    }

    def "part1"() {
        expect:
        new IntCode(1, puzzleInput).processUntilNonZero() == 14155342
    }

//    def "part2 tests"() {
//        expect:
//        for (int i = 0; i < 10; i++) {
//            assert new IntCode(i, input).process() == output(i)
//        }
//
//        where:
//        output                                        | input
//        { i -> (i == 8 ? 1 : 0) }                     | [3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8]
//        { i -> (i < 8 ? 1 : 0) }                      | [3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8]
//        { i -> (i == 8 ? 1 : 0) }                     | [3, 3, 1108, -1, 8, 3, 4, 3, 99]
//        { i -> (i < 8 ? 1 : 0) }                      | [3, 3, 1107, -1, 8, 3, 4, 3, 99]
//        { i -> (i < 8 ? 999 : i == 8 ? 1000 : 1001) } | [3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31, 1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104, 999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99]
//    }

    def "part2"() {
        expect:
        new IntCode(5, puzzleInput).processUntilNonZero() == 8684145
    }

}
