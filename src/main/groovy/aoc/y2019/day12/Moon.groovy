package aoc.y2019.day12

class Moon {
    int x
    int y
    int z
    Velocity v = new Velocity()
    RepeatingPattern xs = new RepeatingPattern()
    RepeatingPattern ys = new RepeatingPattern()
    RepeatingPattern zs = new RepeatingPattern()
    boolean recording

    Moon(x, y, z, recording = true) {
        this.x = x
        this.y = y
        this.z = z
        this.recording = recording
        recordMove()
    }

    def applyGravity(Moon moon) {
        v.x += moon.x > x ? 1 : moon.x < x ? -1 : 0
        v.y += moon.y > y ? 1 : moon.y < y ? -1 : 0
        v.z += moon.z > z ? 1 : moon.z < z ? -1 : 0
    }

    def move() {
        x += v.x
        y += v.y
        z += v.z
        recordMove()
    }

    def recordMove() {
        if (recording) {
            xs.push(x)
            ys.push(y)
            zs.push(z)
        }
    }

    def isRepeating() {
        return xs.isRepeating && ys.isRepeating && zs.isRepeating
    }

    def potentialEnergy() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z)
    }

    def kineticEnergy() {
        return Math.abs(v.x) + Math.abs(v.y) + Math.abs(v.z)
    }

    def totalEnergy() {
        potentialEnergy() * kineticEnergy()
    }

    String toString() {
        "x: $x, y: $y, z: $z, vel: $v"
    }
}
