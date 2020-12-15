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
        Map<Integer, Pair> spoken = new LinkedHashMap<>()
        int inputSize = input.size()
        for (i in 0..<inputSize) {
            spoken.put(input[i], new Pair(i + 1))
        }
        int next = input[-1]
        for (int i = inputSize; i <target; i++) {
            next = i - (spoken.get(next).i2 ?: i)
            if (spoken.containsKey(next)) {
                spoken.get(next).push(i + 1)
            } else {
                spoken.put(next, new Pair(i + 1))
            }
        }
        return next
    }

    static class Pair {
        int i1
        int i2

        Pair(int i) {
            i1 = i
        }

        Pair push(int i) {
            i2 = i1
            i1 = i
            return this
        }
    }
}
