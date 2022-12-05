package aoc.y2022.day3

class Day3 {
    static part1(String inputString) {
        def input = inputString.tokenize().collect {[it[0..it.length()/2 - 1], it[it.length()/2..-1]]}
        long score = 0
        input.each {backpack->
            char[] chars = backpack[0].toCharArray()
            for (int i = 0; i <chars.length; i++) {
                if (backpack[1].contains(""+ chars[i])) {
                    if (chars[i] == chars[i].toLowerCase()) {
                        score += (chars[i] - 96)
                    } else {
                        score += (chars[i] - 64) + 26
                    }
                    break
                }
            }
        }
        return score
    }

    static part2(String inputString) {
        def input = inputString.tokenize()
        long score = 0
        for (int i = 0; i <input.size(); i+=3) {
            def bp1 = input[i]
            def bp2 = input[i+1]
            def bp3 = input[i+2]
            char[] chars = bp1.toCharArray()
            for (int j = 0; j <chars.length; j++) {
                if (bp2.contains(""+chars[j]) && bp3.contains(""+chars[j])) {
                    if (chars[j] == chars[j].toLowerCase()) {
                        score += (chars[j] - 96)
                    } else {
                        score += (chars[j] - 64) + 26
                    }
                    break
                }
            }
        }

        return score
    }
}
