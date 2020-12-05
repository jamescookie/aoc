package aoc.y2020.day5

class Day5 {
    static part1(String inputString) {
        String[] input = inputString.tokenize()
        return input.collect {BinarySearch.search(it)}.max()
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize()
        def sorted = input.collect { BinarySearch.search(it) }.sort()
        def answer = sorted[0]
        for (i in 0..<sorted.size()) {
            if (answer++ != sorted[i]) {
                break
            }
        }
        return answer - 1
    }

    static class BinarySearch {
        static long search(String input) {
            String rows = input[-3..-1]
            String cols = input - rows
            cols = cols.replaceAll('B', '1').replaceAll('F', '0')
            rows = rows.replaceAll('R', '1').replaceAll('L', '0')
            return Integer.parseInt(cols, 2) * 8 + Integer.parseInt(rows, 2)
        }
    }
}
