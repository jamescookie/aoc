package aoc.y2020.day23

class Day23 {
    static part1(String inputString, int moves) {
        int[] input = inputString.trim().collect {Integer.parseInt(it)}
        play(input, moves)
        return Long.parseLong(input.join().split('1').reverse().join())
    }

    static part2(String inputString) {
        int[] firstFew = inputString.trim().collect {Integer.parseInt(it)}
        int[] input = new int[1000000]
        for (int i = 0; i<input.size(); i++) {
            input[i] = i
        }
        for (i in 0..<firstFew.size()) {
            input[i] = firstFew[i]
        }
        play(input, 10000000)
        return 0
    }

    private static void play(int[] input, int moves) {
        def size = input.size()
        def index = 0
        for (i in 0..<moves) {
            def next = adjust(index + 1, size)
            def cannotBe = [input[next], input[adjust(next + 1, size)], input[adjust(next + 2, size)]]
            def destination = input[index] - 1 ?: size
            while (cannotBe.contains(destination)) {
                destination = destination - 1 ?: size
            }
            def to = input.findIndexOf { it == destination }
            for (j in 0..<3) {
                shuffle(input, next, to)
            }
            index = adjust(index + 1, size)
        }
    }

    static def shuffle(int[] input, int fromPos, int toPos) {
        def size = input.size()
        int current = input[fromPos]
        while (fromPos != toPos) {
            def next = adjust(fromPos + 1, size)
            input[fromPos] = input[next]
            fromPos = adjust(fromPos + 1, size)
        }
        input[toPos] = current
    }

    static int adjust(int what, int max) {
        return what >= max ? what - max : what
    }
}
