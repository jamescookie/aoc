package aoc.y2023.day18

import aoc.Point

class Day18 {
    public static final Point NORTH = new Point(-1, 0)
    public static final Point WEST = new Point(0, -1)
    public static final Point SOUTH = new Point(1, 0)
    public static final Point EAST = new Point(0, 1)
    public static Map<String, Point> DIRECTIONS = [
            'R': EAST,
            'D': SOUTH,
            'L': WEST,
            'U': NORTH,
    ]

    static part1(String s) {
        def input = new Input(s, false)
        return stuff(input)

//
//        def start = new Point(0, 0)
//        def results = input.rows.inject([start]) { a, b -> a.addAll(b.apply(a[-1])); a }
//        assert results[0] == start
//        assert results[-1] == start
//        assert results.pop() == start
//        Point.print(results, false)
//        def inside = fillIn(results, new Point(1, 1))
//        Point.print(inside, false)
//        return results.size() + inside.size()
    }

    static part2(String s) {
        def input = new Input(s, true)
        return stuff(input)
    }

    private static long stuff(Input input) {
        List<Perimeter> downwardStack = []
        List<Perimeter> upwardStack = []
        int current = 0
        long result = 1
        def path = [new Point(0, 0)]
        def lastEastWest = null
        for (int i = 0; i < input.rows.size(); i++) {
            Instruction instruction = input.rows[i]
            path.addAll(instruction.apply(path[-1]))
            result += instruction.count
            switch (instruction.direction) {
                case SOUTH:
                    def remaining = instruction.count
//                    if (lastEastWest != input.rows[(i + 1 < input.rows.size() ? i + 1 : 0)].direction) {
//                        remaining++
//                    }
                    if (!upwardStack.isEmpty() && upwardStack[0].y < current) {
                        println(instruction)
                        println(path.size())
                        Point.print(path, false)
                        println('-----')
                        while (remaining > 0 && !upwardStack.isEmpty()) {
                            Perimeter lastUp = upwardStack.pop()
                            long diff
                            if (lastUp.count <= remaining) {
                                diff = lastUp.count
                                remaining -= diff
                            } else {
                                diff = remaining
                                lastUp.count = lastUp.count - remaining
                                upwardStack.push(lastUp)
                                remaining = 0
                            }
                            result += (Math.abs(current - lastUp.y)) * diff
                        }
                    }
                    if (remaining) {
                        downwardStack.push(new Perimeter(current, remaining))
                    }
                    break
                case EAST:
                    current += instruction.count
                    lastEastWest = EAST
//                    result += instruction.count
                    break
                case WEST:
                    current -= instruction.count
                    lastEastWest = WEST
                    break
                case NORTH:
                    def remaining = instruction.count
//                    if (lastEastWest != input.rows[(i + 1 < input.rows.size() ? i + 1 : 0)].direction) {
//                        remaining++
//                    }
                    if (!downwardStack.isEmpty() && downwardStack[0].y > current) {
                        println(instruction)
                        println(path.size())
                        Point.print(path, false)
                        println('-----')
                        while (remaining > 0 && !downwardStack.isEmpty()) {
                            Perimeter lastDown = downwardStack.pop()
                            long diff
                            if (lastDown.count <= remaining) {
                                diff = lastDown.count
                                remaining -= diff
                            } else {
                                diff = remaining
                                lastDown.count = lastDown.count - remaining
                                downwardStack.push(lastDown)
                                remaining = 0
                            }
                            result += (Math.abs(current - lastDown.y)) * diff
                        }
                    }
                    if (remaining) {
                        upwardStack.push(new Perimeter(current, remaining))
                    }
                    break
            }
        }
        return result
    }
//
//    static List<Point> fillIn(List<Point> points, Point start) {
//        def results = []
//        def stack = [start]
//        int maxX = (points*.x).max() as int
//        int maxY = (points*.y).max() as int
//        int minX = (points*.x).min() as int
//        int minY = (points*.y).min() as int
//
//        while (!stack.isEmpty()) {
//            Point next = stack.pop()
//            if (!results.contains(next)) {
//                results.add(next)
//                def neighbours = Point.neighbours(maxX, maxY, minX, minY, next)
//                for (Point neighbour in neighbours) {
//                    if (!points.contains(neighbour)) {
//                        stack.add(neighbour)
//                    }
//                }
//            }
//        }
//
//        return results
//    }

    static class Instruction {
        String original
        Point direction
        int count

        Instruction(String s, boolean useHex) {
            original = s
            def tokenize = s.tokenize(' ')
            if (useHex) {
                def colour = tokenize[2][2..-2]
                direction = DIRECTIONS.get(DIRECTIONS.keySet()[colour[-1] as int])
                count = Integer.decode('0x' + colour[0..-2])
            } else {
                direction = DIRECTIONS.get(tokenize[0])
                count = tokenize[1] as int
            }
        }

        List<Point> apply(Point p) {
            p = new Point(p)
            List<Point> results = []
            for (i in 0..<count) {
                results << p.translate(direction).clone()
            }
            return results
        }

        @Override
        String toString() {
            original
        }
    }

    static class Input {
        List<Instruction> rows

        Input(String s, boolean useHex) {
            rows = s.tokenize('\n').collect { new Instruction(it, useHex) }
        }
    }

    static class Perimeter {
        int y
        long count

        Perimeter(int y, long count) {
            this.y = y
            this.count = count
        }
    }

}
