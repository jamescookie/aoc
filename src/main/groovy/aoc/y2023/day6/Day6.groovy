package aoc.y2023.day6

class Day6 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')*.tokenize(':')*.last()*.tokenize()*.collect { it as long }
        def time = input[0]
        def distance = input[1]
        long result = 1

        for (i in 0..<time.size()) {
            int better = 0
            for (j in 0..<time[i]) {
                long newDistance = calcDistance(j, time[i])
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
        def input = inputString.tokenize('\n')*.tokenize(':')*.last()*.tokenize()*.join('')
        def time = input[0] as long
        def distance = input[1] as long

        int better = 0
        for (j in 0..<time) {
            long newDistance = calcDistance(j, time)
            if (newDistance > distance) {
                better++
            }
        }

        return better
    }

    static long calcDistance(long buttonTime, long raceTime) {
        return (raceTime - buttonTime) * buttonTime
    }
}
