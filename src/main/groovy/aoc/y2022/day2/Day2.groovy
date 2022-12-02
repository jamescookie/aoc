package aoc.y2022.day2

class Day2 {
    static def replacements = [
            //Theirs
            'A': 1, //Rock
            'B': 2, //Paper
            'C': 3, //Scissors
            //Mine
            'X': 1, //Rock / Lose
            'Y': 2, //Paper / Draw
            'Z': 3, //Scissors / Win
    ]

    // Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock

    static part1(String inputString) {
        def input = inputString.split('\n').collect { it.tokenize().collect {replacements.get(it)} }
        long score = 0
        input.each {
            def mine = it[1]
            def theirs = it[0]
            score += mine
            if (mine == theirs) {
                score += 3
            } else {
                def check = theirs + 1
                if (check > 3) check = 1
                if (check == mine) {
                    score += 6
                }
            }
        }
        return score
    }

    static part2(String inputString) {
        def input = inputString.split('\n').collect { it.tokenize().collect {replacements.get(it)} }
        long score = 0
        input.each {
            def mine = it[1]
            def theirs = it[0]
            def check = theirs;
            switch (mine) {
                case 1:
                    check--
                    if (check < 1) check = 3
                    score += check
                    break
                case 2:
                    score += 3
                    score += check
                    break
                case 3:
                    score += 6
                    check++
                    if (check > 3) check = 1
                    score += check
                    break
            }
        }
        return score
    }
}
