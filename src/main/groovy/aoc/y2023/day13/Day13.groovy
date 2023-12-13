package aoc.y2023.day13

class Day13 {
    public static char ASH = ('.' as char)
    public static char ROCK = ('#' as char)

    static part1(String s) {
        def input = new Input(s)
        long result = 0
        for (List<String> puzzle in input.puzzles) {
            def vertical = findVertical(puzzle)
            if (vertical) {
                result += vertical
            } else {
                result += findHorizontal(puzzle)
            }
        }
        return result
    }

    static part2(String s) {
        def input = new Input(s)
        long result = 0
        for (List<String> puzzle in input.puzzles) {
            def currentVertical = findVertical(puzzle)
            def currentHorizontal = findHorizontal(puzzle)
            boolean found = false
            for (i in 0..<puzzle.size()) {
                def row = puzzle[i].toCharArray()
                for (j in 0..<row.size()) {
                    flipBit(row, j)
                    puzzle.remove(i)
                    puzzle.add(i, String.valueOf(row))
                    def vertical = findVertical(puzzle)
                    if (vertical && vertical != currentVertical) {
                        result += vertical
                        found = true
                        break
                    } else {
                        def horizontal = findHorizontal(puzzle)
                        if (horizontal && horizontal != currentHorizontal) {
                            result += horizontal
                            found = true
                            break
                        }
                    }
                    flipBit(row, j)
                    puzzle.remove(i)
                    puzzle.add(i, String.valueOf(row))
                }
                if (found) {
                    break
                }
            }
        }
        return result
    }

    private static void flipBit(char[] chars, int i) {
        if (chars[i] == ASH) {
            chars[i] = ROCK
        } else if (chars[i] == ROCK) {
            chars[i] = ASH
        }
    }

    static class Input {
        List<List<String>> puzzles

        Input(String s) {
            puzzles = s.split('\n\n')*.tokenize('\n')
        }
    }

    static int findVertical(List<String> puzzle) {
        int result = 0
        int size = puzzle[0].size() - 1
        boolean found
        for (i in 0..<size) {
            def col1 = puzzle*.getAt(i)
            def col2 = puzzle*.getAt(i + 1)
            if (col1 == col2) {
                found = true
                for (j in 1..<(i+1)) {
                    if (i + 1 + j > size || i - j < 0) {
                        break
                    }
                    col1 = puzzle*.getAt(i - j)
                    col2 = puzzle*.getAt(i + 1 + j)
                    if (col1 != col2) {
                        found = false
                    }
                }
                if (found) {
                    result = i + 1
                    break
                }
            }
        }
        return result
    }

    static int findHorizontal(List<String> puzzle) {
        int result = 0
        int size = puzzle.size() - 1
        boolean found
        for (i in 0..<size) {
            def row1 = puzzle[i]
            def row2 = puzzle[i + 1]
            if (row1 == row2) {
                found = true
                for (j in 1..<(i+1)) {
                    if (i + 1 + j > size || i - j < 0) {
                        break
                    }
                    row1 = puzzle[i - j]
                    row2 = puzzle[i + 1 + j]
                    if (row1 != row2) {
                        found = false
                    }
                }
                if (found) {
                    result = (i + 1) * 100
                    break
                }
            }
        }
        return result
    }
}
