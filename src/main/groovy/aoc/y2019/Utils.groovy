package aoc.y2019

class Utils {
    static long gcd(long m, long n) { m = m.abs(); n = n.abs(); n == 0 ? m : m % n == 0 ? n : gcd(n, m % n) }

    static long lcm(long m, long n) { Math.abs(m * n) / gcd(m, n) }
}
