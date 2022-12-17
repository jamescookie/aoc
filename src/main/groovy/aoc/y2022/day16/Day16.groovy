package aoc.y2022.day16

import java.util.concurrent.atomic.AtomicLong

class Day16 {
    static part1(String inputString) {
        Map<String, Valve> valves = [:]
        inputString.split(System.lineSeparator()).each {new Valve(it, valves)}

        int max = 31
        AtomicLong best = new AtomicLong(0)
        findPressure(max, best, valves, [new Minute('AA', [])])
        return best.get()
    }

    protected static void findPressure(int max, AtomicLong best, Map<String, Valve> valves, List<Minute> current, String nextOpen = null) {
        List<String> currentlyOpen = new ArrayList<>(current[-1].open)
        List<String> targets = valves.values().findAll {it.shouldOpen}.collect {it.name} - currentlyOpen - [nextOpen]
        if (targets.size() == 0 || current.size() >= max) {
            long score = 0
            if (nextOpen != null) {
                current << new Minute(current[-1].current, currentlyOpen)
                currentlyOpen = new ArrayList<>(currentlyOpen + [nextOpen])
                current << new Minute(current[-1].current, currentlyOpen)
            }
            for (i in 1..<max) {//skip 'AA'
                def open
                if (current.size() <= i) {
                    open = currentlyOpen
                } else {
                    open = current[i].open
                }
                if (open) {
                    score += open.collect { valves.get(it).pressure }.sum() as long
                }
            }
            if (score > best.get()) {
                best.set(score)
            }
            return
        }
        if (nextOpen != null) {
            current << new Minute(current[-1].current, new ArrayList<String>(current[-1].open))
            currentlyOpen << nextOpen
        }
        for (i in 0..<targets.size()) {
            List<Minute> currentCopy = new ArrayList<Minute>(current)
            List<String> path = valves[currentCopy[-1].current].bestPath(targets[i], [], valves) - [currentCopy[-1].current]
            path.each {currentCopy << new Minute(it, new ArrayList<String>(currentlyOpen))}
            findPressure(max, best, valves, currentCopy, targets[i])
        }
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }
    //[['AA':[]],['DD':[]],['CC': ['DD']],['BB':['DD']],['BB':['DD']],['AA':['DD','BB']],]
    //DD 28 mins
    //BB 25 mins
    //JJ 20 mins

    static class Minute {
        String current
        List<String> open

        Minute(String c,List<String> o) {
            current = c
            open = o
        }

        String toString() {
            "['$current':$open]"
        }
    }

    static class Valve {
        List<String> connections = []
        String name
        boolean open = false
        boolean shouldOpen
        int pressure

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
            soFar << name
            if (connections.contains(target)) {
                soFar << target
                return soFar
            } else {
                def goodConnections = connections - soFar
                def paths = goodConnections.collect { all.get(it).bestPath(target,new ArrayList<String>(soFar), all) }
                def validPaths = paths.findAll {it != null}
                if (validPaths.size() == 0) {
                    return null
                } else {
                    return validPaths.min {it.size()}
                }
            }
        }
    }
}
