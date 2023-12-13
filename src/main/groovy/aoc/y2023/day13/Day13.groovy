package aoc.y2023.day13

class Day13 {
    public static char ASH = ('0' as char)
    public static char ROCK = ('#' as char)

    static part1(String s) {
        def input = new Input(s)
        long result = 0
        for (List<String> puzzle in input.puzzles) {
            def vertical = findVertical(puzzle, -1)
            if (vertical) {
                result += vertical
            } else {
                result += findHorizontal(puzzle, -1)
            }
        }
        return result
    }

    static part2(String s) {
        def input = new Input(s)
        long result = 0
        for (List<String> puzzle in input.puzzles) {
            def currentVertical = findVertical(puzzle, -1)
            def currentHorizontal = findHorizontal(puzzle, -1)
            boolean found = false
            for (i in 0..<puzzle.size()) {
                def row = puzzle[i].toCharArray()
                for (j in 0..<row.size()) {
                    flipBit(row, j)
                    puzzle.remove(i)
                    puzzle.add(i, String.valueOf(row))
                    def vertical = findVertical(puzzle, currentVertical)
                    if (vertical) {
                        result += vertical
                        found = true
                        break
                    } else {
                        def horizontal = findHorizontal(puzzle, currentHorizontal)
                        if (horizontal) {
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
            puzzles = s.split('\n\n')*.tokenize('\n')*.collect {it.replaceAll('[.]', '0')}
        }
    }

    static int findVertical(List<String> puzzle, int exclude) {
        int result = 0
        int size = puzzle[0].size() - 1
        boolean found
        for (i in 0..<size) {
            def potential = i + 1
            def col1 = puzzle*.getAt(i)
            def col2 = puzzle*.getAt(potential)
            if (exclude != potential && col1 == col2) {
                found = true
                for (j in 1..<potential) {
                    if (potential + j > size || i - j < 0) {
                        break
                    }
                    col1 = puzzle*.getAt(i - j)
                    col2 = puzzle*.getAt(potential + j)
                    if (col1 != col2) {
                        found = false
                    }
                }
                if (found) {
                    result = potential
                    break
                }
            }
        }
        return result
    }

    static int findHorizontal(List<String> puzzle, int exclude) {
        int result = 0
        int size = puzzle.size() - 1
        boolean found
        for (i in 0..<size) {
            def potential = i + 1
            def row1 = puzzle[i]
            def row2 = puzzle[potential]
            if (exclude != (potential * 100) && row1 == row2) {
                found = true
                for (j in 1..<potential) {
                    if (potential + j > size || i - j < 0) {
                        break
                    }
                    row1 = puzzle[i - j]
                    row2 = puzzle[potential + j]
                    if (row1 != row2) {
                        found = false
                    }
                }
                if (found) {
                    result = potential * 100
                    break
                }
            }
        }
        return result
    }
}
