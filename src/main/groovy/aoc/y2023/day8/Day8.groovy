package aoc.y2023.day8

class Day8 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')
        def instructions = input.pop().toCharArray().collect {it == ('R' as char) ? 1 : 0}
        Map<String, List<String>> maps = input.collectEntries {
            def line = it.split(' = ')
            [(line[0]): line[1][1..-2].split(', ')]
        }
        int stepCount = 0
        boolean found = false
        def possiblePaths = maps.get('AAA')
        def instructionPointer = 0
        def instruction = instructions[instructionPointer]
        while (!found) {
            stepCount++
            def nextPath = possiblePaths[instruction]
            if (nextPath == 'ZZZ') {
                found = true
            } else {
                possiblePaths = maps.get(nextPath)
                instructionPointer++
                if (instructionPointer == instructions.size()) instructionPointer = 0
                instruction = instructions[instructionPointer]
            }
        }

        return stepCount
    }

    static part2(String inputString) {
        def input = inputString.tokenize('\n')
        def instructions = input.pop().toCharArray().collect {it == ('R' as char) ? 1 : 0}
        Map<String, List<String>> maps = input.collectEntries {
            def line = it.split(' = ')
            [(line[0]): line[1][1..-2].split(', ')]
        }
        long stepCount = 0
        boolean found = false
        def possiblePaths = maps.keySet().findAll {it.endsWith('A') }.collect {maps[it]}
        def instructionPointer = 0
        def instruction = instructions[instructionPointer]

        while (!found) {
            stepCount++
            def nextPaths = possiblePaths.collect {it[instruction]}
            if (nextPaths.every {it.endsWith('Z')}) {
                found = true
            } else {
                possiblePaths = maps.keySet().findAll {nextPaths.contains(it) }.collect {maps[it]}
                instructionPointer++
                if (instructionPointer == instructions.size()) instructionPointer = 0
                instruction = instructions[instructionPointer]
            }
        }

        return stepCount
    }
}
