package aoc.y2020.day14

class Day14 {

    public static final String X = 'X'

    static part1(String inputString) {
        String[] input = inputString.tokenize("\n")
        def mask = ""
        def mem = [:]
        for (i in 0..<input.size()) {
            if (input[i].startsWith("mask")) {
                mask = input[i] -= "mask = "
            } else {
                def split = input[i].split(" = ")
                split[0] -= "mem["
                split[0] -= "]"
                mem[Integer.parseInt(split[0])] = Long.parseLong(split[1]) & Long.parseLong(mask.replaceAll(X, '1'), 2)
                mem[Integer.parseInt(split[0])] = mem[Integer.parseInt(split[0])] | Long.parseLong(mask.replaceAll(X, '0'), 2)
            }
        }
        return mem.values().sum()
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize("\n")
        def mask = ""
        def mem = [:]
        for (i in 0..<input.size()) {
            if (input[i].startsWith("mask")) {
                mask = input[i] -= "mask = "
            } else {
                def split = input[i].split(" = ")
                split[0] -= "mem["
                split[0] -= "]"
                long current = Long.parseLong(split[0]) | Long.parseLong(mask.replaceAll(X, '0'), 2)
                findMems(current, mask).each {mem[it] = Long.parseLong(split[1])}
            }
        }
        return mem.values().sum()
    }

    static Set<Long> findMems(long current, String mask) {
        def binary = Long.toBinaryString(current).padLeft(mask.size(), '0').toCharArray()
        for (i in 0..<mask.size()) {
            if (mask[i] == X) {
                binary[i] = X
            }
        }
        return replaceIt(binary.toString()).collect {Long.parseLong(it, 2)}
    }

    static List<String> replaceIt(String mask) {
        List<String> result = []
        if (mask.contains(X)) {
            result.addAll(replaceIt(mask.replaceFirst(X, '0')))
            result.addAll(replaceIt(mask.replaceFirst(X, '1')))
        } else {
            result.add(mask)
        }
        return result
    }
}
