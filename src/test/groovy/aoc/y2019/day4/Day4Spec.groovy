package aoc.y2019.day4

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day4Spec extends Specification {

    def "part1"() {
        given:
        def cracker = new PasswordCracker(upper: 643603, lower: 171309)
        cracker.calculate()

        expect:
        cracker.countPart1 == 1625
    }

    def "part2"() {
        given:
        def cracker = new PasswordCracker(upper: 643603, lower: 171309)
        cracker.calculate()

        expect:
        cracker.countPart2 == 1111
    }

}
