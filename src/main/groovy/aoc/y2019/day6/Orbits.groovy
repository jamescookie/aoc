package aoc.y2019.day6

class Orbits {
    static int count(pair, orbits) {
        def parentPair = orbits.find { it[1] == pair[0] }
        if (parentPair) {
            return 1 + count(parentPair, orbits)
        } else {
            return 1
        }
    }

    static def orbiting(obj, orbits) {
        def pair = orbits.find { it[1] == obj }
        if (pair) {
            return orbiting(pair[0], orbits) + obj
        } else {
            return ['COM']
        }
    }
}
