package aoc.y2019.day12


import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day12Spec extends Specification {

    def "part1 test a"() {
        given:
        def jupiter = new Jupiter([
                new Moon(x: -1, y: 0, z: 2),
                new Moon(x: 2, y: -10, z: -7),
                new Moon(x: 4, y: -8, z: 8),
                new Moon(x: 3, y: 5, z: -1)
        ])

        expect:
        jupiter.doSteps(10).totalEnergy() == 179
    }

    def "part1 test b"() {
        given:
        def jupiter = new Jupiter([
                new Moon(x: -8, y: -10, z: 0),
                new Moon(x: 5, y: 5, z: 10),
                new Moon(x: 2, y: -7, z: 3),
                new Moon(x: 9, y: -8, z: -3)
        ])

        expect:
        jupiter.doSteps(100).totalEnergy() == 1940
    }

    def "part1"() {
        given:
        def jupiter = new Jupiter([
                new Moon(x:17, y:-12, z:13),
                new Moon(x:2, y:1, z:1),
                new Moon(x:-1, y:-17, z:7),
                new Moon(x:12, y:-14, z:18),
        ])

        expect:
        jupiter.doSteps(1000).totalEnergy() == 8960
    }

    def "part2 test a"() {
        given:
        def jupiter = new Jupiter([
                new Moon(x: -1, y: 0, z: 2),
                new Moon(x: 2, y: -10, z: -7),
                new Moon(x: 4, y: -8, z: 8),
                new Moon(x: 3, y: 5, z: -1)
        ])

        expect:
        jupiter.findPreviousStep() == 2772
    }

    def "part2 test b"() {
        given:
        def jupiter = new Jupiter([
                new Moon(x: -8, y: -10, z: 0),
                new Moon(x: 5, y: 5, z: 10),
                new Moon(x: 2, y: -7, z: 3),
                new Moon(x: 9, y: -8, z: -3)
        ])

        expect:
        jupiter.findPreviousStep() == 4_686_774_924
    }

    def "part2"() {
        given:
        def jupiter = new Jupiter([
                new Moon(x:17, y:-12, z:13),
                new Moon(x:2, y:1, z:1),
                new Moon(x:-1, y:-17, z:7),
                new Moon(x:12, y:-14, z:18),
        ])

        expect:
        jupiter.findPreviousStep() == 1
    }
}