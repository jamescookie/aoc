package aoc.y2021.day2

class Day2 {
    static part1(String inputString) {
        String[] input = inputString.tokenize()
        int horizontal = 0
        int depth = 0
        for (int i = 0; i < input.size() - 1; i+=2) {
            String instruction = input[i]
            int amount = Integer.parseInt(input[i+1])
            switch (instruction) {
                case "forward":
                    horizontal += amount
                    break
                case "down":
                    depth += amount
                    break
                case "up":
                    depth -= amount
                    break
            }
        }
        return horizontal * depth
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize()
        int horizontal = 0
        int aim = 0
        long depth = 0
        for (int i = 0; i < input.size() - 1; i+=2) {
            String instruction = input[i]
            int amount = Integer.parseInt(input[i+1])
            switch (instruction) {
                case "forward":
                    horizontal += amount
                    depth += (aim * amount)
                    break
                case "down":
                    aim += amount
                    break
                case "up":
                    aim -= amount
                    break
            }
        }
        return horizontal * depth
    }
}
