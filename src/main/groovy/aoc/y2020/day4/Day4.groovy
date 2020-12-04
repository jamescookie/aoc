package aoc.y2020.day4


class Day4 {
    static part1(String inputString) {
        String[] input = inputString.split(/\n\n/)
        return input.collect {new Passport(it.tokenize()).validPart1()}.findAll {it}.size()
    }

    static part2(String inputString) {
        String[] input = inputString.split(/\n\n/)
        return input.collect {new Passport(it.tokenize()).validPart2()}.findAll {it}.size()
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
            try {
                return validPart1() &&
                        between(byr, 1920, 2002) &&
                        between(iyr, 2010, 2020) &&
                        between(eyr, 2020, 2030) &&
                        validHeight(hgt) &&
                        hcl ==~ /#[0-9a-f]{6}/ &&
                        ecl in ['amb', 'blu','brn','gry','grn','hzl','oth'] &&
                        pid ==~ /[0-9]{9}/
            } catch (e) {
                return false
            }
        }

        static boolean validHeight(String hgt) {
            def matcher = hgt =~ /(\d+)([cm|in]+)/
            if (matcher.size() == 1 && matcher[0].size() == 3) {
                if (matcher[0][2] == 'in') {
                    return between(matcher[0][1], 59, 76)
                }
                if (matcher[0][2] == 'cm') {
                    return between(matcher[0][1], 150, 193)
                }
            }
            return false
        }

        static boolean between(String s, int low, int high) {
            s && Integer.parseInt(s) >= low && Integer.parseInt(s) <= high
        }
    }
}
