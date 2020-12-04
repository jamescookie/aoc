package aoc.y2020.day4


class Day4 {
    static part1(String inputString) {
        String[] input = inputString.split(/\n\n/)
        return input.collect { new Passport(it.tokenize()).validPart1() }.findAll { it }.size()
    }

    static part2(String inputString) {
        String[] input = inputString.split(/\n\n/)
        return input.collect { new Passport(it.tokenize()).validPart2() }.findAll { it }.size()
    }

    static class Passport {
        String byr //(Birth Year)
        String iyr //(Issue Year)
        String eyr //(Expiration Year)
        String hgt //(Height)
        String hcl //(Hair Color)
        String ecl //(Eye Color)
        String pid //(Passport ID)
        String cid //(Country ID)

        Passport(List<String> input) {
            input.collect { it.tokenize(':') }.each { this["${it[0]}"] = it[1] }
        }

        boolean validPart1() {
            return byr &&
                    iyr &&
                    eyr &&
                    hgt &&
                    hcl &&
                    ecl &&
                    pid
        }

        boolean validPart2() {
            return validPart1() &&
                    byr ==~ /19[2-9][0-9]|200[0-2]/ &&
                    iyr ==~ /20[1][0-9]|2020/ &&
                    eyr ==~ /20[2][0-9]|2030/ &&
                    hgt ==~ /59in|6[0-9]in|7[0-6]in|1[5-8][0-9]cm|19[0-3]cm/ &&
                    hcl ==~ /#[0-9a-f]{6}/ &&
                    ecl ==~ /(amb|blu|brn|gry|grn|hzl|oth)+/ &&
                    pid ==~ /[0-9]{9}/
        }
    }
}
