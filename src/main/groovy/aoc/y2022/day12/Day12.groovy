package aoc.y2022.day12

import aoc.Point

class Day12 {
    static part1(String inputString) {
        Character[][] input = inputString.tokenize().collect { it.toCharArray() }

        Point start = null
        Point end = null
        for (x in 0..<input.length) {
            def row = input[x]
            for (y in 0..<row.size()) {
                if (input[x][y] == 'S') {
                    input[x][y] = 'a'
                    start = new Point(x, y)
                } else if (input[x][y] == 'E') {
                    input[x][y] = 'z'
                    end = new Point(x, y)
                }
            }
        }

        return findPath(input, [[new Place(start, 'a' as char)]] as List<List<Place>>, new Place(end, 'z' as char))
    }

    static part2(String inputString) {
        Character[][] input = inputString.tokenize().collect { it.toCharArray() }

        List<Point> start = []
        Point end = null
        for (x in 0..<input.length) {
            def row = input[x]
            for (y in 0..<row.size()) {
                if (input[x][y] == 'S') {
                    input[x][y] = 'a'
                    start << new Point(x, y)
                } else if (input[x][y] == 'a') {
                    start << new Point(x, y)
                } else if (input[x][y] == 'E') {
                    input[x][y] = 'z'
                    end = new Point(x, y)
                }
            }
        }

        return start.collect { findPath(input, [[new Place(it, 'a' as char)]] as List<List<Place>>, new Place(end, 'z' as char)) }.min()
    }

    static int findPath(Character[][] input, List<List<Place>> current, Place end) {
        while (true) {
            Map<Place, List<Place>> placesToAdd = [:]
            current.forEach { potential ->
                Point.neighbours(input, potential[-1].point)
                        .findAll { input[it.x][it.y] - potential[-1].character < 2 }
                        .collect { new Place(it, input[it.x][it.y]) }
                        .findAll { !potential.contains(it) }
                        .forEach { placesToAdd.put(it, new ArrayList<>(potential)) }
            }
            if (!placesToAdd) {
                return Integer.MAX_VALUE
            }
            def best = placesToAdd.get(end)
            if (best) {
                return best.size()
            }
            Character bestChar = current.collect { it[-1].character }.max()
            def useThese = placesToAdd.findAll { k, v -> k.character > bestChar }
            if (useThese) {
                current = useThese.collect { k, v -> v << k }
            } else {
                current = placesToAdd.collect { k, v -> v << k }
            }
        }
    }

    static class Place {
        Point point
        Character character

        Place(Point p, Character c) {
            point = p;
            character = c
        }

        @Override
        int hashCode() {
            return point.x.hashCode() * point.y.hashCode()
        }

        @Override
        boolean equals(Object obj) {
            if (obj instanceof Place) {
                return (point.x == obj.point.x) && (point.y == obj.point.y)
            }
            return super.equals(obj)
        }
    }
}
