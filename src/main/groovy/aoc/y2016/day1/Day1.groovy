package aoc.y2016.day1

import java.awt.Point

class Day1 {
    static def DIRECTION_MAPPINGS = [
            "NR": "E",
            "NL": "W",
            "SR": "W",
            "SL": "E",
            "ER": "S",
            "EL": "N",
            "WR": "N",
            "WL": "S"
    ]

    static part1(String inputString) {
        String[] input = inputString.trim().tokenize(', ')

        def mover = new DirectionMover(currentDirection: "N", current: new Point())
        input.each {mover.move(it)}

        return (Math.abs(mover.current.x) + Math.abs(mover.current.y)).intValue()
    }

    static part2(String inputString) {
        String[] input = inputString.trim().tokenize(', ')

        def mover = new DirectionMover(currentDirection: "N", current: new Point(), record: true)
        input.each {mover.move(it)}

        return mover.dupe ? (Math.abs(mover.dupe.x) + Math.abs(mover.dupe.y)).intValue() : (Math.abs(mover.current.x) + Math.abs(mover.current.y)).intValue()
    }

    static class DirectionMover {
        String currentDirection
        Point current
        Point dupe = null
        List<Point> pastPoints = []
        boolean record = false

        void move(String where) {
            def dir = where[0]
            def howFar = Integer.parseInt(where[1..-1])
            def newDir = DIRECTION_MAPPINGS.get(currentDirection+dir)
            def newX = current.x
            def newY = current.y
            switch (newDir) {
                case "N":
                    newY = newY + howFar
                    break;
                case "S":
                    newY = newY - howFar
                    break;
                case "E":
                    newX = newX + howFar
                    break;
                case "W":
                    newX = newX - howFar
                    break;
            }
            def newPoint = new Point(newX.intValue(), newY.intValue())
            if (record) {
                addPoints(current, newPoint)
            }
            current = newPoint
            currentDirection = newDir
        }

        void addPoints(Point p1, Point p2) {
            if (p1.x < p2.x) {
                for (int i = p1.x.intValue(); i < p2.x.intValue(); i++) {
                    addPastPoint(new Point(i, p1.y.intValue()))
                }
            } else {
                for (int i = p1.x.intValue(); i > p2.x.intValue(); i--) {
                    addPastPoint(new Point(i, p1.y.intValue()))
                }
            }
            if (p1.y < p2.y) {
                for (int i = p1.y.intValue(); i < p2.y.intValue(); i++) {
                    addPastPoint(new Point(p1.x.intValue(), i))
                }
            } else {
                for (int i = p1.y.intValue(); i > p2.y.intValue(); i--) {
                    addPastPoint(new Point(p1.x.intValue(), i))
                }
            }
        }

        void addPastPoint(Point p) {
            if (dupe == null && pastPoints.contains(p)) {
                dupe = p
            }
            pastPoints.add(p)
        }
    }
}
