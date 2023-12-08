package aoc.y2023.day8

import aoc.y2019.Utils

class Day8 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n')
        List<Integer> instructions = input.pop().toCharArray().collect {it == ('R' as char) ? 1 : 0}
        Map<String, List<String>> maps = input.collectEntries {
            def line = it.split(' = ')
            [(line[0]): line[1][1..-2].split(', ')]
        }
        List<String> possiblePaths = maps.get('AAA')
        def stepCount = findPath(instructions, possiblePaths, maps, {it -> it == 'ZZZ'})

        return stepCount
    }

    protected static long findPath(List<Integer> instructions, def possiblePaths, Map<String, List<String>> maps, Closure endCheck) {
        long stepCount = 0
        boolean found = false
        def instructionPointer = 0
        def instruction = instructions[instructionPointer]
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

    static part2(String inputString) {
        def input = inputString.tokenize('\n')
        List<Integer> instructions = input.pop().toCharArray().collect {it == ('R' as char) ? 1 : 0}
        Map<String, List<String>> maps = input.collectEntries {
            def line = it.split(' = ')
            [(line[0]): line[1][1..-2].split(', ')]
        }
        long stepCount = 0
        boolean found = false
        List<List<String>> possiblePathList = maps.keySet().findAll {it.endsWith('A') }.collect {maps[it]}
        def instructionPointer = 0
        def instruction = instructions[instructionPointer]

        def stuff = possiblePathList.collect { possiblePaths ->
          findPath(instructions, possiblePaths, maps, {it -> it.endsWith('Z')})

        }


//        List<String> possiblePaths = maps.get('AAA')
//        def stepCount = findPath(instructions, possiblePaths, maps, {it -> it == 'ZZZ'})
//
//        while (!found) {
//            stepCount++
//            def nextPaths = possiblePaths.collect {it[instruction]}
//            if (nextPaths.every {it.endsWith('Z')}) {
//                found = true
//            } else {
//                possiblePaths = maps.keySet().findAll {nextPaths.contains(it) }.collect {maps[it]}
//                instructionPointer++
//                if (instructionPointer == instructions.size()) instructionPointer = 0
//                instruction = instructions[instructionPointer]
//            }
//        }

        return stuff.inject(1) {a,b -> Utils.lcm(a,b)}
    }
}
