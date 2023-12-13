package aoc.y2023.day13

class Day13 {
    public static char ASH = ('.' as char)
    public static char ROCK = ('#' as char)

    static part1(String s) {
        def input = new Input(s)
        long result = 0
        int value
        for (List<String> puzzle in input.puzzles) {
            if (!(value = findVertical(puzzle))) {
                value = findHorizontal(puzzle)
            }
            result += value
        }
        return result
    }

    static part2(String s) {
        def input = new Input(s)
        long result = 0
        int value
        char[] row
        for (List<String> puzzle in input.puzzles) {
            def currentVertical = findVertical(puzzle)
            def currentHorizontal = findHorizontal(puzzle)
            for (i in 0..<puzzle.size()) {
                row = puzzle[i].toCharArray()
                for (j in 0..<row.size()) {
                    flipBit(row, j, puzzle, i)
                    if (value = findVertical(puzzle, currentVertical)) {
                        break
                    } else if (value = findHorizontal(puzzle, currentHorizontal)) {
                        break
                    }
                    flipBit(row, j, puzzle, i)
                }
                if (value) {
                    result += value
                    break
                }
            }
        }
        return result
    }

    static class Input {
        List<List<String>> puzzles

        Input(String s) {
            puzzles = s.split('\n\n')*.tokenize('\n')
        }
    }

    static int findVertical(List<String> puzzle, int exclude = -1) {
        return findMatch(puzzle, puzzle[0].size() - 1, exclude, 1, {puz, i -> puz*.getAt(i)})
    }

    static int findHorizontal(List<String> puzzle, int exclude = -1) {
        return findMatch(puzzle, puzzle.size() - 1, exclude, 100, {puz, i -> puz[i]})
    }

    static int findMatch(List<String> puzzle, int size, int exclude, int multiplier, Closure getter) {
        int result = 0
        boolean found
        for (i in 0..<size) {
            def potential = i + 1
            def first = getter(puzzle, i)
            def second = getter(puzzle, potential)
            if (exclude != (potential * multiplier) && first == second) {
                found = true
                for (j in 1..<potential) {
                    if (potential + j > size || i - j < 0) {
                        break
                    }
                    first = getter(puzzle, i - j)
                    second = getter(puzzle, potential + j)
                    if (first != second) {
                        found = false
                    }
                }
                if (found) {
                    result = potential * multiplier
                    break
                }
            }
        }
        return result
    }

    private static void flipBit(char[] chars, int charNumber, List<String> puzzle, int rowNumber) {
        if (chars[charNumber] == ASH) {
            chars[charNumber] = ROCK
        } else if (chars[charNumber] == ROCK) {
            chars[charNumber] = ASH
        }
        puzzle.remove(rowNumber)
        puzzle.add(rowNumber, String.valueOf(chars))
    }
}
