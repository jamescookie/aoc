package aoc.y2022.day6

class Day6 {
    static part1(String inputString) {
        return findDistinct(inputString, 4)
    }

    static part2(String inputString) {
        return findDistinct(inputString, 14)
    }

    static int findDistinct(String inputString, int packetSize) {
        def packet = []
        int count = 0
        for (i in 0..<inputString.length()) {
            count++
            if (packet.size() == packetSize) {
                packet.pop()
            }
            packet << inputString[i]
            if (packet.size() == packetSize) {
                if (allDifferent(packet)) {
                    break
                }
            }
        }
        count
    }

    static boolean allDifferent(List list) {
        def copy = new ArrayList(list)
        for (i in 0..<copy.size()) {
            def check = copy.pop()
            if (copy.contains(check)) return false
        }
        return true
    }
}
