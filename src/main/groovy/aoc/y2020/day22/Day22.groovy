package aoc.y2020.day22

class Day22 {
    static boolean playingPart1;
    static Set<String> previous
    static Map<String, Boolean> gameResults

    static part1(String inputString) {
        previous = []
        gameResults = [:]
        List<Integer>[] input = inputString.split('\n\n').collect { player -> player.replaceAll('Player \\d:', '').tokenize().collect { Integer.parseInt(it) } }
        playingPart1 = true
        play(input)
        return calcResult(input)
    }

    static part2(String inputString) {
        previous = []
        gameResults = [:]
        List<Integer>[] input = inputString.split('\n\n').collect { player -> player.replaceAll('Player \\d:', '').tokenize().collect { Integer.parseInt(it) } }
        playingPart1 = false
        play(input)
        return calcResult(input)
    }

    private static boolean playerOneWins(int card1, int card2, def left) {
        if (!playingPart1 && card1 <= left[0].size() && card2 <= left[1].size()) {
            def newCards = [left[0][0..(card1 - 1)], left[1][0..(card2 - 1)]]
            if (gameResults.containsKey(newCards.toString())) {
                return gameResults.get(newCards.toString())
            }
            return play(newCards)
        } else {
            return card1 > card2
        }
    }

    private static boolean play(def input) {
        boolean finished = false
        String starting = input.toString()
        while (!finished) {
            if (!previous.add(input.toString())) {
                gameResults.put(starting, true)
                return true
            } else {
                def card1 = input[0].pop()
                def card2 = input[1].pop()
                if (playerOneWins(card1, card2, input)) {
                    input[0].add(card1)
                    input[0].add(card2)
                } else {
                    input[1].add(card2)
                    input[1].add(card1)
                }
                finished = input[0].isEmpty() || input[1].isEmpty()
            }
        }
        gameResults.put(starting, input[1].isEmpty())
        return input[1].isEmpty()
    }

    private static long calcResult(List<Integer>[] input) {
        long result = 0
        def winning = (input[0].isEmpty() ? input[1] : input[0]).reverse()
        for (i in 0..<winning.size()) {
            result += ((i + 1) * winning[i])
        }
        return result
    }
}
