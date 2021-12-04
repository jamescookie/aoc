package aoc.y2021.day4

class Day4 {
    static part1(String inputString) {
        return bingo(inputString) { a, b -> a.lastCalledIndex <=> b.lastCalledIndex }
    }

    static part2(String inputString) {
        return bingo(inputString) { a, b -> b.lastCalledIndex <=> a.lastCalledIndex }
    }

    static int bingo(String inputString, Closure<Integer> sorting) {
        String[] input = inputString.split("\n\n")
        def calling = input[0].tokenize(",").collect { Integer.parseInt(it) }
        return input[1..-1].collect { new Board(it, calling) }.sort(sorting)[0].answer
    }

    private static class Board {
        int lastCalledIndex
        int answer
        Collection<Integer> answers = []

        Board(String input, Collection<Integer> numbers) {
            int[][] board = input.tokenize("\n").collect { row -> row.tokenize().collect { Integer.parseInt(it) } }
            for (i in 0..<board.length) {
                addAnswer(numbers, board[i])
            }
            for (i in 0..<board[0].length) {
                addAnswer(numbers, board*.getAt(i))
            }
            if (answers) {
                lastCalledIndex = answers.min()
                answer = (board.flatten() - numbers[0..lastCalledIndex]).sum() * numbers[lastCalledIndex]
            }
        }

        protected void addAnswer(Collection<Integer> numbers,  toCheck) {
            if (numbers.containsAll(toCheck)) {
                answers << toCheck.collect { x -> numbers.findIndexOf { it == x } }.max()
            }
        }
    }

}
