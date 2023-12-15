package aoc.y2023.day15

class Day15 {
    static part1(String s) {
        def input = new Input(s.trim())
        return input.steps.collect { hash(it) }.sum()
    }

    static part2(String s) {
        def input = new Input(s.trim())
        List<List<Lens>> boxes = new ArrayList<>()
        for (i in 0..<256) {
            boxes.add([])
        }
        for (def step in input.steps) {
            def lens = new Lens(step)
            def box = boxes[lens.hashCode()]
            if (lens.focus) {
                if (box.contains(lens)) {
                    box.get(box.indexOf(lens)).focus = lens.focus
                } else {
                    box.add(lens)
                }
            } else {
                box.remove(lens)
            }
        }
        def result = 0
        for (i in 0..<boxes.size()) {
            def box = boxes[i]
            for (j in 0..<box.size()) {
                result += ((i+1) * (j+1)* box[j].focus)
            }
        }
        return result
    }

    static class Input {
        List<String> steps

        Input(String s) {
            steps = s.tokenize(',')
        }
    }

    static class Lens {
        String label
        int focus
        long box
        Lens(String s) {
            if (s.contains('=')) {
                def tokenize = s.tokenize('=')
                label = tokenize[0]
                focus = tokenize[1] as int
            } else {
                label = s[0..-2]
            }
            this.box = hash(label)
        }

        @Override
        int hashCode() {
            this.box.intValue()
        }

        @Override
        boolean equals(Object obj) {
            return obj instanceof Lens && this.label == obj.label
        }
    }

    static long hash(String s) {
        long result = 0
        def chars = s.toCharArray()
        for (def character in chars) {
            result += character
            result *= 17
            result = result % 256
        }
        return result
    }
}
