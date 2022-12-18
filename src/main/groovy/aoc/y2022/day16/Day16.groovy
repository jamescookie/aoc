package aoc.y2022.day16


import java.util.concurrent.atomic.AtomicLong

class Day16 {
    static part1(String inputString) {
        Map<String, Valve> valves = [:]
        inputString.split(System.lineSeparator()).each { new Valve(it, valves) }

        int max = 31
        AtomicLong best = new AtomicLong(0)
        List<String> allowedValves = valves.values().findAll { it.shouldOpen }.collect { it.name }
        findPressure(max, best, valves, allowedValves, [new Minute('AA', 0)], [])
        return best.get()
    }

    static part2(String inputString) {
        Map<String, Valve> valves = [:]
        inputString.split(System.lineSeparator()).each { new Valve(it, valves) }

        AtomicLong best = new AtomicLong(0)
        findPressureWithTwo(27, best, valves)

        return best.get()
    }

    protected static void findPressureWithTwo(int max, AtomicLong best, Map<String, Valve> valves) {
        //split up targets
        //combine scores
        List<String> allowedValves = valves.values().findAll { it.shouldOpen }.collect { it.name }
        Collection<Pair> combinations = generateCombinations(allowedValves)

        combinations.each {
            AtomicLong best1 = new AtomicLong(0)
            AtomicLong best2 = new AtomicLong(0)
            findPressure(max, best1, valves, it.a, [new Minute('AA', 0)], [])
            findPressure(max, best2, valves, it.b, [new Minute('AA', 0)], [])
            long score = best1.get() + best2.get()
            if (score > best.get()) {
                best.set(score)
            }
        }
    }

    //needs more work
    static Collection<Pair> generateCombinations(List<String> input) {
        HashSet<Pair> subsets = new HashSet<>();

        int half = input.size() / 2
        int[] s = new int[half]


        for (j in 0..<half) {
            for (i in 0..<input.size()) {
                getSubset(input, i, j + 1).forEach {left->
                    subsets.add(new Pair(left, input - left));
                }
            }
        }
//
//        if (half <= input.size()) {
//            // first index sequence: 0, 1, 2, ...
//            for (int x = 0; (s[x] = x) < half - 1; x++);
//            List<String> left = getSubset(input, s)
//            for (; ;) {
//                int i;
//                // find position of item that can be incremented
//                for (i = half - 1; i >= 0 && s[i] == input.size() - half + i; i--);
//                if (i < 0) {
//                    break;
//                }
//                s[i]++;                    // increment this item
//                for (++i; i < half; i++) {    // fill up remaining items
//                    s[i] = s[i - 1] + 1;
//                }
//                subsets.add(new Pair(left, getSubset(input, s)));
//            }
//        }

        return subsets
    }

    static List<List<String>> getSubset(List<String> input, int i, int howMany) {
        List<List<String>> result = []
        if (i+howMany > input.size()) return result
        result << input[i..<i+howMany]
//        for (j in 1..<howMany+1) {
//        }
        return result;
    }

    static class Pair {
        List<String> a
        List<String> b

        Pair(List<String> a, List<String> b) {
            this.a = a
            this.b = b
        }

        String toString() {
            "[$a, $b]"
        }

        boolean equals(Object obj) {
            if (obj instanceof Pair) {
                Pair pt = (Pair) obj
                return ((a == pt.a) && (b == pt.b)) || ((b == pt.a) && (a == pt.b))
            }
            return super.equals(obj)
        }

        int hashCode() {
            return a.hashCode() * b.hashCode()
        }
    }

    protected static void findPressure(int max, AtomicLong best, Map<String, Valve> valves, List<String> allowedValves, List<Minute> minutes, List<String> currentlyOpen, String nextOpen = null) {
        long currentScore = minutes[-1].score
        List<String> targets = allowedValves - currentlyOpen - [nextOpen]
        if (targets.size() == 0 || minutes.size() >= max) {
            if (nextOpen != null) {
                currentScore += (valves.get(nextOpen).pressure * (max - minutes.size() - 1))
            }
            long score
            if (minutes.size() >= max) {
                score = minutes[max - 1].score
            } else {
                score = currentScore
            }
            if (score > best.get()) {
                best.set(score)
            }
            return
        }
        if (nextOpen != null) {
            minutes << new Minute(minutes[-1].valve, currentScore)
            currentScore += (valves.get(nextOpen).pressure * (max - minutes.size()))
            currentlyOpen << nextOpen
        }
        for (i in 0..<targets.size()) {
            List<Minute> currentCopy = new ArrayList<Minute>(minutes)
            List<String> path = valves[currentCopy[-1].valve].bestPath(targets[i], [], valves) - [currentCopy[-1].valve]
            path.each { currentCopy << new Minute(it, currentScore) }
            findPressure(max, best, valves, allowedValves, currentCopy, new ArrayList<String>(currentlyOpen), targets[i])
        }
    }

    static class Minute {
        String valve
        long score

        Minute(String c, long o) {
            valve = c
            score = o
        }

        String toString() {
            "['$valve':$score]"
        }
    }

    static class Valve {
        List<String> connections = []
        String name
        boolean shouldOpen
        int pressure
        Map<String, List<String>> bestPaths = [:]

        Valve(String input, Map<String, Valve> all) {
            //Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
            def split = input.split('; ')
            def split1 = (split[0] - 'Valve ').split(' has flow rate=')
            name = split1[0]
            pressure = split1[1] as int
            connections = ((split[1] - 'tunnels lead to valves ') - 'tunnel leads to valve ').split(', ')
            all.put(name, this)
            shouldOpen = pressure != 0
        }

        List<String> bestPath(String target, List<String> soFar, Map<String, Valve> all) {
            if (bestPaths.containsKey(target)) {
                return bestPaths.get(target)
            }
            def path = internalBestPath(target, soFar, all)
            saveBest(target, path)
            return path
        }

        List<String> internalBestPath(String target, List<String> soFar, Map<String, Valve> all) {
            soFar << name
            if (connections.contains(target)) {
                soFar << target
                return soFar
            } else {
                def goodConnections = connections - soFar
                def paths = goodConnections.collect { all.get(it).internalBestPath(target, new ArrayList<String>(soFar), all) }
                def validPaths = paths.findAll { it != null }
                if (validPaths.size() == 0) {
                    return null
                } else {
                    def best = validPaths.min { it.size() }
                    return best
                }
            }
        }

        protected List<String> saveBest(String target, List<String> soFar) {
            if (soFar[0] == name) {
                bestPaths.put(target, new ArrayList<String>(soFar))
            }
        }
    }
}
