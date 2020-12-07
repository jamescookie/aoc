package aoc.y2020.day7

class Day7 {
    static part1(String inputString, String type) {
        String[] input = inputString.tokenize('\n')
        def rules = input.collect { new Rule(it) }
        return recurse1(type, rules).size()
    }

    static part2(String inputString, String type) {
        String[] input = inputString.tokenize('\n')
        def rules = input.collect { new Rule(it) }
        return recurse2(type, rules)
    }

    static Set<String> recurse1(String type, List<Rule> rules) {
        def all = rules.findAll { it.containing.keySet().contains(type) }
        if (all) {
            Set<String> result = new HashSet<>(all*.type)
            all.each { result.addAll(recurse1(it.type, rules)) }
            return result
        } else {
            return []
        }
    }

    static long recurse2(String type, List<Rule> rules) {
        def rule = rules.find { r -> r.type == type }
        return rule.containing ? rule.containing.values().sum() + rule.containing.collect { recurse2(it.key, rules) * it.value }.sum() : 0
    }

    static class Rule {
        String type
        Map<String, Integer> containing = [:]

        Rule(String r) {
            def matcher = r =~ /(.*?) bag(?:s) contain (.*)/
            type = matcher[0][1]
            matcher = matcher[0][2] =~ /([\d|no]+) (.*?) bag[s]*[.,\s]*/
            matcher.each { if (it[1] != 'no') containing.put(it[2], Integer.parseInt("${it[1]}")) }
        }
    }
}
