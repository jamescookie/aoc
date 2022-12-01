package aoc.y2022.day1

class Day1 {
    static part1(String inputString) {
        return splitToGroups(inputString).max()
    }

    static part2(String inputString) {
        return splitToGroups(inputString).sort().reverse()[0..2].sum()
    }

    protected static List<Long> splitToGroups(String inputString) {
        inputString.split('\n\n').collect { (Long) it.split('\n').collect { it as Long }.sum() }
    }
}
