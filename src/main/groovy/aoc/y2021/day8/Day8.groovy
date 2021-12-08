package aoc.y2021.day8

class Day8 {
    static Map<Integer, String> segments = [
            0: 'abcefg', // 6 (contains all of 7)
            1: 'cf',     // 2 -
            2: 'acdeg',  // 5 (what's left)
            3: 'acdfg',  // 5 (contains all of 7)
            4: 'bcdf',   // 4 -
            5: 'abdfg',  // 5 (is contained by 6)
            6: 'abdefg', // 6 (what's left)
            7: 'acf',    // 3 -
            8: 'abcdefg',// 7 -
            9: 'abcdfg'  // 6 (contains all of 4)
    ]

    static part1(String inputString, List<Integer> seeking) {
        def input = inputString.trim().tokenize('\n').collect { row -> row.tokenize('|').collect { it.tokenize() } }

        int total = 0
        for (i in 0..<seeking.size()) {
            total += input.collect { row -> row[1].findAll { it.size() == segments.get(seeking[i]).size() }.size() }.sum()
        }

        return total
    }

    static part2(String inputString) {
        def input = inputString.trim().tokenize('\n').collect { row -> row.tokenize('|').collect { it.tokenize().collect { (it as List).sort().join() } } }

        return input.collect { encode(decode(it[0]), it[1]) }.sum()
    }

    static Map<String, Integer> decode(List<String> input) {
        Map<Integer, String> answer =
                [1, 4, 7, 8].collectEntries { number ->
                    def found = null
                    def results = input.findAll { it.size() == segments.get(number).size() }
                    if (results.size() == 1) {
                        found = results[0]
                    }
                    [(number): found]
                }
        input.findAll { it.size() == segments.get(6).size() }.each { y ->
            if ((y as List).containsAll(answer.get(4) as List)) {
                answer.put(9, y)
            } else if ((y as List).containsAll(answer.get(7) as List)) {
                answer.put(0, y)
            } else {
                answer.put(6, y)
            }
        }
        input.findAll { it.size() == segments.get(5).size() }.each { y ->
            if ((y as List).containsAll(answer.get(7) as List)) {
                answer.put(3, y)
            } else if ((answer.get(6) as List).containsAll(y as List)) {
                answer.put(5, y)
            } else {
                answer.put(2, y)
            }
        }

        return answer.collectEntries { k, v -> [(v): k] }
    }

    static long encode(Map<String, Integer> answer, List<String> output) {
        output.collect { answer.get(it) }.join() as long
    }
}




