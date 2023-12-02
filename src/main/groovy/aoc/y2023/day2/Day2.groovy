package aoc.y2023.day2

class Day2 {
    static part1(String inputString, Map<String, Integer> max) {
        def games = inputString.tokenize('\n')
        return games.collect { game ->
            def inputs = game.tokenize(':')
            int number = inputs[0].tokenize()[1] as int
            List<String> attempts = inputs[1].tokenize(';')
            if (attempts.any { attempt ->
                return attempt.tokenize(',')*.trim()*.tokenize().any {
                    ((it[0] as int) > max[it[1]])
                }
            }) {
                return 0
            }
            return number
        }.sum()
    }

    static part2(String inputString) {
        def games = inputString.tokenize('\n')
        return games.collect { game ->
            Map<String, Integer> max = [:]
            def inputs = game.tokenize(':')
            List<String> attempts = inputs[1].tokenize(';')
            attempts.each { attempt ->
                return attempt.tokenize(',')*.trim()*.tokenize().each {
                    int total = it[0] as int
                    max.compute(it[1], {k, v-> (v == null) ? total : v > total ? v : total})
                }
            }
            max.values().inject { a, b -> a * b }
        }.sum()
    }
}
