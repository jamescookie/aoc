package aoc.y2023.day2

class Day2 {
    static part1(String inputString, Map<String, Integer> max) {
        inputString.tokenize('\n').collect { game ->
            def inputs = game.tokenize(':')
            int number = inputs[0].tokenize()[1] as int
            inputs[1].tokenize(';').any { attempt ->
                attempt.tokenize(',')*.trim()*.tokenize().any {
                    (it[0] as int) > max[it[1]]
                }
            } ? 0 : number
        }.sum()
    }

    static part2(String inputString) {
        inputString.tokenize('\n').collect { game ->
            Map<String, Integer> max = [:]
            game.tokenize(':')[1].tokenize(';').each { attempt ->
                attempt.tokenize(',')*.trim()*.tokenize().each {
                    int total = it[0] as int
                    max.compute(it[1], { k, v -> !v ? total : v > total ? v : total })
                }
            }
            max.values().inject { a, b -> a * b }
        }.sum()
    }
}
