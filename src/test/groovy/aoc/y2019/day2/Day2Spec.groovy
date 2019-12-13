package aoc.y2019.day2

import aoc.y2019.IntCode
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day2Spec extends Specification {
    def puzzleInput = [1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,6,1,19,1,5,19,23,1,13,23,27,1,6,27,31,2,31,13,35,1,9,35,39,2,39,13,43,1,43,10,47,1,47,13,51,2,13,51,55,1,55,9,59,1,59,5,63,1,6,63,67,1,13,67,71,2,71,10,75,1,6,75,79,1,79,10,83,1,5,83,87,2,10,87,91,1,6,91,95,1,9,95,99,1,99,9,103,2,103,10,107,1,5,107,111,1,9,111,115,2,13,115,119,1,119,10,123,1,123,10,127,2,127,10,131,1,5,131,135,1,10,135,139,1,139,2,143,1,6,143,0,99,2,14,0,0]

    def "part1 tests"() {
        given:
        def code = new IntCode(null, input)
        code.process()

        expect:
        code.programme == output

        where:
        input                         | output
        [1, 0, 0, 0, 99]              | [2, 0, 0, 0, 99]
        [2, 3, 0, 3, 99]              | [2, 3, 0, 6, 99]
        [2, 4, 4, 5, 99, 0]           | [2, 4, 4, 5, 99, 9801]
        [1, 1, 1, 4, 99, 5, 6, 0, 99] | [30, 1, 1, 4, 2, 5, 6, 0, 99]
    }

    def "part1"() {
        given:
        def input = puzzleInput.clone()
        input[1] = 12
        input[2] = 2
        def code = new IntCode(null, input)
        code.process()

        expect:
        code.programme[0] == 5290681
    }

    def "part2"() {
        given:
        def input = puzzleInput.clone()
        def result = 0
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                input[1] = i
                input[2] = j
                def code = new IntCode(null, input)
                code.process()
                if (code.programme[0] == 19690720) {
                    result = (100 * i + j)
                    break
                }
            }
        }

        expect:
        result == 5741
    }

}