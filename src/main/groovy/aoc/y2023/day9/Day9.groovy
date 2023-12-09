package aoc.y2023.day9

class Day9 {
    static part1(String inputString) {
        return new Input(inputString).rows
                .inject(0) { a, b -> a + extrapolate(b, true) }
    }

    static part2(String inputString) {
        return new Input(inputString).rows
                .inject(0) { a, b -> a + extrapolate(b, false) }
    }

    static class Input {
        List<List<Integer>> rows

        Input(String inputString) {
            rows = inputString.tokenize('\n')*.split()*.collect { it as int }
        }
    }

    static int extrapolate(List<Integer> input, boolean forwards) {
        def history = [input]
        while (!history[-1].every { it == 0 }) {
            history.add(diffs(history[-1]))
        }
        history = history.reverse()
        history.pop()
        int addition = history.pop()[0]
        for (i in 0..<history.size()) {
            if (forwards) {
                addition = history[i][-1] + addition
            } else {
                addition = history[i][0] - addition
            }
        }
        return addition
    }

    static List<Integer> diffs(List<Integer> input) {
        List<Integer> result = []
        for (i in 0..<(input.size() - 1)) {
            result.add(input[i + 1] - input[i])
        }
        return result
    }
}
