package aoc.y2022.day4

class Day4 {
    static part1(String inputString) {
        def input = inputString.tokenize().collect { pairs -> pairs.split(',').collect { range -> range.split('-').collect { it as int } }.collect { (it[0]..it[1]).asList() } }
        return input.findAll { it[0].containsAll(it[1]) || it[1].containsAll(it[0]) }.size()
    }

    static part2(String inputString) {
        def input = inputString.tokenize().collect { pairs -> pairs.split(',').collect { range -> range.split('-').collect { it as int } }.collect { (it[0]..it[1]).asList() } }
        return input.findAll { !it[0].intersect(it[1]).isEmpty() }.size()
    }
}
