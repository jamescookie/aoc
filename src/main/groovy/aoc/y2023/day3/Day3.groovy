package aoc.y2023.day3

import aoc.Point

class Day3 {

    public static final String PART = '*'

    static part1(String inputString) {
        inputString = inputString.replaceAll(/[^0-9.\n]/, PART)
        def rows = inputString.tokenize('\n')
        long result = 0;
        String currentNumber = ""
        boolean touching = false
        for (x in 0..<rows.size()) {
            def row = rows[x]
            for (y in 0..<row.size()) {
                def place = row[y]
                if (place == '.' || place == PART) {
                    if (currentNumber && touching) {
                        result += (currentNumber as int)
                    }
                    currentNumber = ""
                    touching = false
                } else {
                    currentNumber += place
                }
                touching = currentNumber && (touching || Point.neighboursWithDiagonals(rows, new Point(x, y)).any { p -> rows[p.x][p.y] == PART })
            }
            if (currentNumber && touching) {
                result += (currentNumber as int)
            }
            currentNumber = ""
            touching = false
        }
        if (currentNumber && touching) {
            result += (currentNumber as int)
        }
        return result
    }

    static part2(String inputString) {
        def rows = inputString.tokenize('\n')
        long result = 0;
        for (x in 0..<rows.size()) {
            def row = rows[x]
            for (y in 0..<row.size()) {
                def place = row[y]
                if (place == PART) {
                    def all = Point.neighboursWithDiagonals(rows, new Point(x, y)).findAll { p -> rows[p.x][p.y] ==~ /[0-9]/ }
                    Set<List<Point>> numbers = new HashSet(all.collect { findNumber(it, rows, [it]) })
                    if (numbers.size() == 2) {
                        def first = numbers.first().collect { rows[it.x][it.y] }.join('') as int
                        def last = numbers.last().collect { rows[it.x][it.y] }.join('') as int
                        result += first * last
                    }
                }
            }
        }
        return result
    }

    static List<Point> findNumber(point, rows, List<Point> found) {
        def all = Point.neighbours(rows, point).findAll { it.x == point.x && rows[it.x][it.y] ==~ /[0-9]/ }
        all.each { p ->
            if (!found.contains(p)) {
                found << p
                findNumber(p, rows, found)
            }
        }
        return found.sort()
    }
}
