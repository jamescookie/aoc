package aoc.y2019

class IntCode {
    private Long position = 0
    private Long inputPointer = 0
    private Long relativeBase = 0
    def state = State.AWAITING_INPUT
    def input
    List<Long> programme
    Long output = null

    IntCode(input, List<Long> programme) {
        this.input = [] + input
        this.programme = programme.clone()
    }

    def nextInput(item) {
        assert state == State.AWAITING_INPUT || State.PAUSED
        input += item
        return process()
    }

    private Long findParameterOffset(String instruction, int offset) {
        def where = 0
        switch (instruction) {
            case '0':
                where = programme[position + offset]
                break
            case '1':
                where = position + offset
                break
            case '2':
                where = relativeBase + programme[position + offset]
                break
        }
        if (programme[where] == null) {
            programme[where] = 0
        }
        return where
    }

    def processUntilNonZero() {
        while (!output) {
            process()
        }
        return output
    }

    def process() {
        state = State.PROCESSING
        while (state == State.PROCESSING) {
            def instructions = programme[position].toString().padLeft(5, '0')
            def instruction = Integer.parseInt(instructions[-2..-1])
            Long one = null
            Long two = null
            Long three = null
            Long move = 0
            if (instruction in [1, 2, 3, 4, 5, 6, 7, 8, 9]) {
                one = findParameterOffset(instructions[-3], 1)
                if (!(instruction in [3, 4, 9])) {
                    two = findParameterOffset(instructions[-4], 2)
                }
                if (instruction in [1,2,7,8]) {
                    three = findParameterOffset(instructions[-5], 3)
                }
            }
//            println "instruction: " + instruction
//            println "one: " + one
//            println "two: " + two
            switch (instruction) {
                case 1:
                    programme[three] = getValue(one) + getValue(two)
                    move = 4
                    break
                case 2:
                    programme[three] = getValue(one) * getValue(two)
                    move = 4
                    break
                case 3:
                    if (inputPointer >= input.size()) {
                        state = State.AWAITING_INPUT
                    } else {
                        programme[one] = input[inputPointer++]
                        move = 2
                    }
                    break
                case 4:
                    output = getValue(one)
                    move = 2
                    state = State.PAUSED
                    break
                case 5:
                    position = getValue(one) != 0 ? getValue(two) : position + 3
                    break
                case 6:
                    position = getValue(one) == 0 ? getValue(two) : position + 3
                    break
                case 7:
                    programme[three] = getValue(one) < getValue(two) ? 1 : 0
                    move = 4
                    break
                case 8:
                    programme[three] = getValue(one) == getValue(two) ? 1 : 0
                    move = 4
                    break
                case 9:
                    relativeBase += getValue(one)
                    move = 2
                    break
                default:
                    state = State.FINISHED
            }
            position += move
//            println programme
        }
        return output
    }

    private long getValue(long offset) {
        programme[offset]
    }

    enum State {
        PROCESSING,
        FINISHED,
        PAUSED,
        AWAITING_INPUT
    }
}