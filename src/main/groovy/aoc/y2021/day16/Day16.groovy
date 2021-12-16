package aoc.y2021.day16

class Day16 {
    static part1(String inputString) {
        StringBuffer input = new StringBuffer(inputString.trim().collect {Integer.toBinaryString(Integer.parseInt(it, 16)).padLeft(4, '0')}.join())

        int versions = 0
        while(input.size()) {
            def version = Integer.parseInt(read(input, 3), 2)
            versions += version
            def type = Integer.parseInt(read(input, 3), 2)
            if (type == 4) {
                while (read(input, 1) == '1') {
                    read(input, 4)
                }
                read(input, 4)
            } else {
                if (read(input, 1) == '1') {
                    def subPackets = Integer.parseInt(read(input, 11), 2)
//                    read(input, subPackets * 11)
                } else {
                    def totalLength = Integer.parseInt(read(input, 15), 2)
//                    read(input, totalLength)
                }
            }
            if (!input.contains('1')) {
                input.delete(0, input.length())
            }
        }

        return versions
    }

    static String read(StringBuffer input, int amount) {
        String result = input.getAt(0..(amount-1))
        input.delete(0, amount)
        return result
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }
}
