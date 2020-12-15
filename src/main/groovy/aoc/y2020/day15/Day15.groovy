package aoc.y2020.day15

class Day15 {
    static part1(String inputString, int target) {
        Integer[] input = inputString.trim().tokenize(',').collect { Integer.parseInt(it) }
        def spoken = input.clone().toList()
        for (i in input.size()..<target) {
            def current = spoken[i - 1]
            def last = spoken.reverse().indexOf(current)
            def next = 0
            if (last >= 0) {
                def previous = spoken.reverse().findIndexOf(last + 1, { it == current })
                if (previous > 0) {
                    next = previous - last
                }
            }
            spoken << next
        }
        return spoken[-1]
    }

    static part2(String inputString, int target) {
        Integer[] input = inputString.trim().tokenize(',').collect { Integer.parseInt(it) }
        Map<Integer, Pair> spoken = [:]
        int inputSize = input.size()
        for (i in 0..<inputSize) {
            spoken.put(input[i], new Pair().push(i + 1))
        }
        int current
        int next = input[-1]
        Pair p
        for (i in inputSize..<target) {
            current = next
            next = 0
            p = spoken.get(current)
            if (p) {
                if (p.i2) {
                    next = i - p.i2
                }
            }
            spoken.put(next, spoken.getOrDefault(next, new Pair()).push(i + 1))
        }
        return next
    }

    static class Pair {
        int i1
        int i2

        Pair push(int i) {
            i2 = i1
            i1 = i
            return this
        }
    }
}
