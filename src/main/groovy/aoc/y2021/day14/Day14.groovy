package aoc.y2021.day14

class Day14 {
    static part1(String inputString, int steps) {
        String[] input = inputString.split('\n\n')
        List<Character> result = input[0] as List
        List<String[]> instructions = input[1].split('\n').collect { it.split(' -> ') }

        for (step in 0..<steps) {
            List<Character> newResult = []
            for (int i = 0; i < result.size(); i++) {
                newResult << (result[i] as Character)
                if (i + 1 < result.size()) {
                    def found = instructions.find { it[0] == "${result[i]}${result[i + 1]}" }
                    if (found) {
                        newResult << (found[1] as Character)
                    }
                }
            }
            result = newResult
        }

        def group = result.groupBy { it }
        Long biggest = group.inject(0L) { x, y -> x > y.value.size() ? x : y.value.size() }
        Long smallest = group.inject(Long.MAX_VALUE) { x, y -> x < y.value.size() ? x : y.value.size() }
        return biggest - smallest
    }

    static part2(String inputString, int steps) {
        String[] input = inputString.split('\n\n')
        String startingString = input[0]
        Map<String, List<String>> instructions = input[1].split('\n').collectEntries {
            def split = it.split(' -> ');
            [(split[0]): [split[0][0] + split[1], split[1] + split[0][1]]]
        }
        Map<String, Long> result = [:]
        for (int i = 0; i < startingString.size() - 1; i++) {
            String pair = "${startingString[i]}${startingString[i + 1]}"
            result.put(pair, result.getOrDefault(pair, 0L) + 1)
        }

        for (step in 0..<steps) {
            Map<String, Long> newResult = [:]
            result.each {entry->
                instructions.get(entry.key).each {
                    newResult.put(it, newResult.getOrDefault(it, 0L) + entry.value)
                }
            }
            result = newResult
        }

        Map<String, Long> allChars = [(startingString[-1]) : 1L]
        result.each {
            allChars.put(it.key[0], allChars.getOrDefault(it.key[0], 0) + it.value)
        }

        def values = allChars.values()
        return values.max() - values.min()
    }
}
