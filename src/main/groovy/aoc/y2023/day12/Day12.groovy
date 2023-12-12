package aoc.y2023.day12

class Day12 {
    static part1(String s) {
        def input = new Input(s,1)
        return input.rows.collect { it.possibilities() }.sum()
    }

    static part2(String s) {
        def input = new Input(s,5)
        return input.rows.collect { it.possibilities() }.sum()
    }

    static class Input {
        List<Springs> rows

        Input(String s, int multiples) {
            rows = s.tokenize('\n').collect { new Springs(it, multiples) }
        }
    }

    static class Springs {
        public static final char UNKNOWN = ('?' as char)
        public static final char OPERATIONAL = ('.' as char)
        public static final char DAMAGED = ('#' as char)
        String map = ""
        List<Integer> records = []

        Springs(String s, int multiples) {
            def tokenize = s.tokenize(' ')
            def integers = tokenize[1].tokenize(',').collect { it as int }
            String sep = ''
            for (i in 0..<multiples) {
                map += sep + tokenize[0]
                records.addAll(integers)
                sep = UNKNOWN
            }
        }

        int possibilities() {
            def matches = checkMatches(map, 0)
            return matches
        }

        int checkMatches(String map, Integer start) {
            String regex = "^[.?]*" + records.collect {"[#?]{$it}"}.join('[.?]+') + "[.?]*\$"
            assert map ==~ regex
            char[] chars = map.toCharArray()
            int result = 0
            for (i in start..<chars.size()) {
                char next = chars[i]
                if (next == UNKNOWN) {
                    if (checkMatch(chars, i, regex, DAMAGED)) {
                        if (checkMatch(chars, i, regex, OPERATIONAL)) {
                            chars[i] = DAMAGED
                            result += checkMatches(String.valueOf(chars), i)
                            chars[i] = OPERATIONAL
                            result += checkMatches(String.valueOf(chars), i)
                            chars[i] = UNKNOWN
                            break
                        } else {
                            chars[i] = DAMAGED
                        }
                    } else {
                        chars[i] = OPERATIONAL
                    }
                }
            }
            result ?: 1
        }

        private static boolean checkMatch(char[] chars, int i, String regex, char replacement) {
            char previous = chars[i]
            chars[i] = replacement
            def result = String.valueOf(chars) ==~ regex
            chars[i] = previous
            return result
        }
    }
}
