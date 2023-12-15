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
            def matches = checkMatches(map, 0, new ArrayList<>(records), [:])
            println "Got $matches for $map in ${System.currentTimeMillis() - start}"
            return matches
        }

        static int checkMatches(String map, Integer start, List<Integer> records, Map<String, Map<List<Integer>, Integer>> cache) {
            for (i in 0..<records.size()) {
                int record = records[0]
                def matcher = map =~ "^([.]*[#]{$record})[.].*"
                if (matcher.find()) {
                    def removed = matcher[0][1].size()
                    map = map.substring(removed)
                    if (start) start -= removed
                    records.pop()
                } else {
                    break
                }
            }
            def matcher = map =~ "^([.]+).*"
            if (matcher.find()) {
                def removed = matcher[0][1].size()
                map = map.substring(removed)
                start -= removed
            }
            if (cache.containsKey(map)) {
                if (cache.get(map).containsKey(records)) {
                    return cache.get(map).get(records)
                }
            }
            if (start < 0) start = 0
            String regex = "^[.?]*" + records.collect {"[#?]{$it}"}.join('[.?]+') + "[.?]*\$"
            assert map ==~ regex
            char[] chars = map.toCharArray()
            int result = 0
            for (i in start..<chars.size()) {
                char next = chars[i]
                if (next == UNKNOWN) {
                    chars[i] = DAMAGED
                    if (String.valueOf(chars) ==~ regex) {
                        chars[i] = OPERATIONAL
                        if (String.valueOf(chars) ==~ regex) {
                            result += checkMatches(String.valueOf(chars), i + 1, new ArrayList<>(records), cache)
                            chars[i] = DAMAGED
                            result += checkMatches(String.valueOf(chars), i + 1, new ArrayList<>(records), cache)
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
            if (!cache.containsKey(map)) {
                cache.put(map, [:])
            }
            def finalResult = result ?: 1
            cache.get(map).put(records, finalResult)
            finalResult
        }
    }
}
