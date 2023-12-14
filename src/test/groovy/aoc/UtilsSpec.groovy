package aoc

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class UtilsSpec extends Specification {
    def "pattern check"() {
        when:
        def actual = Utils.checkPattern(input, 3)

        then:
        actual.entrySet()[0].key == index
        actual.entrySet()[0].value.collect { it.intValue() } == pattern

        where:
        index | pattern               | input
        0     | [1, 2, 3]             | [1, 2, 3, 1, 2, 3, 1, 2, 3]
        3     | [1, 2, 3]             | [-1, 0, 100, 1, 2, 3, 1, 2, 3, 1, 2, 3]
        1     | [1]                   | [-1, 1, 1, 1, 1]
        10    | [1, 2]                | [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 1, 2, 1, 2, 1]
        3     | [1]                   | [-1, 1, 2, 1, 1, 1, 2, 1]
        1     | [1, 2, 3, 1, 2, 3, 4] | [0, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 1, 2, 3, 4, 5]
    }

    def "pattern check fails"() {
        expect:
        Utils.checkPattern(input, minRepeats) == null

        where:
        minRepeats | input
        3          | [1]
        3          | [1, 2, 3, 1, 2, 3]
        3          | [1, 2, 3, 1, 2, 3, 1, 2]
        8          | [1, 1, 1, 1, 1, 1, 1, 8]
    }
}
