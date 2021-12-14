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
        Map<String, Long> allChars = (new HashSet<>(inputString as List) - ['-', '>', ' ', '\n']).collectEntries { [(it), 0L] }
        String[] input = inputString.split('\n\n')
        List<Character> start = input[0] as List
        Map<String, List<String>> instructions = input[1].split('\n').collectEntries {
            def split = it.split(' -> ');
            [(split[0]): [split[0][0] + split[1], split[1] + split[0][1]]]
        }
        List<String> initial = []
        Map<String, Long> result = [:]
        for (int i = 0; i < start.size() - 1; i++) {
            String pair = "${start[i]}${start[i + 1]}"
            result.put(pair, result.getOrDefault(pair, 0) + 1)
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
        result.each {
            allChars.put(it.key[0], allChars.get(it.key[0]) + it.value)
        }
        allChars.put(input[0][-1], allChars.get(input[0][-1]) + 1)

        Long biggest = allChars.values().max()
        Long smallest = allChars.values().min()
        return biggest - smallest
    }

    static void findPairs(List<String> pairs, int steps, Map<String, Long> result, Map<String, List<String>> instructions) {
        for (String pair in pairs) {
            if (steps == 0) {
                result.put(pair, result.getOrDefault(pair, 0) + 1)
            } else {
                findPairs(instructions.get(pair), (steps - 1), result, instructions)
            }
        }
    }

}
