package aoc.y2019.day10


import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day10Spec extends Specification {

    def "part1 tests"() {
        expect:
        new MonitoringStation(readInput(which)).best() == output

        where:
        which        | output
        'day10map1a' | 8
        'day10map1b' | 33
        'day10map1c' | 35
        'day10map1d' | 41
        'day10map1e' | 210
    }

    def "part1"() {
        expect:
        new MonitoringStation(readInput('day10')).best() == 253
    }


    def readInput(which) {
        return getClass().getResource("/${which}.txt").getText()
    }
}
