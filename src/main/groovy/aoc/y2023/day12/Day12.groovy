package aoc.y2023.day12

class Day12 {
    static part1(String s) {
        def input = new Input(s)
        return input.rows.collect { it.possibilities() }.sum()
    }

    static part2(String s) {
        return new Input(s).rows
                .inject(0) { a, b -> a + b.size() }
    }

    static class Input {
        List<Springs> rows

        Input(String s) {
            rows = s.tokenize('\n').collect { new Springs(it) }
        }
    }

    static class Springs {
        List<String> map
        List<Integer> records

        Springs(String s) {
            def tokenize = s.tokenize(' ')
            map = tokenize[0].tokenize('.')
            records = tokenize[1].tokenize(',')
        }

        int possibilities() {
            removeTruths()
            return 1
        }

        void removeTruths() {
            int i = 0
            for (j in 0..<map.size()) {
                String match = map[j]
                if (match.size() == records[i]) {

                }
            }

            for (final Iterator iterator = records.iterator(); iterator.hasNext();) {
                int next = iterator.next()
                String regex = "^[#?]{$next}\$".toString()
                if (someCondition) {
                    iterator.remove()
                }
                i++
            }
        }
    }
}
