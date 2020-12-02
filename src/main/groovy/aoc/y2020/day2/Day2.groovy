package aoc.y2020.day2

class Day2 {
    static part1(String inputString) {
        String[] input = inputString.tokenize('\n')
        return input.collect{it.tokenize()}.collect {new PasswordValidator(it[0], it[1], it[2])}.findAll {it.validPart1()}.size()
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize('\n')
        return input.collect{it.tokenize()}.collect {new PasswordValidator(it[0], it[1], it[2])}.findAll {it.validPart2()}.size()
    }

    static class PasswordValidator {
        int low
        int high
        char letter
        Character[] password

        PasswordValidator(String repeats, String letter, String password) {
            def tokenize = repeats.tokenize('-')
            low = Integer.parseInt(tokenize[0])
            high = Integer.parseInt(tokenize[1])
            this.letter = letter[0]
            this.password = password.toCharArray()
        }

        boolean validPart1() {
            def occurrences = password.findAll { it == letter }.size()
            return occurrences >= low && occurrences <= high
        }

        boolean validPart2() {
            return [password[low-1] == letter, password[high-1] == letter].findAll{it}.size() == 1
        }
    }
}
