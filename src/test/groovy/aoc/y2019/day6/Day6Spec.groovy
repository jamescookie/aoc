package aoc.y2019.day6

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day6Spec extends Specification {

    def "part1"() {
        given:
        def model = readInput()

        expect:
        model.collect{Orbits.count(it,model)}.sum() == 158090
    }

    def "part2"() {
        given:
        def model = readInput()
        def santaOrbiting = Orbits.orbiting('SAN', model)
        def youOrbiting = Orbits.orbiting('YOU', model)
        def common = santaOrbiting.intersect(youOrbiting)

        expect:
        ((santaOrbiting - common).size() + (youOrbiting - common).size() - 2) == 241
    }

    def readInput() {
        def input = getClass().getResource('/day6.txt').getText()
        return input.tokenize().collect{it.tokenize(')')}
    }
}
