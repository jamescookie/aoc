package aoc.y2019.day12;

class Jupiter {
    List<Moon> moons;

    Jupiter(List<Moon> moons) {
        this.moons = moons
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
}
