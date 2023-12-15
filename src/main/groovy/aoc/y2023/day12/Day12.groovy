package aoc.y2023.day12

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.regex.Matcher

class Day12 {
    static part1(String s) {
        return new Input(s, 1).rows.collect { it.possibilities() }.sum()
    }

    static part2(String s) {
        def rows = new Input(s, 5).rows
        def threadPool = Executors.newFixedThreadPool(rows.size())
        try {
            return rows.collect {
                threadPool.submit({ -> it.possibilities() } as Callable)
            }.collect { it.get() }.sum()
        } finally {
            threadPool.shutdown()
        }
    }

    static class Input {
        List<Springs> rows

        Input(String s, int multiples) {
            this.rows = s.tokenize('\n').collect { new Springs(it, multiples) }
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

        long possibilities() {
            long start = System.currentTimeMillis()
            def matches = checkMatches(map, 0, new ArrayList<>(records), [:])
            println "Got $matches for $map in ${System.currentTimeMillis() - start}"
            return matches
        }

        static long checkMatches(String map, Integer start, List<Integer> records, Map<String, Map<List<Integer>, Long>> cache) {
            Matcher matcher
            int removed
            for (i in 0..<records.size()) {
                matcher = map =~ '^([.]*[#]{' + records[0] + '})[.].*'
                if (matcher.find()) {
                    removed = matcher[0][1].size()
                    map = map.substring(removed)
                    start -= removed
                    records.pop()
                } else {
                    break
                }
            }
            matcher = map =~ /^([.]+).*/
            if (matcher.find()) {
                removed = matcher[0][1].size()
                map = map.substring(removed)
                start -= removed
            }
            if (!cache.containsKey(map)) {
                cache.put(map, [:])
            }
            if (cache.get(map).containsKey(records)) {
                return cache.get(map).get(records)
            }
            if (start < 0) start = 0
            String regex = '^[.?]*' + records.collect { "[#?]{$it}" }.join('[.?]+') + '[.?]*$'
            char[] chars = map.toCharArray()
            long result = 0
            for (i in start..<chars.size()) {
                if (chars[i] == UNKNOWN) {
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
            def finalResult = result ?: 1
            cache.get(map).put(records, finalResult)
            finalResult
        }
    }
}
