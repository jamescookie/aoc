package aoc.y2021.day1

class Day1 {
    static part1(String inputString) {
        Integer[] input = inputString.tokenize().collect {Integer.parseInt(it)}
        return calc(1, input)
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect {Integer.parseInt(it)}
        return calc(3, input)
    }

    protected static int calc(int size, Integer[] input) {
        int count = 0
        int current = 0
        for (j in 0..<size) {
            current += input[j]
        }
        for (i in 1..<input.length) {
            if (i + size > input.length) break
            int next = 0
            for (j in i..<i + size) {
                next += input[j]
            }
            if (next > current) count++
            current = next
        }
        return count
    }
}
