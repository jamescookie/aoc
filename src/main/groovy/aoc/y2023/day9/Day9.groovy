package aoc.y2023.day9

class Day9 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')*.tokenize(' ')*.collect { it as int }
        long result = 0

        for (def row in input) {
            def history = [row]
            while (!history[-1].every {it == 0}) {
                history.add(diffs(history[-1]))
            }
            history = history.reverse()
            history.pop()
            int addition = history.pop()[0]
            for (i in 0..<history.size()) {
                addition = history[i][-1] + addition
            }
            result += addition
        }

        return result
    }

    static part2(String inputString) {
        def input = inputString.tokenize('\n')*.tokenize(' ')*.collect { it as int }
        long result = 0

        for (def row in input) {
            def history = [row]
            while (!history[-1].every {it == 0}) {
                history.add(diffs(history[-1]))
            }
            history = history.reverse()
            history.pop()
            int addition = history.pop()[0]
            for (i in 0..<history.size()) {
                addition = history[i][0] - addition
            }
            result += addition
        }

        return result
    }

    static List<Integer> diffs(List<Integer> input) {
        List<Integer> result = []
        for (i in 0..<(input.size() - 1)) {
            result.add(input[i + 1] - input[i])
        }
        return result
    }
}
