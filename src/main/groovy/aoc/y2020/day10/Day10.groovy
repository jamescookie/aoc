package aoc.y2020.day10

class Day10 {
    static part1(String inputString) {
        Integer[] input = inputString.tokenize().collect { Integer.parseInt(it) }.sort()
        int current = 0
        int[] diffs = [0, 0, 0, 1, 0]
        for (i in 0..<input.size()) {
            diffs[input[i] - current]++
            current = input[i]
        }
        return diffs[1] * diffs[3]
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { Integer.parseInt(it) }.sort()
        input = [0, input, input[-1] + 3, input[-1] + 6, input[-1] + 9, input[-1] + 12, input[-1] + 15].flatten()
        int current = 3
        int passedBy = 0
        long result = 1
        int[] diffs = [0, 0, 0, 0, 0]
        for (int i = 1; i < input.size() - 5; i++) {
            def lookat = input[i-1..i+4]
            if (input[i + 5] == input[i] + 5) {
                result = calc(6, result)
                i += 2
            } else if (input[i + 4] == input[i] + 5) {
                result = calc(9, result)
                i += 3
            } else if (input[i + 3] == input[i] + 4) {
                if (input[i + 2] == input[i] + 2) {
                    result = calc(5, result)
                } else {
                    result = calc(4, result)
                }
                i += 2
            } else if (input[i + 2] == input[i] + 2) {
                result = calc(4, result)
                i++
            } else if (input[i + 1] == input[i] + 1) {
                result = calc(2, result)
            }
        }
        return result
    }
//                if (input[i + 3] == input[i] + 3) {
//                    result = calc(6, result)
//                    i+=2
//                } else if (input[i + 2] == input[i] + 2) {
//                    if (input[i + 3] == input[i+1] + 3) {
//                        result = calc(5, result)
//                    } else {
//                        result = calc(4, result)
//                    }
//                    i++
//                } else if (input[i + 1] == input[i] + 1) {
//                    result = calc(2, result)
////                    i++
//                }


//            } else if (i + 2 < input.size()) {
//                if (input[i + 2] == input[i] + 3) {
//                    result = calc(2, result)
//                } else if (input[i + 2] == input[i] + 2) {
//                    result = calc(2, result)
//                }
//
//            }
//            if (i + 3 < input.size()) {
//                if (input[i + 3] == input[i] + 3) {
//                    result = calc(6, result)
//                    i += 2
//                } else if (input[i + 2] == input[i] + 2) {
//                    result = calc(4, result)
//                    i++
//                }
//            } else if (i + 2 < input.size()) {
//                if (input[i + 2] == input[i] + 3) {
//                    result = calc(2, result)
//                    i++
//                } else if (input[i + 2] == input[i] + 2) {
//                    result = calc(4, result)
//                    i++
//                }
//
//            }


//            passedBy++
//            def x = input[i]
//            if (input[i] < current) {
//            } else if (input[i] == current) {
//                result = calc(passedBy, result)
//                diffs[passedBy]++
//                passedBy = 0
//                current = input[i] + 3
//            } else if (input[i] > current) {
//                result = calc(passedBy - 1, result)
//                diffs[passedBy - 1]++
//                passedBy = 1
//                current = input[i - 1] + 3
//
    static long calc(int passed, long current) {
        if (!current) return passed
        return current * passed
    }

    static long fact(int n) {
        long fact = 1
        for (int i = 2; i <= n; i++) {
            fact *= i
        }
        return fact
    }
}




