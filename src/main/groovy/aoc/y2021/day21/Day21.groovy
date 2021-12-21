package aoc.y2021.day21

class Day21 {
    static part1(String inputString) {
        Integer[] input = inputString.trim().split('\n').collect { player->player.split(': ')[1] as int }
        int player1Pos = input[0] - 1
        int player2Pos = input[1] - 1
        long player1Score = 0
        long player2Score = 0
        int die = 0
        int diceRolls = 0
        while (true) {
            for (i in 0..<3) {
                die = roll(die)
                diceRolls++
                player1Pos += die
            }
            player1Pos = player1Pos % 10
            player1Score += (player1Pos + 1)
            if (player1Score >= 1000) {
                break
            }
            for (i in 0..<3) {
                die = roll(die)
                diceRolls++
                player2Pos += die
            }
            player2Pos = player2Pos % 10
            player2Score += (player2Pos + 1)
            if (player2Score >= 1000) {
                break
            }
        }
        return [player1Score, player2Score].min() * diceRolls
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static int roll(int die) {
        die++
        if (die > 100) {
            die -= 100
        }
        return die
    }
}
