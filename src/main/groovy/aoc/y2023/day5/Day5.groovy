package aoc.y2023.day5

class Day5 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')
        List<Long> seeds = input.remove(0).tokenize(':')[1].tokenize().collect { it as long }
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

        List<Long> locations = seeds.collect { seed ->
            for (List<Entry> thing : almanac) {
                for (Entry entry : thing) {
                    def newSeed = entry.apply(seed)
                    if (newSeed != seed) {
                        seed = newSeed
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
        List<Long> seeds = []
        for (int i = 0; i < seedRanges.size(); i++) {
            for (j in (seedRanges[i])..<(seedRanges[i] + seedRanges[i+1])) {
                seeds << j
            }
            i++
        }

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

        List<Long> locations = seeds.collect { seed ->
            for (List<Entry> thing : almanac) {
                for (Entry entry : thing) {
                    def newSeed = entry.apply(seed)
                    if (newSeed != seed) {
                        seed = newSeed
                        break
                    }
                }
            }
            seed
        }

        return locations.min()
    }

    static class Entry {
        private long destination
        private long source
        private long range
        private long diff

        Entry(String s) {
            def tokenize = s.tokenize().collect { it as long }
            destination = tokenize[0]
            source = tokenize[1]
            range = tokenize[2]
            diff = destination - source
        }

        long apply(long seed) {
            if (seed >= source && seed <= (source + range)) {
                seed += diff
            }
            seed
        }
    }
}
