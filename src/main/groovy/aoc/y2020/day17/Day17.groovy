package aoc.y2020.day17

class Day17 {
    static char ACTIVE = '#'

    static part1(String inputString) {
        Set<Point> points = pointsFromInput(inputString.tokenize().reverse() as String[])

        for (i in 0..<6) {
            points = newPoints(points)
        }

        return points.size()
    }

    static part2(String inputString) {
        Set<Point> points = pointsFromInput(inputString.tokenize().reverse() as String[])

        for (i in 0..<6) {
            points = newPoints2(points)
        }

        return points.size()
    }

    private static Set<Point> pointsFromInput(String[] input) {
        Set<Point> points = []
        for (i in 0..<input.size()) {
            for (j in 0..<input[i].size()) {
                if (input[i][j] == ACTIVE) {
                    points << new Point(j, i, 0, 0)
                }
            }
        }
        points
    }

    static Set<Point> newPoints(Set<Point> existing) {
        Set<Point> newPoints = new HashSet<>()
        Set<Point> allInactive = []
        for (i in 0..<existing.size()) {
            def touches = existing[i].touches(existing)
            if (touches == 2 || touches == 3) {
                newPoints.add(existing[i])
            }
            allInactive.addAll(existing[i].neighbours3d)
        }
        allInactive.removeAll(existing)
        for (i in 0..<allInactive.size()) {
            def p = allInactive[i]
            if (p.touches(existing) == 3) {
                newPoints.add(p)
            }
        }
        return newPoints
    }

    static Set<Point> newPoints2(Set<Point> existing) {
        Set<Point> newPoints = new HashSet<>()
        Set<Point> allInactive = []
        for (i in 0..<existing.size()) {
            def touches = existing[i].touches2(existing)
            if (touches == 2 || touches == 3) {
                newPoints.add(existing[i])
            }
            allInactive.addAll(existing[i].neighbours4d)
        }
        allInactive.removeAll(existing)
        for (i in 0..<allInactive.size()) {
            def p = allInactive[i]
            if (p.touches2(existing) == 3) {
                newPoints.add(p)
            }
        }
        return newPoints
    }

    static class Point {
        int x
        int y
        int z
        int w
        List<Point> neighbours3d
        List<Point> neighbours4d
        
        Point(int x, int y, int z, int w) {
            this.x = x
            this.y = y
            this.z = z
            this.w = w
        }

        List<Point> getNeighbours3d() {
            if (!neighbours3d) {
                neighbours3d = neighbours() - new Point(x,y,z,w)
            }
            return neighbours3d
        }

        List<Point> getNeighbours4d() {
            if (!neighbours4d) {
                neighbours4d = neighbours(w-1) + neighbours(w+1) + neighbours(w) - new Point(x,y,z,w)
            }
            return neighbours4d
        }

        int touches(Set<Point> existing) {
            return existing.findAll { getNeighbours3d().contains(it) }.size()
        }

        int touches2(Set<Point> existing) {
            return existing.findAll { getNeighbours4d().contains(it) }.size()
        }

        private List<Point> neighbours(int w = 0) {
            [
                    new Point(x - 1, y - 1, z, w),
                    new Point(x - 1, y, z, w),
                    new Point(x - 1, y + 1, z, w),
                    new Point(x, y - 1, z, w),
                    new Point(x, y, z, w),
                    new Point(x, y + 1, z, w),
                    new Point(x + 1, y - 1, z, w),
                    new Point(x + 1, y, z, w),
                    new Point(x + 1, y + 1, z, w),

                    new Point(x - 1, y - 1, z + 1, w),
                    new Point(x - 1, y, z + 1, w),
                    new Point(x - 1, y + 1, z + 1, w),
                    new Point(x, y - 1, z + 1, w),
                    new Point(x, y, z + 1, w),
                    new Point(x, y + 1, z + 1, w),
                    new Point(x + 1, y - 1, z + 1, w),
                    new Point(x + 1, y, z + 1, w),
                    new Point(x + 1, y + 1, z + 1, w),

                    new Point(x - 1, y - 1, z - 1, w),
                    new Point(x - 1, y, z - 1, w),
                    new Point(x - 1, y + 1, z - 1, w),
                    new Point(x, y - 1, z - 1, w),
                    new Point(x, y, z - 1, w),
                    new Point(x, y + 1, z - 1, w),
                    new Point(x + 1, y - 1, z - 1, w),
                    new Point(x + 1, y, z - 1, w),
                    new Point(x + 1, y + 1, z - 1, w)
            ]
        }

        boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point pt = (Point) obj
                return (x == pt.x) && (y == pt.y) && (z == pt.z) && (w == pt.w)
            }
            return super.equals(obj)
        }

        int hashCode() {
            (x?:1) * (z?:1) * (y?:1) * (w?:1)
        }

        String toString() {
            "$x, $y, $z, $w"
        }
    }
}
