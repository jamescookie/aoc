package aoc.y2020.day1

class Day1 {
    static part1(String inputString, Integer expected) {
        Integer[] input = inputString.tokenize().collect {Integer.parseInt(it)}
        for (i in 0..<input.size()) {
            for (j in i..<input.size()) {
                if (input[i] + input[j] == expected) {
                    return input[i] * input[j]
                }
            }
        }
        return 0
    }

    static part2(String inputString, Integer expected) {
        Integer[] input = inputString.tokenize().collect {Integer.parseInt(it)}
        for (i in 0..<input.size()) {
            for (j in i..<input.size()) {
                for (k in 0..<input.size()) {
                    if (input[i] + input[j] + input[k] == expected) {
                        return input[i] * input[j] * input[k]
                    }
                }
            }
        }
        return 0
    }
}
