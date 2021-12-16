package aoc.y2021.day16

class Day16 {
    static part1(String inputString) {
        StringBuffer input = new StringBuffer(inputString.trim().collect { Integer.toBinaryString(Integer.parseInt(it, 16)).padLeft(4, '0') }.join())
        int versions = 0
        readPacket(input, { versions += it })
        return versions
    }

    static part2(String inputString) {
        StringBuffer input = new StringBuffer(inputString.trim().collect { Integer.toBinaryString(Integer.parseInt(it, 16)).padLeft(4, '0') }.join())
        return readPacket(input, {}).sum()
    }

    static List<Long> readPacket(StringBuffer input, Closure checkVersion, Closure stopCondition = { StringBuffer remaining, count -> remaining.length() == 0 || !remaining.toString().contains('1') }) {
        List<Long> results = []
        int count = 0
        while (true) {
            count++
            def version = Integer.parseInt(read(input, 3), 2)
            checkVersion(version)
            def type = Integer.parseInt(read(input, 3), 2)
            List<Long> subResults = []
            if (type != 4) {
                if (read(input, 1) == '1') {
                    def numberOfSubPackets = Integer.parseInt(read(input, 11), 2)
                    subResults = readPacket(input, checkVersion, { a, b -> b == numberOfSubPackets })
                } else {
                    subResults = readPacket(new StringBuffer(read(input, Integer.parseInt(read(input, 15), 2))), checkVersion, { a, b -> a.length() == 0 || !a.toString().contains('1') })
                }
            }
            switch (type) {
                case 0:
                    results << (subResults.sum() as long)
                    break
                case 1:
                    results << subResults.inject { a, b -> a * b }
                    break
                case 2:
                    results << subResults.min()
                    break
                case 3:
                    results << subResults.max()
                    break
                case 4:
                    String result = ""
                    while (read(input, 1) == '1') {
                        result += read(input, 4)
                    }
                    result += read(input, 4)
                    results << Long.parseLong(result, 2)
                    break
                case 5:
                    results << (subResults[0] > subResults[1] ? 1L : 0L)
                    break
                case 6:
                    results << (subResults[0] < subResults[1] ? 1L : 0L)
                    break
                case 7:
                    results << (subResults[0] == subResults[1] ? 1L : 0L)
                    break
                default:
                    break
            }
            if (stopCondition(input, count)) {
                break
            }
        }
        return results
    }

    static String read(StringBuffer input, int amount) {
        String result = input.getAt(0..(amount - 1))
        input.delete(0, amount)
        return result
    }
}
