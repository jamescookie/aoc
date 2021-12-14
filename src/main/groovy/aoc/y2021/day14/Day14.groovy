package aoc.y2021.day14

class Day14 {
    static part1(String inputString, int steps) {
        String[] input = inputString.split('\n\n')
        List<Character> result = input[0] as List
        List<String[]> instructions = input[1].split('\n').collect {it.split(' -> ')}

        for (step in 0..<steps) {
            List<Character> newResult = []
            for(int i = 0; i< result.size(); i++) {
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
        Map<Character, Long> allChars = (new HashSet<>(inputString as List) - ['-', '>', ' ', '\n']).collectEntries {[(it), 0L]}
        String[] input = inputString.split('\n\n')
        List<Character> result = input[0] as List
        List<String[]> instructions = input[1].split('\n').collect {it.split(' -> ')}
        allChars.each {entry->entry.value = result.findAll {it == entry.key}.size()}

        for(int i = 0; i< result.size(); i++) {
            if (i + 1 < result.size()) {
                for (step in 0..<steps) {

                }
            }
        }

        Long biggest = allChars.values().max()
        Long smallest = allChars.values().min()
        return biggest - smallest
    }

}
