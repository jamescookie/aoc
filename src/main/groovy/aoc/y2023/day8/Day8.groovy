package aoc.y2023.day8

import aoc.y2019.Utils

class Day8 {
    static part1(String inputString) {
        def input = new Input(inputString)
        return findPath(input.instructions, input.maps.get('AAA'), input.maps, { it -> it == 'ZZZ' })
    }

    static part2(String inputString) {
        def input = new Input(inputString)
        Closure endCondition = { it -> it.endsWith('Z') }
        return input.maps.keySet().findAll { it.endsWith('A') }
                .collect { input.maps[it] }
                .collect { findPath(input.instructions, it, input.maps, endCondition) }
                .inject(1) { a, b -> Utils.lcm(a, b) }
    }

    private static class Input {
        List<Integer> instructions
        Map<String, List<String>> maps

        Input(String inputString) {
            def input = inputString.tokenize('\n')
            instructions = input.pop().toCharArray().collect { it == ('R' as char) ? 1 : 0 }
            maps = input.collectEntries {
                def line = it.split(' = ')
                [(line[0]): line[1][1..-2].split(', ')]
            }
        }
    }

    protected static long findPath(List<Integer> instructions, def possiblePaths, Map<String, List<String>> maps, Closure endCheck) {
        long stepCount = 0
        boolean found = false
        int instructionPointer = 0
        int instruction = instructions[instructionPointer]
        while (!found) {
            stepCount++
            def nextPath = possiblePaths[instruction]
            if (endCheck(nextPath)) {
                found = true
            } else {
                possiblePaths = maps.get(nextPath)
                instructionPointer++
                if (instructionPointer == instructions.size()) instructionPointer = 0
                instruction = instructions[instructionPointer]
            }
        }
        stepCount
    }
}
