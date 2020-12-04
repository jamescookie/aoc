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
                return byr && Integer.parseInt(byr) >= 1920 && Integer.parseInt(byr) <= 2002 &&
                        iyr && Integer.parseInt(iyr) >= 2010 && Integer.parseInt(iyr) <= 2020 &&
                        eyr && Integer.parseInt(eyr) >= 2020 && Integer.parseInt(eyr) <= 2030 &&
                        hgt && validHeight(hgt) &&
                        hcl && hcl ==~ /#[0-9a-f]{6}/ &&
                        ecl && ecl in ['amb', 'blu','brn','gry','grn','hzl','oth'] &&
                        pid && pid ==~ /[0-9]{9}/
            } catch (e) {
                return false
            }
        }

        boolean validHeight(hgt) {
            def matcher = hgt =~ /(\d+)([cm|in]+)/
            if (matcher.size() == 1 && matcher[0].size() == 3) {
                if (matcher[0][2] == 'in') {
                    return Integer.parseInt(matcher[0][1]) >= 59 && Integer.parseInt(matcher[0][1]) <= 76
                }
                if (matcher[0][2] == 'cm') {
                    return Integer.parseInt(matcher[0][1]) >= 150 && Integer.parseInt(matcher[0][1]) <= 193
                }
            }
            return false
        }

        /*
        byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
cid (Country ID) - ignored, missing or not.
         */
    }
}
