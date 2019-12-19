package aoc.y2019.day12


import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day12Spec extends Specification {

    def "part1 test a"() {
        given:
        def jupiter = new Jupiter([
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)
        ])

        expect:
        jupiter.doSteps(10).totalEnergy() == 179
    }

    def "part1 test b"() {
        given:
        def jupiter = new Jupiter([
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)
        ])

        expect:
        jupiter.doSteps(100).totalEnergy() == 1940
    }

    def "part1"() {
        given:
        def jupiter = new Jupiter([
                new Moon(17, -12, 13),
                new Moon(2, 1, 1),
                new Moon(-1, -17, 7),
                new Moon(12, -14, 18),
        ])

        expect:
        jupiter.doSteps(1000).totalEnergy() == 8960
    }

    def "part2 test a"() {
        given:
        def jupiter = new Jupiter([
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)
        ])

        expect:
        jupiter.findPreviousStep() == 2772
    }

    def "part2 test b"() {
        given:
        def jupiter = new Jupiter([
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)
        ])

        expect:
        jupiter.findPreviousStep() == 4_686_774_924
    }

    def "part2"() {
        given:
        def jupiter = new Jupiter([
                new Moon(17, -12, 13),
                new Moon(2, 1, 1),
                new Moon(-1, -17, 7),
                new Moon(12, -14, 18),
        ])

        expect:
        jupiter.findPreviousStep() == 314917503970904
    }
}
