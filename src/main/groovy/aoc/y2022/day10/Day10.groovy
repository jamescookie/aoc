package aoc.y2022.day10

import java.util.concurrent.atomic.AtomicInteger

class Day10 {
    static part1(String inputString) {
        def input = inputString.split('\n')
        long x = 1
        long cycle = 1
        AtomicInteger interest = new AtomicInteger(20)
        long value = 0
        for (i in 0..<input.size()) {
            value = checkCycle(cycle, x, interest, value)
            cycle++
            if (input[i].startsWith('addx')) {
                value = checkCycle(cycle, x, interest, value)
                int amount = Integer.parseInt(input[i] - 'addx ')
                x += amount
                cycle++
            }
        }
        return value
    }

    static part2(String inputString) {
        def input = inputString.split('\n')
        long x = 1
        long cycle = 1
        StringBuffer sb = new StringBuffer()
        for (i in 0..<input.size()) {
            printCycle(cycle, x, sb)
            cycle++
            if (input[i].startsWith('addx')) {
                printCycle(cycle, x, sb)
                int amount = Integer.parseInt(input[i] - 'addx ')
                x += amount
                cycle++
            }
        }
        print(sb.toString())
        return sb.toString()
    }

    static long checkCycle(long cycle, long x, AtomicInteger interest, long value) {
        if (cycle == interest.get()) {
            interest.set(interest.get() + 40)
            return value + (x * cycle)
        } else {
            return value
        }
    }

    static void printCycle(long cycle, long x, StringBuffer sb) {
        while (cycle > 40) {
            cycle -= 40
        }
        if (cycle == x || cycle == x + 1 || cycle == x + 2) {
            sb.append('#')
        } else {
            sb.append('.')
        }
        if (cycle == 40) {
            sb.append('\n')
        }
    }
}
