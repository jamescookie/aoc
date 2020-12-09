package aoc.y2020.day9

class Day9 {
    static part1(String inputString, int preambleLength) {
        Long[] input = inputString.tokenize().collect {Long.parseLong(it)}
        for (int i = preambleLength; i < input.size(); i++) {
            def target = input[i]
            def findIn = (i-preambleLength..i-1).collect {input[it]}.sort()
            for (j in 0..<findIn.size()) {
                def pop = findIn.pop()
                if (findIn.contains(target - pop)) {
                    break
                }
            }
            if (findIn.isEmpty()) {
                return target
            }
        }
        return 0
    }

    static part2(String inputString, int preambleLength) {
        Long[] input = inputString.tokenize().collect {Long.parseLong(it)}
        def target = part1(inputString, preambleLength)
        for (int i = 0; i < input.size(); i++) {
            def end = find(target, input[i..-1], 0)
            if (end) {
                def longs = input[i..(i + end)]
                return longs.min() + longs.max()
            }
        }
        return null
    }

    static int find(long target, def input, int depth) {
        for (int i = 0; i < input.size(); i++) {
            def newTarget = target - input[i]
            if (newTarget == 0) {
                return depth
            } else if (newTarget < 0) {
                return 0
            } else {
                return find(newTarget, input[1..-1], ++depth)
            }
        }
    }
}
