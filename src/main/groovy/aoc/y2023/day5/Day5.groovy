package aoc.y2023.day5

class Day5 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')
        List<Long> seeds = input.remove(0).tokenize(':')[1].tokenize().collect { it as long }
        List<List<Entry>> almanac = createAlmanac(input)

        List<Long> locations = seeds.collect { seed ->
            for (List<Entry> thing : almanac) {
                for (Entry entry : thing) {
                    if (seed >= entry.start && seed <= entry.end) {
                        seed += entry.diff
                        break
                    }
                }
            }
            seed
        }

        return locations.min()
    }

    static part2(String inputString) {
        def input = inputString.tokenize('\n')
        List<Long> seedRanges = input.remove(0).tokenize(':')[1].tokenize().collect { it as long }
        List<List<Entry>> almanac = createAlmanac(input)

        long result = Long.MAX_VALUE;
        for (int i = 0; i < seedRanges.size(); i++) {
            for (seed in (seedRanges[i])..<(seedRanges[i] + seedRanges[i+1])) {
                for (List<Entry> thing : almanac) {
                    for (Entry entry : thing) {
                        if (seed >= entry.start && seed <= entry.end) {
                            seed += entry.diff
                            break
                        }
                    }
                }
                if (seed < result) result = seed
            }
            i++
        }

        return result
    }

    protected static List<List<Entry>> createAlmanac(List<String> input) {
        List<List<Entry>> almanac = []
        List<Entry> current = []
        for (String line : input) {
            if (line) {
                if (line.endsWith(':')) {
                    current = []
                    almanac << current
                } else {
                    current << new Entry(line)
                }
            }
        }
        almanac
    }

    static class Entry {
        private long destination
        private long source
        private long range
        private long diff
        private long start
        private long end

        Entry(String s) {
            def tokenize = s.tokenize().collect { it as long }
            destination = tokenize[0]
            source = tokenize[1]
            range = tokenize[2]
            start = source
            end = source + range
            diff = destination - source
        }

        String toString() {
            return "$destination $source $range"
        }
    }
}
