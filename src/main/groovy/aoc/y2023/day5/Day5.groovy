package aoc.y2023.day5

class Day5 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')
        List<Long> seeds = input.remove(0).tokenize(':')[1].tokenize().collect { it as long }
        List<List<Entry>> almanac = createAlmanac(input)

        List<Long> locations = seeds.collect { seed ->
            for (List<Entry> entries : almanac) {
                for (Entry entry : entries) {
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
        for (int i = 0; i < seedRanges.size(); i += 2) {
            def ranges = [new BigRange(seedRanges[i], seedRanges[i] + seedRanges[i + 1] - 1)]
            for (List<Entry> entries : almanac) {
                def newRanges = []
                for (BigRange range : ranges) {
                    newRanges.addAll(findRanges(entries, [range]))
                }
                ranges = newRanges
            }
            def min = ranges.collect { it.from }.min()
            if (min < result) {
                result = min
            }
        }

        return result
    }

    protected static List<BigRange> findRanges(List<Entry> entries, List<BigRange> currentRanges) {
        List<BigRange> result = []
        for (Entry entry : entries) {
            List<BigRange> newCurrentRanges = []
            for (BigRange currentRange : currentRanges) {
                if (currentRange.overlaps(entry.sourceRange)) {
                    if (currentRange.from < entry.sourceRange.from) {
                        newCurrentRanges << new BigRange(currentRange.from, entry.sourceRange.from - 1)
                    }
                    if (currentRange.to > entry.sourceRange.to) {
                        newCurrentRanges << new BigRange(entry.sourceRange.to + 1, currentRange.to)
                    }
                    result << entry.sourceRange.intersect(currentRange).transpose(entry.diff)
                } else {
                    newCurrentRanges << currentRange
                }
            }
            currentRanges = newCurrentRanges
        }
        result.addAll(currentRanges)
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

    static class BigRange implements Comparable<BigRange> {
        long from
        long to

        BigRange(String s) {
            def tokenize = s.tokenize('-')
            this.from = tokenize[0] as long
            this.to = tokenize[1] as long
        }

        BigRange(long from, long to) {
            this.from = from
            this.to = to
        }

        boolean overlaps(BigRange other) {
            if (this.from <= other.from) {
                if (this.to >= other.from) {
                    return true
                }
            } else {
                if (this.to <= other.to || this.from <= other.to) {
                    return true
                }
            }
            return false
        }

        BigRange intersect(BigRange other) {
            return new BigRange([this.from, other.from].max(), [this.to, other.to].min())
        }

        BigRange transpose(long diff) {
            return new BigRange(this.from + diff, this.to + diff)
        }

        @Override
        String toString() {
            return "$from-$to"
        }

        @Override
        int compareTo(BigRange o) {
            return this.from <=> o.from
        }
    }

    static class Entry {
        private long diff
        private long start
        private long end
        private BigRange sourceRange

        Entry(String s) {
            def tokenize = s.tokenize().collect { it as long }
            start = tokenize[1]
            diff = tokenize[0] - start
            end = start + tokenize[2] - 1
            sourceRange = new BigRange(start, end)
        }
    }
}
