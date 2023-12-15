package aoc.y2023.day12

class Day12 {
    static part1(String s) {
        def input = new Input(s, 1)
        return input.rows.collect { it.possibilities() }.sum()
    }

    static part2(String s) {
        def input = new Input(s, 5)
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
        Map<String, Map<Integer, Integer>> cache = [:]

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
            long start = System.currentTimeMillis()
            def matches = checkMatches(map.toCharArray(), 0, "^[.?]*" + records.collect { "[#?]{$it}" }.join('[.?]+') + "[.?]*\$")
            println "Got $matches for $map in ${System.currentTimeMillis() - start}"
            return matches
        }

        int checkMatches(char[] chars, Integer start, String regex) {
            int result = 0
            char next
            for (i in start..<chars.size()) {
                next = chars[i]
                if (next == UNKNOWN) {
                    chars[i] = DAMAGED
                    if (String.valueOf(chars) ==~ regex) {
                        chars[i] = OPERATIONAL
                        if (String.valueOf(chars) ==~ regex) {
                            result += checkMatches(chars, i, regex)
                            chars[i] = DAMAGED
                            result += checkMatches(chars, i, regex)
                            chars[i] = UNKNOWN
                            break
                        } else {
                            chars[i] = DAMAGED
                        }
                    } else {
                        chars[i] = OPERATIONAL
                    }
                    chars[i] = UNKNOWN
                }
            }
            return result ?: 1
        }
    }
}
