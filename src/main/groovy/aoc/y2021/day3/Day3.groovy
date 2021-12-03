package aoc.y2021.day3

class Day3 {
    static part1(String inputString) {
        def strings = inputString.tokenize()
        Integer[] input = strings.collect { Integer.parseInt(it, 2) }
        int size = strings.get(0).size()

        int gamma = findGamma(input, Math.pow(2.0, size - 1).intValue())
        int epsilon = Integer.parseInt(Integer.toBinaryString((int) ~gamma)[-size..-1], 2)
        return gamma * epsilon
    }

    static part2(String inputString) {
        def strings = inputString.tokenize()
        Integer[] input = strings.collect { Integer.parseInt(it, 2) }
        def start = Math.pow(2.0, strings.get(0).size() - 1).intValue()
        int oxygen = findResult(input, start) { a, b -> a >= b }
        int co2 = findResult(input, start) { a, b -> a < b }
        return oxygen * co2
    }

    static int findGamma(Integer[] input, int denominator) {
        def half = input.size() / 2
        Integer result = null
        while (denominator > 0) {
            def match = matchingBit(input, denominator).size() > half
            if (result) {
                result = result << 1
            }
            if (match) {
                result = result ? result + 1 : 1
            }
            denominator = denominator >> 1
        }
        result
    }

    static int findResult(Integer[] input, int denominator, comparator) {
        while (input.size() != 1) {
            def ones = matchingBit(input, denominator)
            def zeros = input - ones
            if (comparator(ones.size(), zeros.size())) {
                input = ones
            } else {
                input = zeros
            }
            denominator = denominator >> 1
        }
        input[0]
    }

    protected static Collection<Integer> matchingBit(Integer[] input, int denominator) {
        input.findAll { i -> (i & denominator) == denominator }
    }
}
