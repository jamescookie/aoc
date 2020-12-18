package aoc.y2020.day16

class Day16 {
    static part1(String inputString) {
        String[] input = inputString.tokenize('\n')
        List<Rule> rules = []
        def otherTickets = []

        parse(input, rules, otherTickets)

        def result = 0
        for (i in 0..<otherTickets.size()) {
            def ticket = otherTickets[i]
            for (j in 0..<ticket.size()) {
                def value = ticket[j]
                def valid = false
                for (k in 0..<rules.size()) {
                    if (rules[k].contains(value)) {
                        valid = true
                        break
                    }
                }
                if (!valid) {
                    result += value
                }
            }
        }

        return result
    }

    private static def parse(String[] input, ArrayList<Rule> rules, ArrayList otherTickets) {
        def myTicket
        def part = 0
        for (i in 0..<input.size()) {
            if (input[i].contains("your ticket")) {
                part++
                continue
            }
            if (input[i].contains("nearby tickets")) {
                part++
                continue
            }
            if (part == 0) {
                rules << new Rule(input[i])
            } else if (part == 1) {
                myTicket = input[i].tokenize(',').collect { Integer.parseInt(it) }
            } else {
                otherTickets << input[i].tokenize(',').collect { Integer.parseInt(it) }
            }
        }
        return myTicket
    }

    static class Rule {
        def tmp
        def pos = []
        String name
        Range upper
        Range lower

        Rule(String s) {
            def matcher = s =~ /(.*?): (\d+)-(\d+) or (\d+)-(\d+)/
            name = matcher[0][1]
            lower = (Integer.parseInt(matcher[0][2])..Integer.parseInt(matcher[0][3]))
            upper = (Integer.parseInt(matcher[0][4])..Integer.parseInt(matcher[0][5]))
        }

        boolean contains(int i) {
            return upper.contains(i) || lower.contains(i)
        }
    }

    static part2(String inputString, String what) {
        String[] input = inputString.tokenize('\n')
        List<Rule> rules = []
        def otherTickets = []
        def myTicket = parse(input, rules, otherTickets)

        for (i in 0..<otherTickets[0].size()) {
            for (j in 0..<rules.size()) {
                def count = 0
                for (k in 0..<otherTickets.size()) {
                    if (rules[j].contains(otherTickets[k][i])) {
                        count++
                    }
                }
                rules[j].tmp = count
            }
            def max = rules*.tmp.max()
            rules.findAll { it.tmp == max }.each { it.pos << i }
        }

        def finished = false
        while (!finished) {
            for (i in 0..<rules.size()) {
                if (rules[i].pos.size() == 1) {
                    for (j in 0..<rules.size()) {
                        if (i != j) {
                            rules[j].pos.removeElement(rules[i].pos[0])
                        }
                    }
                }
            }
            finished = rules.every { it.pos.size() == 1 }
        }

        long result = 1
        rules.findAll { it.name.startsWith(what) }.collect { myTicket[it.pos[0]] }.each { result *= it }
        return result
    }
}
