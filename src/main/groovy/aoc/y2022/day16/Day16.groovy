package aoc.y2022.day16

import java.util.concurrent.atomic.AtomicLong

class Day16 {
    static part1(String inputString) {
        Map<String, Valve> valves = [:]
        inputString.split(System.lineSeparator()).each {new Valve(it, valves)}

        int max = 31
        AtomicLong best = new AtomicLong(0)
        findPressure(max, best, valves, [new Minute('AA', 0)], [])
        return best.get()
    }

    // DD (560) BB (325) JJ (441) HH (286) EE (27) CC (12) = 1651
    protected static void findPressure(int max, AtomicLong best, Map<String, Valve> valves, List<Minute> minutes, List<String> currentlyOpen, String nextOpen = null) {
        long currentlyScore = minutes[-1].score
        List<String> targets = valves.values().findAll {it.shouldOpen}.collect {it.name} - currentlyOpen - [nextOpen]
        if (targets.size() == 0 || minutes.size() >= max) {
            if (nextOpen != null) {
                currentlyScore += (valves.get(nextOpen).pressure * (max - minutes.size() - 1))
            }
            long score
            if (minutes.size() >= max) {
                score = minutes[max-1].score
            } else {
                score = currentlyScore
            }
            if (score > best.get()) {
                best.set(score)
            }
            return
        }
        if (nextOpen != null) {
            minutes << new Minute(minutes[-1].valve, currentlyScore)
            currentlyScore += (valves.get(nextOpen).pressure * (max - minutes.size()))
            currentlyOpen << nextOpen
        }
        for (i in 0..<targets.size()) {
            List<Minute> currentCopy = new ArrayList<Minute>(minutes)
            List<String> path = valves[currentCopy[-1].valve].bestPath(targets[i], [], valves) - [currentCopy[-1].valve]
            path.each {currentCopy << new Minute(it, currentlyScore)}
            findPressure(max, best, valves, currentCopy, new ArrayList<String>(currentlyOpen), targets[i])
        }
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }
    //[['AA':[]],['DD':[]],['CC': ['DD']],['BB':['DD']],['BB':['DD']],['AA':['DD','BB']],]
    //DD 28 mins
    //BB 25 mins
    //JJ 21 mins

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
//            if (bestPaths.containsKey(target)) {
//                return bestPaths.get(target)
//            }
            soFar << name
            if (connections.contains(target)) {
                soFar << target
//                saveBest(target, soFar)
                return soFar
            } else {
                def goodConnections = connections - soFar
                def paths = goodConnections.collect { all.get(it).bestPath(target,new ArrayList<String>(soFar), all) }
                def validPaths = paths.findAll {it != null}
                if (validPaths.size() == 0) {
                    return null
                } else {
                    def best = validPaths.min { it.size() }
//                    saveBest(target, best)
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
