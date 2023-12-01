package aoc.y2023.day1

class Day1 {
    static def map = [
            'one': '1',
            'two': '2',
            'three': '3',
            'four': '4',
            'five': '5',
            'six': '6',
            'seven': '7',
            'eight': '8',
            'nine': '9',
    ]

    static part1(String inputString) {
        String[] input = inputString.tokenize('\n')
        def result = input.collect {line->
            line = line.replaceAll(/[^0-9]+/, '')
            return (line[0] + line[-1]) as int
        }.sum()
        return result
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize('\n')
        def result = input.collect {line ->
            String first = findFirst(line)
            String last = findLast(line)
            return (first + last) as int
        }.sum()
        return result
    }

    protected static String findFirst(String line) {
        for (i in 0..<line.size()) {
            String tmp = line[(i)..-1]
            for (def es in map) {
                if (tmp.startsWith(es.key) || tmp.startsWith(es.value)) {
                    return es.value
                }
            }
        }
        return null
    }

    protected static String findLast(String line) {
        for (i in 0..<line.size()) {
            String tmp = line[0..(-1-i)]
            for (def es in map) {
                if (tmp.endsWith(es.key) || tmp.endsWith(es.value)) {
                    return es.value
                }
            }
        }
        return null
    }
}
