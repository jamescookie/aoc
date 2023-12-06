package aoc.y2023.day6

class Day6 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')*.tokenize(':')*.last()*.tokenize()*.collect { it as int }
        def time = input[0]
        def distance = input[1]
        long result = 1

        for (i in 0..<time.size()) {
            int better = 0
            for (j in 0..<time[i]) {
                int newDistance = calcDistance(j, time[i])
                if (newDistance > distance[i]) {
                    better++
                }
            }
            if (better) {
                result *= better
            }
        }

        return result
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static int calcDistance(int buttonTime, int raceTime) {
        return (raceTime - buttonTime) * buttonTime
    }
}
