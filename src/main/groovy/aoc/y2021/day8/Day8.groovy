package aoc.y2021.day8

class Day8 {
    static Map<Integer, String> segments = [
            1: 'cf',     // 2 -
            7: 'acf',    // 3 -
            4: 'bcdf',   // 4 -
            8: 'abcdefg',// 7 -
            9: 'abcdfg', // 6 (contains all of 4)
            0: 'abcefg', // 6 (contains all of 7)
            6: 'abdefg', // 6 (what's left)
            3: 'acdfg',  // 5 (contains all of 7)
            5: 'abdfg',  // 5 (is contained by 6)
            2: 'acdeg',  // 5 (what's left)
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
        Map<Integer, String> answers = [:]

        segments.each { k, v ->
            def found = input.findAll { it.size() == v.size() }.collect { it as List }
            List answer = []
            if (found.size() == 1) {
                answer = found[0]
            } else {
                switch (k) {
                    case 9:
                        answer = found.find { it.containsAll(answers.get(4) as List) }
                        break
                    case 0:
                    case 3:
                        answer = found.find { it.containsAll(answers.get(7) as List) }
                        break
                    case 5:
                        answer = found.find { (answers.get(6) as List).containsAll(it) }
                        break
                }
            }
            answers.put(k, answer.join())
            input.remove(answers.get(k))
        }

        return answers.collectEntries { k, v -> [(v): k] }
    }

    static long encode(Map<String, Integer> answers, List<String> output) {
        output.collect { answers.get(it) }.join() as long
    }
}




