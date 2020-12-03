package aoc.y2020.day3

class Day3 {
    static char TREE = '#'
    static part1(String inputString, int right, int down) {
        String[] input = inputString.tokenize()
        def width = input[0].size()
        def count = 0
        def current = 1
        for (int i = down; i < input.size(); i += down) {
            current += right
            if (current > width) current -= width
            if (input[i][current-1] == TREE) {
                count++
            }
        }

        return count
    }

    static part2(String inputString, List<List<Integer>> inputs) {
        long answer = 1
        inputs.collect {part1(inputString, it[0], it[1])}.each {answer *= it}
        return answer
    }
}
