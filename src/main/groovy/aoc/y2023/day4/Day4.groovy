package aoc.y2023.day4

class Day4 {
    static part1(String inputString) {
        return findIntersects(inputString).collect { it -> it ? (1 << (it - 1)) : 0 }.sum()
    }

    static part2(String inputString) {
        List<List<Integer>> results = findIntersects(inputString).collect { [it, 1] }
        for (i in 0..<results.size()) {
            for (j in 1..<(results[i][0] + 1)) {
                results[i + j][1] = results[i + j][1] + results[i][1]
            }
        }
        return results.collect { it[1] }.sum()
    }

    protected static List<Integer> findIntersects(String inputString) {
        inputString.tokenize('\n')*.tokenize(':')*.last()*.tokenize('|').collect {
            it[0].tokenize().intersect(it[1].tokenize()).size()
        }
    }
}
