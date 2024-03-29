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

    static Collection<Pair> generateCombinations(List<String> input) {
        HashSet<Pair> subsets = new HashSet<>();

        def half = Math.round(input.size() / 2).intValue()

        for (j in 0..<half) {
            subset(input, j).forEach { left ->
                subsets.add(new Pair(left, input - left));
            }
        }

        return subsets
    }

    static List<List<String>> subset(List<String> input, int howMany, List<String> soFar = [], int index = 0) {
        List<List<String>> result = []
        for (i in index..<input.size() - howMany) {
            if (howMany > 0) {
                result.addAll(subset(input, howMany - 1, soFar + input[i], i + 1))
            } else {
                result << soFar + input[i]
            }
        }
        return result
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
                return soFar + bestPaths.get(target)
            }
            List<String> result = null
            soFar << name
            if (connections.contains(target)) {
                soFar << target
                result = soFar
            } else {
                List<List<String>> validPaths = (connections - soFar)
                        .collect { all.get(it).bestPath(target, new ArrayList<String>(soFar), all) }
                        .findAll { it != null }
                if (validPaths.size() > 0) {
                    result = validPaths.min { it.size() }
                }
            }
            if (result != null) {
                bestPaths.put(target, new ArrayList<String>(result.subList(result.indexOf(name), result.size())))
            }
            return result
        }
    }
}
