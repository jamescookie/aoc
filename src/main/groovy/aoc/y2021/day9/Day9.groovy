package aoc.y2021.day9

class Day9 {
    static part1(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> (row as List).collect { it as int } }
        int answer = 0

        for (i in 0..<input.size()) {
            Integer[] row = input[i]
            for (j in 0..<row.size()) {
                boolean low = true
                int square = row[j]
                if (j - 1 >= 0) {
                    low = low && square < row[j - 1]
                }
                if (j + 1 < row.size()) {
                    low = low && square < row[j + 1]
                }
                if (i - 1 >= 0) {
                    low = low && square < input[i - 1][j]
                }
                if (i + 1 < input.size()) {
                    low = low && square < input[i + 1][j]
                }
                if (low) {
                    answer += square + 1
                }
            }
        }
        return answer
    }

    static part2(String inputString) {
        Integer[][] input = inputString.tokenize().collect { row -> (row as List).collect { it as int } }
        def answers = []

        for (i in 0..<input.size()) {
            Integer[] row = input[i]
            for (j in 0..<row.size()) {
                if (input[i][j] != 9) {
                    answers << findBasin(input, i, j)
                }
            }
        }

        answers = answers.sort()[-3..-1]
        return answers[0] * answers[1] * answers[2]
    }

    static int findBasin(Integer[][] input, int i, int j) {
        int answer = 1
        input[i][j] = 9

        if (j - 1 >= 0 && input[i][j - 1] != 9) {
            answer += findBasin(input, i, j - 1)
        }
        if (j + 1 < input[i].size() && input[i][j + 1] != 9) {
            answer += findBasin(input, i, j + 1)
        }
        if (i - 1 >= 0 && input[i - 1][j] != 9) {
            answer += findBasin(input, i - 1, j)
        }
        if (i + 1 < input.size() && input[i + 1][j] != 9) {
            answer += findBasin(input, i + 1, j)
        }

        return answer
    }
}
