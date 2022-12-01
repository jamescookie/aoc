package aoc.y2022.day1

class Day1 {
    static part1(String inputString) {
        def input = inputString.split('\n\n')*.split('\n')*.collect {it as int}*.sum()
        return input.max()
    }

    static part2(String inputString) {
        def input = inputString.split('\n\n')*.split('\n')*.collect {it as int}*.sum()
        return input.sort().reverse()[0..2].sum()
    }
}
