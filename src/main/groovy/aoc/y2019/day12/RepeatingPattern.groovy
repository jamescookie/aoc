package aoc.y2019.day12

class RepeatingPattern {
    def pattern = []
    def isRepeating = false
    def potentialRepeat = []
    def index = 0
    def potentiallyRepeating = false
    def repeated = 0

    def push(thing) {
        if (!isRepeating) {
            checkRepeating(thing)
        }
    }

    def checkRepeating(thing) {
        if (pattern.size() >= 1) {
            if (potentialRepeat == pattern) {
                repeated++
                potentialRepeat = []
                index = 0
                potentiallyRepeating = false
                if (repeated > 10) {
                    isRepeating = true
                }
            }
            if (thing == pattern[index]) {
                potentiallyRepeating = true
                potentialRepeat.add(thing)
                index++
            } else {
                if (potentiallyRepeating || repeated) {
                    def patternSoFar = pattern.clone()
                    repeated.times {pattern.addAll(patternSoFar)}
                    if (potentiallyRepeating) {
                        pattern.addAll(potentialRepeat)
                    }
                    potentialRepeat = []
                    potentiallyRepeating = false
                    index = 0
                    repeated = 0
                }
                if (thing == pattern[index]) {
                    potentiallyRepeating = true
                    potentialRepeat.add(thing)
                    index++
                } else {
                    pattern.add(thing)
                }
            }
        } else {
            pattern.add(thing)
        }
    }
}
