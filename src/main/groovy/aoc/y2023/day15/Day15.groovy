package aoc.y2023.day15

class Day15 {
    static part1(String s) {
        def input = new Input(s.trim())
        return input.steps.collect { hash(it) }.sum()
    }

    static part2(String s) {
        return new Input(s).steps
                .inject(0) { a, b -> a + b.size() }
    }

    static class Input {
        List<String> steps

        Input(String s) {
            steps = s.tokenize(',')
        }
    }

    static long hash(String s) {
        long result = 0
        def chars = s.toCharArray()
        for (def character in chars) {
            result += character
            result *= 17
            result = result % 256
        }
        return result
    }
}
