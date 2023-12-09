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
        return history.reverse().inject(0) {a, b -> forwards ? b[-1] + a : b[0] - a}
    }

    static List<Integer> diffs(List<Integer> input) {
        return input[0..-2].withIndex().collect { it, i -> input[i + 1] - it }
    }
}
