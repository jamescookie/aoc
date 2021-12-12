package aoc.y2021.day12

class Day12 {
    static final String START = 'start'
    static final String END = 'end'

    static part1(String inputString) {
        Set<Cave> caves = findCaves(inputString)
        def routes = []
        findRoute(caves.find { it.name == START }, [], routes) { Cave neighbour, List<Cave> soFar ->
            neighbour.big || !soFar.contains(neighbour)
        }
        return routes.size()
    }

    static part2(String inputString) {
        Set<Cave> caves = findCaves(inputString)
        def routes = []
        findRoute(caves.find { it.name == START }, [], routes) { Cave neighbour, List<Cave> soFar ->
            def visitedLittle = soFar.findAll { !it.big && it.name != START }.groupBy { it.name }
            if (visitedLittle.any { it.value.size() > 1 }) {
                return neighbour.big || !soFar.contains(neighbour)
            } else {
                return neighbour.name != START
            }
        }
        return routes.size()
    }

    static Set<Cave> findCaves(String inputString) {
        String[] input = inputString.tokenize()
        Set<Cave> caves = []
        input.each { s ->
            def ab = s.tokenize('-')
            Cave a = new Cave(ab[0])
            Cave b = new Cave(ab[1])
            if (!caves.contains(a)) {
                caves << a
            }
            if (!caves.contains(b)) {
                caves << b
            }
            a = caves.find { it.name == ab[0] }
            b = caves.find { it.name == ab[1] }
            a.neighbours << b
            b.neighbours << a
        }
        caves
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
