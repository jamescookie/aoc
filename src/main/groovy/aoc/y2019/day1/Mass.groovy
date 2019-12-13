class Mass {
    static int part1(it) {
        return (it / 3).intValue() - 2
    }
    static int part2(it) {
        def answer = part1(it)
        if (answer > 0) {
            return answer + part2(answer)
        } else {
            return 0
        }
    }
}