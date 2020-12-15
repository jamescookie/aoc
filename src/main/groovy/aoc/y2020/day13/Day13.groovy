package aoc.y2020.day13

class Day13 {
    static part1(String inputString) {
        String[] input = inputString.tokenize()
        def timestamp = Long.parseLong(input[0])
        def buses = input[1].tokenize(',')
                .findAll {it != 'x'}
                .collect {Integer.parseInt(it)}
        def smallest = 1000
        def bus
        for (i in 0..<buses.size()) {
            def mod = buses[i] - (timestamp % buses[i])
            if (mod < smallest) {
                smallest = mod
                bus = buses[i]
            }
        }
        return bus * smallest
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize()
        def buses = input[-1].tokenize(',').collect {it == 'x' ? 0 : Integer.parseInt(it)}
        def finished = false
        long answer = 0
        def count
        def target = buses.findAll {it}.size()
        while (!finished) {
            count = 1
            answer += buses[0]
            for (i in 1..<buses.size()) {
                if (buses[i]) {
                    if (buses[i] - (answer % buses[i]) == i) {
                        count++
                    }
                }
            }
            if (count == target) {
                finished = true
            }
        }

        return answer
    }

    static part2a(String inputString) {
        String[] input = inputString.tokenize()
        def buses = input[-1].tokenize(',').collect {it == 'x' ? 0 : Integer.parseInt(it)}
        def finished = false
        long answer = 0
        def all = buses.findAll { it }.sort().reverse()
        def target = all.size()
        def biggestIndex = buses.indexOf(all[0])

        while (!finished) {
            answer += all[0]
            finished = testAnswer(buses, answer - biggestIndex, target)
        }

        return answer - biggestIndex
    }

    private static boolean testAnswer(List<Integer> buses, long answer, int target) {
        def count = 0
        for (i in 0..<buses.size()) {
            if (i == 0) {
                if (answer % buses[i] == 0) {
                    count++
                } else {
                    return false
                }
            } else {
                if (buses[i]) {
                    if (buses[i] - (answer % buses[i]) == i) {
                        count++
                    } else {
                        return false
                    }
                }
            }
        }
        count == target
    }
}
