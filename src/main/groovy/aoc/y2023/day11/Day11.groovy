package aoc.y2023.day11

import aoc.Point

class Day11 {
    static part1(String s) {
        def input = new Input(s)
        long result = 0
        for (i in 0..<input.galaxies.size()) {
            Point galaxy1 = input.galaxies[i]
            for (j in 0..<input.galaxies.size()) {
                Point galaxy2 = input.galaxies[j]
                if (galaxy1 != galaxy2) {
                    def distance = Math.abs(galaxy1.x - galaxy2.x) + Math.abs(galaxy1.y - galaxy2.y)
                    result += distance
                }

            }
        }

        return result/2
    }

    static part2(String s) {
        return new Input(s).rows
                .inject(0) { a, b -> a + b.size() }
    }

    static class Input {
        List<List<String>> rows = []
        List<Point> galaxies = []

        Input(String s) {
            List<List<String>> rows = s.tokenize('\n').collect { it.toCharArray().collect {it.toString()} }
            for (i in 0..<rows.size()) {
                this.rows << rows[i]
                if (rows[i].every {it == '.'}) {
                    this.rows << rows[i]
                }
            }
            List<Integer> cols = []
            for (i in 0..<rows[0].size()) {
                if (rows*.get(i).every {it == '.'}) {
                    cols << i
                }
            }
            cols = cols.reverse()
            for (i in 0..<cols.size()) {
                this.rows.each {row->
                    row.add(cols[i], '.')
                }
            }
            for (x in 0..<this.rows.size()) {
                def row = this.rows[x]
                for (y in 0..<row.size()) {
                    if (this.rows[x][y] == '#') {
                        this.galaxies << new Point(x,y)
                    }
                }
            }
        }
    }
}
