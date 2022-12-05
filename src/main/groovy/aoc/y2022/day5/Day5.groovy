package aoc.y2022.day5

class Day5 {
    static part1(String inputString) {
        def input = inputString.split('\n\n')
        def instructions = input[1].split('\n').collect{it.split(' ')}.collect{[it[1],it[3],it[5]].collect{it as int}}
        def stacks = input[0].split('\n').collect{it.replaceAll('    ', '[ ] ').replaceAll(' ','').replaceAll('\\[\\]','[ ]').replaceAll('\\[','').replaceAll('\\]','')}[0..-2]

        List<LinkedList> queues = []
        for (i in 0..<stacks[-1].size()) {
            queues << new LinkedList<>()
        }
        stacks.each {stack->
            for (int i = 0; i<stack.size();i++) {
                if (stack[i] != ' ')
                queues[i].push(stack[i])
            }
        }
        queues = queues.collect{new LinkedList(it.reverse())}
        instructions.each {instruction->
            for (i in 0..<instruction[0]) {
                queues[instruction[2]-1].push(queues[instruction[1]-1].pop())
            }
        }

        return queues.collect{it.peek()}.join()
    }

    static part2(String inputString) {
        def input = inputString.split('\n\n')
        def instructions = input[1].split('\n').collect{it.split(' ')}.collect{[it[1],it[3],it[5]].collect{it as int}}
        def stacks = input[0].split('\n').collect{it.replaceAll('    ', '[ ] ').replaceAll(' ','').replaceAll('\\[\\]','[ ]').replaceAll('\\[','').replaceAll('\\]','')}[0..-2]

        List<List> queues = []
        for (i in 0..<stacks[-1].size()) {
            queues << []
        }
        stacks.each {stack->
            for (int i = 0; i<stack.size();i++) {
                if (stack[i] != ' ')
                    queues[i].push(stack[i])
            }
        }
        queues = queues.collect{it.reverse()}
        instructions.each {instruction->
            def newList = []
            for (i in 0..<instruction[0]) {
                newList << queues[instruction[1]-1].pop()
            }
            queues[instruction[2]-1] = newList + queues[instruction[2]-1]
        }

        return queues.collect{it[0]}.join()
    }
}
