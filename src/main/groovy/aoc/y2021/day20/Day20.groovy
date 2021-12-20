package aoc.y2021.day20

class Day20 {
    static part1(String inputString) {
        return enhanceImage(inputString, 2)
    }

    static part2(String inputString) {
        return enhanceImage(inputString, 50)
    }

    protected static int enhanceImage(String inputString, int times) {
        String[] input = inputString.split('\n\n')
        Integer[] algorithm = input[0].collect {it == '#' ? 1 : 0}
        List<List<Integer>> image = input[1].tokenize().collect { row -> (row as List).collect { it == '#' ? 1 : 0 } }

        int fill = 0
        for (i in 0..<times) {
            image = enhance(image, algorithm, fill)
            fill = algorithm[fill == 0 ? 0 : 511]
        }

        return image.flatten().findAll { it }.size()
    }

    protected static List<List<Integer>> enhance(List<List<Integer>> image, Integer[] algorithm, int fill) {
        def blankRow = image[0].collect { fill }
        image.push([] + blankRow)
        image.push([] + blankRow)
        image.add([] + blankRow)
        image.add([] + blankRow)
        image = image.collect { row -> [fill, fill] + row + [fill, fill] }

        List<List<Integer>> newImage = new ArrayList<>()
        for (int x = 1; x < image.size() - 1; x++) {
            List<Integer> newRow = new ArrayList<>()
            newImage << newRow
            for (int y = 1; y < image[0].size() - 1; y++) {
                String binaryIndex = [image[x - 1][y - 1], image[x - 1][y], image[x - 1][y + 1], image[x][y - 1], image[x][y], image[x][y + 1], image[x + 1][y - 1], image[x + 1][y], image[x + 1][y + 1]].join()
                newRow << algorithm[Integer.parseInt(binaryIndex, 2)]
            }
        }
        newImage
    }
}
