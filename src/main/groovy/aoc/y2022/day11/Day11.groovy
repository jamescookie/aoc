package aoc.y2022.day11

class Day11 {
    static part1(String inputString) {
        def monkeys = inputString.split('\n\n').collect { new Monkey(it, true) }
        for (i in 0..<20) {
            for (j in 0..<monkeys.size()) {
                monkeys[j].takeTurn(monkeys)
            }
        }
        def sorted = monkeys.sort { a, b -> b.inspected <=> a.inspected }
        return sorted[0].inspected * sorted[1].inspected
    }

    static part2(String inputString, amount) {
        def monkeys = inputString.split('\n\n').collect { new Monkey(it, false) }
        for (i in 0..<amount) {
            for (j in 0..<monkeys.size()) {
                monkeys[j].takeTurn(monkeys)
            }
        }
        def sorted = monkeys.sort { a, b -> b.inspected <=> a.inspected }
        return sorted[0].inspected * sorted[1].inspected
    }

    static class Monkey {
        List<Long> items
        Object[] operation
        Closure<Long> test
        int prime
        long inspected = 0
        boolean divide

        Monkey(String input, boolean divide) {
            def split = input.split('\n')
            items = split[1].split(':')[1].split(',').collect { it.trim() as long }
            def tmp = (split[2].split(':')[1].trim() - 'new = old ').split(' ')
            operation = [tmp[0] == '*', tmp[1] == 'old' ? 'old' : Integer.parseInt(tmp[1])]
            prime = split[3].split(' by ')[1] as int
            int monkey1 = split[4].split(' monkey ')[1] as int
            int monkey2 = split[5].split(' monkey ')[1] as int
            test = { value -> (value % prime == 0) ? monkey1 : monkey2 }
            this.divide = divide
        }

        void takeTurn(List<Monkey> monkeys) {
            while (items.size() > 0) {
                inspected++
                long item = items.pop()
                item = execute(item)
                if (divide) {
                    item = (item / 3).longValue()
                } else {
                    item = item % (monkeys*.prime.inject(1) { result, i -> result * i })
                }
                monkeys[test(item)].receive(item)
            }
        }

        void receive(long next) {
            items << next
        }

        private long execute(long value) {
            if (operation[0]) {
                if (operation[1] == 'old') {
                    return value * value
                } else {
                    return value * (operation[1])
                }
            } else {
                if (operation[1] == 'old') {
                    return value + value
                } else {
                    return value + (operation[1])
                }
            }
        }
    }
}
