package aoc.y2023.day4

class Day4 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')*.tokenize(':')*.last()*.tokenize('|')
        long result = 0
        for (i in 0..<input.size()) {
            def winning = input[i][0].tokenize().collect {it as int}
            def hand = input[i][1].tokenize().collect {it as int}
            def intersect = winning.intersect(hand)
            if (intersect) {
                result += (1 << (intersect.size() - 1))
            }
        }
        return result
    }

    static part2(String inputString) {
        def input = inputString.tokenize('\n')*.tokenize(':')*.last()*.tokenize('|')
        long result = 0
        List<List<Integer>> results = []
        for (i in 0..<input.size()) {
            def winning = input[i][0].tokenize().collect {it as int}
            def hand = input[i][1].tokenize().collect {it as int}
            def intersect = winning.intersect(hand)
            results << [intersect.size(), 1]
        }
        for (i in 0..<results.size()) {
            int x = results[i][0]
            for (j in 1..<(x+1)) {
                results[i+j][1] = results[i+j][1] + results[i][1]
            }
        }
        return results.collect{it[1]}.sum()
    }
}
