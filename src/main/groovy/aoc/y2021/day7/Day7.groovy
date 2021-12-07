package aoc.y2021.day7

class Day7 {
    static part1(String inputString) {
        return findEnergy(inputString) { it }
    }

    static part2(String inputString) {
        return findEnergy(inputString) { Long it -> addUp(it) }
    }

    protected static long findEnergy(String inputString, Closure<Long> fn) {
        Long[] input = inputString.trim().tokenize(',').collect { Long.parseLong(it) }
        long least = Long.MAX_VALUE
        for (i in 0..<input.max()) {
            long current = input.collect { fn(Math.abs(it - i)) }.sum() as long
            if (current < least) least = current
        }
        return least
    }

    static long addUp(long n) {
        if (n <= 1) {
            return n;
        }
        return n + addUp(n - 1);
    }
}
