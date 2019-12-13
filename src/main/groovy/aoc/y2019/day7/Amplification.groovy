package aoc.y2019.day7

import aoc.y2019.IntCode

class Amplification {

    static def findCombinations = { phases ->
        [phases, phases, phases, phases, phases].combinations().findAll { a, b, c, d, e ->
            ![b, c, d, e].contains(a) &&
                    ![a, c, d, e].contains(b) &&
                    ![a, b, d, e].contains(c) &&
                    ![a, b, c, e].contains(d) &&
                    ![a, b, c, d].contains(e)
        }
    }

    static def partOne = { combinations, prog ->
        combinations.collect { combo ->
            def next = 0
            for (int i = 0; i < combo.size(); i++) {
                next = new IntCode([combo[i], next], prog.clone()).process()
            }
            next
        }.max()
    }

    static def partTwo = { combinations, prog ->
        combinations.collect { combo ->
            def next = 0
            combo = combo.collect { new IntCode(it, prog.clone()) }
            for (int i = 0; i < combo.size(); i++) {
                next = combo[i].nextInput(next)
                if (i + 1 == combo.size() && combo[i].state != IntCode.State.FINISHED) {
                    i = -1
                }
            }
            next
        }.max()
    }


}
