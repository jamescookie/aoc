package aoc.y2019.day12

import aoc.y2019.Utils;

class Jupiter {
    List<Moon> moons
    String initialState

    Jupiter(List<Moon> moons) {
        this.moons = moons
        this.initialState = toString()
    }

    def doSteps(int number) {
        number.times { step() }
        return this
    }

    def totalEnergy() {
        moons.collect { it.totalEnergy() }.sum()
    }

    def step() {
        moons.each { moon1 ->
            moons.each { moon2 ->
                if (moon1 != moon2) {
                    moon1.applyGravity(moon2)
                }
            }
        }
        moons.each { it.move() }
        return this
    }

    long findPreviousStep() {
        long counter = 1
        step()
        while (true) {
            if (moons.every { it.isRepeating() }) {
                counter = moons.collect {
                    Utils.lcm(Utils.lcm(it.xs.pattern.size(), it.ys.pattern.size()), it.zs.pattern.size())
                }.max()
                break
            }
            counter++
            step()
        }
        return counter
    }
    //part 2a 18 28 44 lcm = 2772
    //part 2b 2028 5898 4702 lcm = 4,686,774,924

    String toString() {
        moons.collect { it.toString() }.join(",")
    }
}
