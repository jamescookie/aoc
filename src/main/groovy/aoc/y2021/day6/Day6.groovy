package aoc.y2021.day6

class Day6 {

    static part1(String inputString, int days) {
        List<Long> input = inputString.trim().tokenize(',').collect { Long.parseLong(it) }
        List<Long> population = (0..8).collect { x -> input.findAll { it == x }.size().longValue() }

        for (i in 0..<days) {
            long breeding = population.remove(0)
            population << breeding
            population[6] = population[6] + breeding
        }

        return population.sum()
    }
}
