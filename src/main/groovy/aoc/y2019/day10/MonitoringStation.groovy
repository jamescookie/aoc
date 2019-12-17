package aoc.y2019.day10

import java.awt.*

import static java.lang.Math.PI

class MonitoringStation {
    def space
    def max
    def location

    MonitoringStation(String input) {
        Point.metaClass.distanceFrom << { Point p -> Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2)) }
        space = input.split().collect { it.collect { asteroid -> (asteroid == '#') } }
        max = [input.size(), input[0].size()].max()
    }

    def vaporise(int howMany) {
        howMany -= 1
        def totalAsteroids = space.flatten().findAll { it }.size()
        assert totalAsteroids >= howMany
        assert best() >= howMany
        def myTans = myTans(location) //19,11
        def seen = myTans.clone().flatten()
        seen.removeAll { it == null }
        def unique = seen.clone().unique().sort() //253
        def sorted =
                unique.findAll { it < 0 && it >= -(PI / 2) } +
                        unique.findAll { it <= PI / 2 && it >= 0 } +
                        unique.findAll { it <= PI && it > PI / 2 } +
                        unique.findAll { it < -(PI / 2) && it >= -(PI) }

        def myTan = sorted[howMany]

//        showMap(myTans, location)
//        println "Laser at: " + location
//        sorted.eachWithIndex { it, i -> println "$i) ${closest(findPoints(myTans, it))} = $it" }

        return closest(findPoints(myTans, myTan))
    }

    private Point closest(points) {
        points.sort { it.distanceFrom(location) }[0]
    }

    private static findPoints(myTans, myTan) {
        def points = []
        for (int y = 0; y < myTans.size(); y++) {
            def row = myTans[y]
            for (int x = 0; x < row.size(); x++) {
                if (row[x] == myTan) {
                    points.add(new Point(x, y))
                }
            }
        }
        return points
    }

    private static void showMap(myTans, location) {
        for (int y = 0; y < myTans.size(); y++) {
            def row = myTans[y].clone()
            for (int x = 0; x < row.size(); x++) {
                if (location.x == y && location.y == x) {
                    row[x] = 'X'
                } else {
                    if (row[x] != null) {
                        row[x] = '#'
                    } else {
                        row[x] = '.'
                    }
                }
            }
            println row.join()
        }
    }

    int best() {
        def max = 0
        for (int y = 0; y < space.size(); y++) {
            def row = space[y].clone()
            for (int x = 0; x < row.size(); x++) {
                if (row[x] == true) {
                    row[x] = howManySeen(new Point(x, y)).unique().size()
                    if (row[x] > max) {
                        max = row[x]
                        location = new Point(x, y)
                    }
                }
            }
        }
        return max
    }

    def howManySeen(point) {
        def flatten = myTans(point).flatten()
        flatten.removeAll { it == null }
        return flatten
    }

    def myTans(point) {
        def seen = []
        for (int y = 0; y < space.size(); y++) {
            def row = space[y].clone()
            seen << row
            for (int x = 0; x < row.size(); x++) {
                if (row[x] == true) {
                    if (point.x == y && point.y == x) {
                        row[x] = null
                    } else {
                        row[x] = tanLine(point, new Point(x, y))
                    }
                } else {
                    row[x] = null
                }
            }
        }
        return seen
    }

    static Double tanLine(Point pointA, Point pointB) {
        def x = pointB.x - pointA.x
        def y = pointB.y - pointA.y
        return Math.atan2(y, x)
    }
}
