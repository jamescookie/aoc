package aoc.y2019.day12;

class Jupiter {
    List<Moon> moons
    String initialState

    Jupiter(List<Moon> moons) {
        this.moons = moons
        this.initialState = toString()
    }

    def doSteps(int number) {
        number.times {step()}
        return this
    }

    def totalEnergy() {
        moons.collect {it.totalEnergy()}.sum()
    }

    def step() {
        moons.each {moon1 ->
            moons.each {moon2 ->
                if (moon1 != moon2) {
                    moon1.applyGravity(moon2)
                }
            }
        }
        moons.each {it.move()}
        return this
    }

    long findPreviousStep() {
        long counter = 1
        step()
        while (toString() != initialState) {
            counter++
            step()
        }
        return counter
    }

    String toString() {
        moons.collect {it.toString()}.join(",")
    }
}
