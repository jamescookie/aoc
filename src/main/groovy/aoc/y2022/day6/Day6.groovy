package aoc.y2022.day6

class Day6 {
    static part1(String inputString) {
        int count = 0
        def packet = []
        for (i in 0..<inputString.length()) {
            count++
            if (packet.size() == 4) {
                packet.remove(3)
            }
            packet.push(inputString[i])
            if (packet.size() == 4) {
                if (allDifferent(packet)) {
                    break
                }
            }
        }
        return count
    }

    static part2(String inputString) {
        int count = 0
        def packet = []
        for (i in 0..<inputString.length()) {
            count++
            if (packet.size() == 14) {
                packet.remove(13)
            }
            packet.push(inputString[i])
            if (packet.size() == 14) {
                if (allDifferent(packet)) {
                    break
                }
            }
        }
        return count
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
