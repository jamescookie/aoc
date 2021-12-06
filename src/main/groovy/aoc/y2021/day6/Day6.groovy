package aoc.y2021.day6

class Day6 {
    static part1(String inputString, int days) {
        List<Integer> input = inputString.trim().tokenize(',').collect {Integer.parseInt(it)}
        def size = 0
        for (i in 0..<days) {
            input = input.collect {--it}
            size = input.size()
            int newOnes = input.findAll { it == 0 }.size()
            for (j in 0..<newOnes) {
                input.add(9)
            }
            input = input.collect {it == 0 ? 7 : it}
        }
        return size
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect {Integer.parseInt(it)}
        return null
    }
}
