package aoc.y2021.day12

class Day12 {
    static final String START = 'start'
    static final String END = 'end'

    static part1(String inputString) {
        Set<Cave> caves = findCaves(inputString)
        def routes = []
        findRoute(caves.find { it.name == START }, [], routes) { Cave cave, List<Cave> soFar ->
            cave.big || !soFar.contains(cave)
        }
        return routes.size()
    }

    static part2(String inputString) {
        Set<Cave> caves = findCaves(inputString)
        def routes = []
        findRoute(caves.find { it.name == START }, [], routes) { Cave cave, List<Cave> soFar ->
            boolean alreadyBeenToSmallTwice = soFar
                    .findAll { !it.big }
                    .groupBy { it.name }
                    .any { it.value.size() > 1 }
            return alreadyBeenToSmallTwice ? cave.big || !soFar.contains(cave) : cave.name != START
        }
        return routes.size()
    }

    static Set<Cave> findCaves(String inputString) {
        String[] input = inputString.tokenize()
        Set<Cave> caves = []
        input.each { s ->
            List<Cave> twoNew = s.tokenize('-').collect { name ->
                caves.find { it.name == name } ?: new Cave(name)
            }
            caves.addAll(twoNew)
            twoNew[0].neighbours << twoNew[1]
            twoNew[1].neighbours << twoNew[0]
        }
        return caves
    }

    static findRoute(Cave current, List<Cave> soFar, routes, Closure acceptable) {
        soFar << current
        if (current.name == END) {
            routes << soFar
        } else {
            for (Cave neighbour in current.neighbours) {
                if (acceptable(neighbour, soFar)) {
                    findRoute(neighbour, new ArrayList<Cave>(soFar), routes, acceptable)
                }
            }
        }
    }

    static class Cave {
        String name
        Set<Cave> neighbours = []
        boolean big

        Cave(String name) {
            this.name = name
            this.big = name.toUpperCase() == this.name
        }

        @Override
        int hashCode() {
            return name.hashCode()
        }

        @Override
        boolean equals(Object obj) {
            if (obj instanceof Cave) {
                Cave c = (Cave) obj
                return name == c.name
            }
            return super.equals(obj)
        }
    }
}
