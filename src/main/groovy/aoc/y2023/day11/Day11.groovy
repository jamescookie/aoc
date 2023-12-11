package aoc.y2023.day11

import aoc.Point

class Day11 {
    static part1(String s) {
        return new Input(s, 2).result
    }

    static part2(String s, int expansion) {
        return new Input(s, expansion).result
    }

    static class Input {
        List<List<String>> rows = []
        List<Integer> emptyRows = []
        List<Integer> emptyCols = []
        List<Point> galaxies = []
        long result = 0

        Input(String s, int expansion) {
            expansion -= 1
            List<List<String>> input = s.tokenize('\n').collect { it.toCharArray().collect { it.toString() } }
            for (i in 0..<input.size()) {
                rows << input[i]
                if (input[i].every { it == '.' }) {
                    this.emptyRows << i
                }
            }
            for (i in 0..<input[0].size()) {
                if (input*.get(i).every { it == '.' }) {
                    emptyCols << i
                }
            }
            for (x in 0..<rows.size()) {
                def row = rows[x]
                for (y in 0..<row.size()) {
                    if (rows[x][y] == '#') {
                        galaxies << new Point(x, y)
                    }
                }
            }
            for (i in 0..<galaxies.size()) {
                Point galaxy1 = galaxies[i]
                for (j in 0..<galaxies.size()) {
                    Point galaxy2 = galaxies[j]
                    if (galaxy1 != galaxy2) {
                        def yPoints = Point.pointsBetweenIncludingLast(galaxy1, new Point(galaxy1.x, galaxy2.y))
                        def xPoints = Point.pointsBetweenIncludingLast(galaxy1, new Point(galaxy2.x, galaxy1.y))
                        def extras = yPoints.findAll { emptyCols.contains(it.y) }.size() +
                                xPoints.findAll { emptyRows.contains(it.x) }.size()
                        result += yPoints.size() + xPoints.size() + (extras * expansion)
                    }
                }
            }
            result = result / 2
        }
    }
}
