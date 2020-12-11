package aoc.y2020.day11

class Day11 {
    static char FLOOR = '.'
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
        def input = inputString.tokenize().collect {it.toCharArray()}
        boolean finished = false
        while(!finished) {
            def newLayout = input.collect{it.clone()}
            for (i in 0..<input.size()) {
                for (j in 0..<input[i].size()) {
                    def thing = input[i][j]
                    def booleans = [isPos2(input, i, j, 1, OCCUPIED),
                                    isPos2(input, i, j, 2, OCCUPIED),
                                    isPos2(input, i, j, 3, OCCUPIED),
                                    isPos2(input, i, j, 4, OCCUPIED),
                                    isPos2(input, i, j, 5, OCCUPIED),
                                    isPos2(input, i, j, 6, OCCUPIED),
                                    isPos2(input, i, j, 7, OCCUPIED),
                                    isPos2(input, i, j, 8, OCCUPIED)]
                    def howMany = booleans.findAll {it}.size()
                    if (thing == SEAT) {
                        if (!howMany) {
                            newLayout[i][j] = OCCUPIED
                        }
                    } else if (thing == OCCUPIED) {
                        if (howMany >= 5) {
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

    static boolean isPos(input, i, j, what) {
        if (i < 0 || j < 0) return false
        if (i >= input.size() || j >= input[i].size()) return false
        return input[i][j] == what
    }

    static boolean isPos2(input, i, j, dir, what) {
        def finished = false
        while (!finished) {
            switch (dir) {
                case 1:
                    i -= 1
                    j -= 1
                    break
                case 2:
                    i -= 1
                    break
                case 3:
                    i -= 1
                    j += 1
                    break
                case 4:
                    j -= 1
                    break
                case 5:
                    j += 1
                    break
                case 6:
                    i += 1
                    j -= 1
                    break
                case 7:
                    i += 1
                    break
                case 8:
                    i += 1
                    j += 1
                    break
            }
            finished = !isPos(input, i, j, FLOOR)
        }
        return isPos(input, i, j, what)
    }
}
