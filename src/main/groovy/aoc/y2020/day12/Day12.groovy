package aoc.y2020.day12

import aoc.Point

class Day12 {
    static part1(String inputString) {
        String[] input = inputString.tokenize()
        def instructions = input.collect { new Ins(it) }
        def ship = new Ship()
        instructions.each { ship.move(it) }
        return Math.abs(ship.current.x) + Math.abs(ship.current.y)
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize()
        def instructions = input.collect { new Ins(it) }
        def ship = new Ship(part1: false)
        instructions.each { ship.move(it) }
        return Math.abs(ship.current.x) + Math.abs(ship.current.y)
    }

    static class Ship {
        boolean part1 = true
        char dir = 'E'
        Point current = new Point(0, 0)
        Point waypoint = new Point(10, 1)

        def move(Ins ins) {
            def point = part1 ? this.current : this.waypoint
            switch (ins.what) {
                case 'N':
                    point.y += ins.howMuch
                    break
                case 'E':
                    point.x += ins.howMuch
                    break
                case 'S':
                    point.y -= ins.howMuch
                    break
                case 'W':
                    point.x -= ins.howMuch
                    break
                case 'L':
                    if (part1) {
                        turn(false, ins.howMuch / 90)
                    } else {
                        transpose(false, ins.howMuch / 90)
                    }
                    break
                case 'R':
                    if (part1) {
                        turn(true, ins.howMuch / 90)
                    } else {
                        transpose(true, ins.howMuch / 90)
                    }
                    break
                case 'F':
                    if (part1) {
                        move(new Ins(what: dir, howMuch: ins.howMuch))
                    } else {
                        this.current.x += this.waypoint.x * ins.howMuch
                        this.current.y += this.waypoint.y * ins.howMuch
                    }
                    break
            }
        }

        def transpose(boolean clockwise, def times) {
            for (i in 0..<times) {
                if (clockwise) {
                    waypoint = new Point(waypoint.y, -waypoint.x)
                } else {
                    waypoint = new Point(-waypoint.y, waypoint.x)
                }
            }
        }

        def turn(boolean clockwise, def howMuch) {
            String dirs = "NESW"
            def index = dirs.indexOf("$dir")
            if (clockwise) {
                index += howMuch
                while (index > 3) {
                    index -= 4
                }
            } else {
                index -= howMuch
                while (index < 0) {
                    index += 4
                }
            }
            dir = dirs[index.intValue()]
        }
    }

    static class Ins {
        char what
        int howMuch

        Ins() {}

        Ins(String s) {
            what = s[0]
            howMuch = Integer.parseInt(s[1..-1])
        }

    }
}
