package aoc.y2020.day11

class Day11 {
    static char SEAT = 'L'
    static char OCCUPIED = '#'
    static part1(String inputString) {
        def input = inputString.tokenize().collect {it.toCharArray()}
        boolean finished = false
        while(!finished) {
            def newLayout = input.collect{it.clone()}
            for (i in 0..<input.size()) {
                for (j in 0..<input[i].size()) {
                    def thing = input[i][j]
                    def booleans = [isPos(input, i - 1, j - 1, OCCUPIED),
                                    isPos(input, i - 1, j, OCCUPIED),
                                    isPos(input, i - 1, j + 1, OCCUPIED),
                                    isPos(input, i, j - 1, OCCUPIED),
                                    isPos(input, i, j + 1, OCCUPIED),
                                    isPos(input, i + 1, j - 1, OCCUPIED),
                                    isPos(input, i + 1, j, OCCUPIED),
                                    isPos(input, i + 1, j + 1, OCCUPIED)]
                    def howMany = booleans.findAll {it}.size()
                    if (thing == SEAT) {
                        if (!howMany) {
                            newLayout[i][j] = OCCUPIED
                        }
                    } else if (thing == OCCUPIED) {
                        if (howMany >= 4) {
                            newLayout[i][j] = SEAT
                        }

                    }
                }
            }
            finished = (newLayout == input)
            input = newLayout
        }
        return input.flatten().findAll{it == OCCUPIED}.size()
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect {Integer.parseInt(it)}
        return null
    }

    static boolean isPos(input, i, j, what) {
        if (i < 0 || j < 0) return false
        if (i >= input.size() || j >= input[i].size()) return false
        return input[i][j] == what
    }
}
