package aoc.y2023.day3

import aoc.Point

class Day3 {

    public static final String GEAR = '*'
    public static final String NUMBER = /[0-9]/

    static part1(String inputString) {
        inputString = inputString.replaceAll(/[^0-9.\n]/, GEAR)
        def rows = inputString.tokenize('\n')
        long result = 0
        String currentNumber = ""
        boolean touching = false
        for (x in 0..<rows.size()) {
            def row = rows[x]
            for (y in 0..<row.size()) {
                def place = row[y]
                if (place == '.' || place == GEAR) {
                    if (currentNumber && touching) {
                        result += (currentNumber as int)
                    }
                    currentNumber = ""
                    touching = false
                } else {
                    currentNumber += place
                }
                touching = currentNumber && (touching || Point.neighboursWithDiagonals(rows, new Point(x, y)).any { p -> rows[p.x][p.y] == GEAR })
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
        long result = 0
        for (x in 0..<rows.size()) {
            def row = rows[x]
            for (y in 0..<row.size()) {
                if (row[y] == GEAR) {
                    def touching = Point.neighboursWithDiagonals(rows, new Point(x, y)).findAll { p -> rows[p.x][p.y] ==~ NUMBER }
                    def numbers = new HashSet<>(touching.collect { findNumber(it, rows, [it]).sort() })
                    if (numbers.size() == 2) {
                        result += (numbers.first().collect { rows[it.x][it.y] }.join('') as int) *
                                (numbers.last().collect { rows[it.x][it.y] }.join('') as int)
                    }
                }
            }
        }
        return result
    }

    static List<Point> findNumber(point, rows, List<Point> found) {
        Point.neighbours(rows, point).findAll { it.x == point.x && rows[it.x][it.y] ==~ NUMBER }.each { p ->
            if (!found.contains(p)) {
                found << p
                findNumber(p, rows, found)
            }
        }
        return found
    }
}
