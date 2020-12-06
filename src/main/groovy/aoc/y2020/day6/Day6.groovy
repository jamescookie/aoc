package aoc.y2020.day6

class Day6 {
    static part1(String inputString) {
        String[] input = inputString.split(/\n\n/)
        return input.collect {it.tokenize().join()}.collect {it.toCharArray() as Set}.collect{it.size()}.sum()
    }

    static part2(String inputString) {
        String[] input = inputString.split(/\n\n/)

        return input.collect {it.tokenize()}.collect{count(it)}.sum()
    }

    static count(List<String> input) {
        int amount = 0
        def array = input[0].toCharArray()
        for (i in 0..<array.size()) {
            if (input.findAll{it.contains(""+array[i])}.size() == input.size()) {
                amount++
            }
        }
        return amount
    }
}
