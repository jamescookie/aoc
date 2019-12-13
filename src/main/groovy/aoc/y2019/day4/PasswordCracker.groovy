package aoc.y2019.day4

class PasswordCracker {
    int lower
    int upper
    def countPart1 = 0
    def countPart2 = 0

    def hasDouble = { String asString ->
        for (int i = 0; i < asString.size() - 1; i++) {
            if (asString[i] == asString[i + 1]) {
                return true
            }
        }
        return false
    }

    def repeatedJustTwice = { String asString ->
        def letterMap = asString.collect { it }
        return letterMap.collect { a -> letterMap.count { b -> a == b } }.find { it == 2 } != null
    }

    def isAscending = { String asString ->
        for (int i = 0; i < asString.size() - 1; i++) {
            if (asString[i] > asString[i + 1]) {
                return false
            }
        }
        return true
    }

    def calculate() {
        for (int i = lower; i < upper; i++) {
            String asString = i.toString()
            if (isAscending(asString)) {
                if (hasDouble(asString)) {
                    countPart1++
                    if (repeatedJustTwice(asString)) {
                        countPart2++
                    }
                }
            }
        }
    }
}
