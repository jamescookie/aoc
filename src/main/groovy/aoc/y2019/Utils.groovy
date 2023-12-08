package aoc.y2019

class Utils {
    /**
     * Greatest Common Divisor
     * Uses Euclidean algorithm:
     * gcd(a, b) = gcd(|a%b|, |a|); where |a| >= |b|
     * gcd(p, 0) = gcd(0, p) = |p|
     */
    static long gcd(long a, long b) { a = a.abs(); b = b.abs(); b == 0 ? a : a % b == 0 ? b : gcd(b, a % b) }

    /**
     * Lowest Common Multiple
     * The absolute value of the product of two numbers is equal to the product of their GCD and LCM.
     * As stated: gcd(a, b) * lcm(a, b) = |a * b|
     * Consequently: lcm(a, b) = |a * b|/gcd(a, b)
     */
    static long lcm(long a, long b) { Math.abs(a * b) / gcd(a, b) }
}
