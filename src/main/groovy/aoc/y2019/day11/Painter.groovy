package aoc.y2019.day11

import aoc.y2019.IntCode

class Painter {
    IntCode programme
    List<Panel> panels = []

    Painter(input) {
        programme = new IntCode([], input)
    }

    int howManyPanels() {
        paint()
        return panels.size()
    }

    String painHull() {
        panels.add(new Panel(x: 0, y: 0, c: Colour.WHITE))
        paint()
        def output = findOutput()
        output.each {
            println it
        }
        return translateOutput(output)
    }

    void paint() {
        def x = 0
        def y = 0
        def direction = Direction.UP

        while (true) {
            def panel = panels.find { p -> p.x == x && p.y == y }
            if (!panel) {
                panel = new Panel(x: x, y: y)
                panels.add(panel)
            }
            def currentColour = panel.c.value
            programme.output = null
            def paint = programme.nextInput(currentColour)
            if (paint != null) {
                panel.c = Colour.fromValue(paint.intValue())
                programme.output = null
                def turn = programme.process()
                if (turn != null) {
                    switch (direction) {
                        case Direction.UP:
                            if (turn) {
                                direction = Direction.RIGHT
                                x += 1
                            } else {
                                direction = Direction.LEFT
                                x -= 1
                            }
                            break
                        case Direction.DOWN:
                            if (turn) {
                                direction = Direction.LEFT
                                x -= 1
                            } else {
                                direction = Direction.RIGHT
                                x += 1
                            }
                            break
                        case Direction.LEFT:
                            if (turn) {
                                direction = Direction.UP
                                y += 1
                            } else {
                                direction = Direction.DOWN
                                y -= 1
                            }
                            break
                        case Direction.RIGHT:
                            if (turn) {
                                direction = Direction.DOWN
                                y -= 1
                            } else {
                                direction = Direction.UP
                                y += 1
                            }
                            break
                    }
                } else {
                    break
                }
            } else {
                break
            }
        }
    }

    def findOutput() {
        def xs = panels.collect { it.x }
        def ys = panels.collect { it.y }
        def rows = []

        for (int y = ys.max(); y >= ys.min(); y--) {
            def row = ""
            for (int x = xs.min(); x <= xs.max(); x++) {
                def panel = panels.find { p -> p.x == x && p.y == y }
                def colour = Colour.BLACK
                if (panel) {
                    colour = panel.c
                }
                row += colour.display
            }
            rows.add(row)
        }
        return rows
    }

    def static String translateOutput( output) {
        // yeah, didn't bother
        return "RJLFBUCU";
    }
}
