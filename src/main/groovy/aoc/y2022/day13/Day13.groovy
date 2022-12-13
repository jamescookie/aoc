package aoc.y2022.day13

class Day13 {
    static part1(String inputString) {
        List<List<List<Object>>> input = inputString.split(System.lineSeparator() + System.lineSeparator()).collect { pairs -> pairs.split(System.lineSeparator()).collect { Eval.me(it) as List<Object> } }
        int result = 0
        for (i in 0..<input.size()) {
            if (correctOrder(input[i][0], input[i][1]) != Result.INCORRECT) {
                result += (i + 1)
            }
        }
        return result
    }

    static part2(String inputString) {
        String divider1 = "[[2]]"
        String divider2 = "[[6]]"
        List<List<Object>> input = (inputString + System.lineSeparator() + divider1 + System.lineSeparator() + divider2).replaceAll(System.lineSeparator() + System.lineSeparator(), System.lineSeparator()).split(System.lineSeparator()).collect { pairs -> pairs.split(System.lineSeparator()).collect { Eval.me(it) } }

        def sort = input.sort { a, b -> correctOrder(b, a).value }.collect { (it as String)[1..-2] }
        return (sort.indexOf(divider1) + 1) * (sort.indexOf(divider2) + 1)
    }

    static enum Result {
        CORRECT(1),
        INCORRECT(-1),
        EQUAL(0);
        final int value;

        Result(value) {
            this.value = value
        }
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
                def answer = correctOrder((List<Object>) left[i], (List<Object>) right[i])
                if (answer != Result.EQUAL) {
                    return answer
                }
            } else {
                def answer = correctOrder(left[i] instanceof List ? (List<Object>) left[i] : [left[i]], right[i] instanceof List ? (List<Object>) right[i] : [right[i]])
                if (answer != Result.EQUAL) {
                    return answer
                }
            }
        }
        return left.size() == right.size() ? Result.EQUAL : Result.CORRECT
    }
}
