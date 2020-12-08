package aoc.y2020.day8

class Day8 {
    static part1(String inputString) {
        String[] input = inputString.tokenize('\n')
        def instructions = input.collect { new Instruction(it) }
        return new Program().run(instructions)
    }

    static part2(String inputString) {
        String[] input = inputString.tokenize('\n')
        def instructions = input.collect { new Instruction(it) }
        def result = 0
        Program p
        for (i in 0..<instructions.size()) {
            instructions.each {it.visited = false}
            if (instructions[i].command == "nop") {
                instructions[i].command = "jmp"
                p = new Program()
                p.run(instructions)
                if (!p.infiniteLoop) {
                    return p.acc
                } else {
                    instructions[i].command = "nop"
                }
            } else if (instructions[i].command == "jmp") {
                instructions[i].command = "nop"
                p = new Program()
                p.run(instructions)
                if (!p.infiniteLoop) {
                    return p.acc
                } else {
                    instructions[i].command = "jmp"
                }
            }
        }
        return result
    }

    static class Program {
        def acc = 0
        def pointer = 0
        boolean infiniteLoop = false

        int run(List<Instruction> instructions) {
            def finish = false
            while (!finish) {
                if (pointer >= instructions.size()) {
                    finish = true
                } else {
                    def instruction = instructions[pointer]
                    if (instruction.visited) {
                        finish = true
                        infiniteLoop = true
                    } else {
                        instruction.visited = true
                        switch (instruction.command) {
                            case "nop":
                                pointer++
                                break;
                            case "acc":
                                pointer++
                                acc += instruction.amount
                                break;
                            case "jmp":
                                pointer += instruction.amount
                                break;
                        }
                    }
                }
            }
            return acc
        }
    }

    static class Instruction {
        boolean visited = false
        String command
        int amount

        Instruction(String s) {
            def input = s.tokenize()
            command = input[0]
            amount = Integer.parseInt(input[1])
        }
    }
}
