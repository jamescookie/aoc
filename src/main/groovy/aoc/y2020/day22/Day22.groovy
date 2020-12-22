package aoc.y2020.day22

class Day22 {
    static boolean playingPart1;
    static Map<Integer, List> previous
    static Map<String, Boolean> gameResults

    static part1(String inputString) {
        previous = [:]
        gameResults = [:]
        List<Integer>[] input = inputString.split('\n\n').collect { player -> player.replaceAll('Player \\d:', '').tokenize().collect { Integer.parseInt(it) } }
        playingPart1 = true
        play(0, input)
        return calcResult(input)
    }

    static part2(String inputString) {
        previous = [:]
        gameResults = [:]
        List<Integer>[] input = inputString.split('\n\n').collect { player -> player.replaceAll('Player \\d:', '').tokenize().collect { Integer.parseInt(it) } }
        playingPart1 = false
        play(0, input)
        return calcResult(input)
    }

    private static boolean playerOneWinsDueToInfinite(Integer key, String left) {
        if (previous.containsKey(key)) {
            def stuff = previous.get(key)
            if (stuff.contains(left)) {
                return true
            } else {
                stuff.add(left)
            }
        } else {
            previous.put(key, [left])
        }
        return false
    }

    private static boolean playerOneWins(int level, int card1, int card2, def left) {
        if (playingPart1) {
            return card1 > card2
        }
        if (card1 <= left[0].size() && card2 <= left[1].size()) {
            return play(level + 1, [left[0][0..(card1-1)], left[1][0..(card2-1)]])
        } else {
            return card1 > card2
        }
    }

    private static boolean play(int level, def input) {
        boolean finished = false
        String starting = input.toString()
        if (gameResults.containsKey(starting)) {
            return gameResults.get(starting)
        }
        while (!finished) {
            if (playerOneWinsDueToInfinite(level, input.toString())) {
                gameResults.put(starting, true)
                previous.removeAll {it.key >= level }
                return true
            } else {
                def card1 = input[0].pop()
                def card2 = input[1].pop()
                if (playerOneWins(level, card1, card2, input)) {
                    input[0].add(card1)
                    input[0].add(card2)
                } else {
                    input[1].add(card2)
                    input[1].add(card1)
                }
//                if (level == 0) {
//                    println input.toString()
//                }
                finished = input[0].isEmpty() || input[1].isEmpty()
            }
        }
        previous.removeAll {it.key >= level }
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
