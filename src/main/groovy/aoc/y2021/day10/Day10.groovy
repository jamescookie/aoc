package aoc.y2021.day10

class Day10 {
    static Map<Character, Integer> score = [
            (')' as char): 3,
            (']' as char): 57,
            ('}' as char): 1197,
            ('>' as char): 25137
    ]
    static Map<Character, Character> matches = [
            ('(' as char): (')' as char),
            ('[' as char): (']' as char),
            ('{' as char): ('}' as char),
            ('<' as char): ('>' as char)
    ]

    static part1(String inputString) {
        String[] input = inputString.trim().tokenize().collect { it }

        return input.collect { findError(it as List) }.sum()
    }

    static part2(String inputString) {
        String[] input = inputString.trim().tokenize().collect { it }

        def answers = input.findAll { !findError(it as List) }.collect { finish(it as List) }

        return answers.sort()[answers.size() / 2]
    }

    static long finish(List<Character> input) {
        List<Character> opened = []
        Set<Character> openers = matches.keySet()

        for (i in 0..<input.size()) {
            Character current = input[i]
            if (openers.contains(current)) {
                opened.push(current)
            } else {
                opened.pop()
            }
        }
        return opened.inject(0L) { result, item -> (result * 5) + (openers.findIndexOf { it == item } + 1) }
    }

    static int findError(List<Character> input) {
        List<Character> opened = []
        Set<Character> openers = matches.keySet()

        for (i in 0..<input.size()) {
            Character current = input[i]
            if (openers.contains(current)) {
                opened.push(current)
            } else {
                def last = opened.pop()
                if (current != matches.get(last)) {
                    return score.get(current)
                }
            }
        }
        return 0
    }

}
