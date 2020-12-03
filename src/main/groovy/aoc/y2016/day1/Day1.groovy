package aoc.y2016.day1

import aoc.Point

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

        return (Math.abs(mover.current.x) + Math.abs(mover.current.y))
    }

    static part2(String inputString) {
        String[] input = inputString.trim().tokenize(', ')

        def mover = new DirectionMover(currentDirection: "N", current: new Point(), record: true)
        input.each {mover.move(it)}

        return mover.dupe ? (Math.abs(mover.dupe.x) + Math.abs(mover.dupe.y)) : (Math.abs(mover.current.x) + Math.abs(mover.current.y))
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
            def newPoint = new Point(newX, newY)
            if (record) {
                addPoints(current, newPoint)
            }
            current = newPoint
            currentDirection = newDir
        }

        void addPoints(Point p1, Point p2) {
            addPastPoint(p1)
            Point.straightLinePointsBetween(p1, p2).each {addPastPoint(it)}
        }

        void addPastPoint(Point p) {
            if (dupe == null && pastPoints.contains(p)) {
                dupe = p
            }
            pastPoints.add(p)
        }
    }
}
