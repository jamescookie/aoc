package aoc.y2022.day5

class Day5 {
    static part1(String inputString) {
        def input = inputString.split('\n\n')
        List<LinkedList> queues = createQueues(input[0])
        getInstructions(input[1]).each { instruction ->
            for (i in 0..<instruction[0]) {
                queues[instruction[2]].push(queues[instruction[1]].pop())
            }
        }

        return queues.collect { it.peek() }.join()
    }

    static part2(String inputString) {
        def input = inputString.split('\n\n')
        List<List> queues = createQueues(input[0])
        getInstructions(input[1]).each { instruction ->
            def newList = []
            for (i in 0..<instruction[0]) {
                newList << queues[instruction[1]].pop()
            }
            queues[instruction[2]] = newList + queues[instruction[2]]
        }

        return queues.collect { it[0] }.join()
    }

    protected static List<List<Integer>> getInstructions(String input) {
        input.split('\n').collect { it.split(' ') }.collect { [it[1] as int, (it[3] as int) - 1, (it[5] as int) - 1] }
    }

    protected static List<LinkedList> createQueues(String input) {
        def stacks = input.split('\n').collect { it.replaceAll(/ {4}/, '[#] ').replaceAll(/[]\s\[]/, '') }[0..-2]

        List<LinkedList> queues = []
        for (i in 0..<stacks[-1].size()) {
            queues << new LinkedList<>()
        }
        stacks.each { stack ->
            for (int i = 0; i < stack.size(); i++) {
                if (stack[i] != '#') queues[i].push(stack[i])
            }
        }
        queues = queues.collect { new LinkedList(it.reverse()) }
        queues
    }
}
