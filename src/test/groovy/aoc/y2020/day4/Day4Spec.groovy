package aoc.y2020.day4

import aoc.InputReader
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Day4Spec extends Specification {
    static String t1 = """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in
"""
    static String t2 = """eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946

hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007

iyr:2010 hgt:194cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719

iyr:2010 hgt:58in hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719

iyr:2010 hgt:77in hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719

iyr:2010 hgt:148cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"""
    static String t3 = """pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f

eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022

iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"""

    def "part1 warmup tests"() {
        expect:
        Day4.part1(input) == output

        where:
        input | output
        t1    | 2
    }

    def "part1"() {
        expect:
        Day4.part1(InputReader.read("y2020/day4")) == 219
    }

    def "part2 warmup tests"() {
        expect:
        Day4.part2(input) == output

        where:
        input | output
        t2    | 0
        t3    | 4
    }

    def "part2"() {
        expect:
        Day4.part2(InputReader.read("y2020/day4")) == 127
    }
}
