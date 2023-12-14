package aoc

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

    /**
     * Finds repeating patterns in a list of numbers
     * @param entries the list to look for patterns in
     * @param repeats how many times the pattern should repeat before it is considered a patter
     * @return A single entry Map where the key is the index into the list of the pattern, and the value is the pattern itself
     */
    static Map<Integer, List<Long>> checkPattern(List<Long> entries, int repeats) {
        repeats = repeats > 1 ? repeats : 2
        List<Long> pattern
        boolean found
        Map<Integer, List<Long>> result = null
        for (x in 0..<(entries.size() - 1)) {
            pattern = []
            for (i in x..<(entries.size() - 1)) {
                pattern << entries[i]
                int end = x + (pattern.size() * repeats)
                if (end > entries.size()) {
                    break
                }
                for (int j = (i + 1); j < end; j++) {
                    found = false
                    for (k in 0..<pattern.size()) {
                        found = true
                        if (entries[j + k] != pattern[k]) {
                            found = false
                            break
                        }
                    }
                    if (found) {
                        j = j + pattern.size() - 1
                    } else {
                        break
                    }
                }
                if (found) {
                    result = [(i - (pattern.size() - 1)): pattern]
                    break
                }
            }
            if (result) {
                break
            }
        }
        return result
    }
}
