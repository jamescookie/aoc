package aoc

class InputReader {

    static String read(where) {
        return new File("./src/test/groovy/aoc/$where/input.txt").text
    }
}
