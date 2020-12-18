package aoc.y2020.day18

import java.util.regex.Pattern

class Day18 {

    public static final String PLUS = '+'

    static part1(String inputString) {
        String[] input = inputString.tokenize('\n')
        def result = 0
        for (i in 0..<input.size()) {
            result += calc(input[i], answerPart1)
        }
        return result
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize('\n')
        def result = 0
        for (i in 0..<input.size()) {
            result += calc(input[i], answerPart2)
        }
        return result
    }

    static long calc(String input, Closure answer) {
        def pattern = /\(([\d +\*]*)\)/
        def m = input =~ pattern
        while (m.size()) {
            for (int i = 0; i <m.size(); i++) {
                input = input.replaceFirst(Pattern.quote(m[i][0]), "${answer(m[i][1])}")
            }
            m = input =~ pattern
        }
        return answer(input)
    }

    static answerPart1 = {String input ->
        long result = 0
        def m = input =~ /(\d+)([ +\*]*)/
        def sign = PLUS

        for (int i = 0; i <m.size(); i++) {
            if (sign == PLUS) {
                result += Long.parseLong(m[i][1])
            } else {
                result *= Long.parseLong(m[i][1])
            }
            sign = m[i][2].trim()
        }
        return result
    }

    static Closure getPart1() {
        return answerPart1
    }

    static answerPart2 = {String input ->
        while (input.contains(PLUS)) {
            def m = input =~ /(\d+ \+ \d+)/
            for (int i = 0; i <m.size(); i++) {
                input = input.replaceFirst(Pattern.quote(m[i][0]), "${getPart1().curry(m[i][1])()}")
            }
        }

        return getPart1().curry(input)()
    }
}
