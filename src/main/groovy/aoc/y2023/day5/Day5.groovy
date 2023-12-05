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
            def ranges = [new BigRange(seedRanges[i], seedRanges[i] + seedRanges[i + 1] - 1)]
            for (List<Entry> thing : almanac) {
                def newRanges = []
                for (BigRange range : ranges) {
                    newRanges.addAll(findRanges(thing, [range]))
                }
                ranges = newRanges
            }
            i++
            def min = ranges.collect{it.from}.min()
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

    protected static BigRange findSeedRange(List<List<Entry>> almanac, BigRange currentRange) {
        def sorted = almanac.pop().sort()
        BigRange result
        long previous = 0

        if (currentRange) {
            for (int i = 0; i < sorted.size(); i++) {
                def entry = sorted[i]
                long max = (i == (sorted.size() - 1)) ? Long.MAX_VALUE : (sorted[i + 1].destination - 1)
                result = new BigRange(previous, max)
                if (result.overlaps(currentRange)) {
                    break
                } else {
                    previous = max
                }
            }
        }
        if (almanac) {
            result = findSeedRange(almanac, result)
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
        boolean equals(Object o) {
            return o instanceof BigRange && this.from == o.from && this.to == o.to
        }

        @Override
        int compareTo(BigRange o) {
            return this.from <=> o.from
        }
    }

    static class Entry implements Comparable<Entry> {
        private long zero
        private long one
        private long two
        private long destination
        private long source
        private long range
        private long diff
        private long start
        private long end
        private BigRange sourceRange

        Entry(String s) {
            def tokenize = s.tokenize().collect { it as long }
            destination = tokenize[0]
            zero = destination
            source = tokenize[1]
            one = source
            this.range = tokenize[2]
            two = this.range
            start = source
            end = source + this.range
            diff = destination - source
            sourceRange = new BigRange(source, source + range - 1)
        }

        String toString() {
            return "$destination $source ${this.range}"
        }

        @Override
        int compareTo(Entry o) {
            (destination + diff) <=> (o.destination + o.diff)
        }
    }
}
