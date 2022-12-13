package aoc.y2022.day13

class Day13 {
    static part1(String inputString) {
        def input = inputString.split('\n\n').collect { pairs -> pairs.split('\n').collect { Eval.me(it) } }
        int result = 0
        for (i in 0..<input.size()) {
            if (correctOrder(input[i][0], input[i][1]) != Result.INCORRECT) {
                def index = (i + 1)
                result += index
            }
        }
        return result
    }

    static part2(String inputString) {
        String divider1 = "[[2]]"
        String divider2 = "[[6]]"
        List<Object> input = (inputString+"\n"+divider1+"\n"+divider2).replaceAll('\n\n', '\n').split('\n').collect { pairs -> pairs.split('\n').collect { Eval.me(it) } }

        def sort = input.sort { a, b ->
            switch (correctOrder(a, b)) {
                case Result.CORRECT:
                    return -1
                case Result.EQUAL:
                    return 0
                case Result.INCORRECT:
                    return 1
            }
        }.collect { (it as String)[1..-2] }
        return (sort.indexOf(divider1) + 1) * (sort.indexOf(divider2) + 1)
    }

    static enum Result {
        CORRECT,
        INCORRECT,
        EQUAL
    }

    static Result correctOrder(List<Object> left, List<Object> right) {
        for (i in 0..<left.size()) {
            if (i == right.size()) {
                return Result.INCORRECT
            }
            if (left[i] instanceof Integer && right[i] instanceof Integer) {
                if (left[i] < right[i]) {
                    return Result.CORRECT
                } else if (left[i] > right[i]) {
                    return Result.INCORRECT
                }
            } else if (left[i] instanceof List && right[i] instanceof List) {
                def answer = correctOrder(left[i], right[i])
                if (answer != Result.EQUAL) {
                    return answer
                }
            } else {
                def answer = correctOrder(left[i] instanceof List ? left[i] : [left[i]], right[i] instanceof List ? right[i] : [right[i]])
                if (answer != Result.EQUAL) {
                    return answer
                }
            }
        }
        return left.size() == right.size() ? Result.EQUAL : Result.CORRECT
    }
}
