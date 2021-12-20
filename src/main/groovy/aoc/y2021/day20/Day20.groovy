package aoc.y2021.day20

class Day20 {
    static part1(String inputString) {
        String[] input = inputString.split('\n\n')
        String algorithm = input[0].replaceAll(/\./, '0').replaceAll('#', '1')
        List<List<Integer>> image = input[1].tokenize().collect { row -> (row as List).collect { it == '#' ? 1 : 0 } }

        int fill = 0
        for (i in 0..<2) {
            image = enhance(image, algorithm, fill)
            fill = algorithm[fill == 0 ? 0 : 511] as int
        }

        return image.flatten().findAll { it }.size()
    }

    static part2(String inputString) {
        String[] input = inputString.split('\n\n')
        String algorithm = input[0].replaceAll(/\./, '0').replaceAll('#', '1')
        List<List<Integer>> image = input[1].tokenize().collect { row -> (row as List).collect { it == '#' ? 1 : 0 } }

        int fill = 0
        for (i in 0..<50) {
            image = enhance(image, algorithm, fill)
            fill = algorithm[fill == 0 ? 0 : 511] as int
        }

        return image.flatten().findAll { it }.size()
    }

    protected static List<List<Integer>> enhance(List<List<Integer>> image, String algorithm, int fill) {
        def blankRow = image[0].collect { fill }
        image.push(new ArrayList<>(blankRow))
        image.push(new ArrayList<>(blankRow))
        image << new ArrayList<>(blankRow)
        image << new ArrayList<>(blankRow)
        image.each { row -> row.push(fill); row.push(fill); row.add(fill); row.add(fill) }

        List<List<Integer>> newImage = new ArrayList<>()
        for (int x = 1; x < image.size() - 1; x++) {
            List<Integer> newRow = new ArrayList<>()
            newImage << newRow
            for (int y = 1; y < image[0].size() - 1; y++) {
                String binaryIndex = [image[x - 1][y - 1], image[x - 1][y], image[x - 1][y + 1], image[x][y - 1], image[x][y], image[x][y + 1], image[x + 1][y - 1], image[x + 1][y], image[x + 1][y + 1]].join()
                newRow << (algorithm[Integer.parseInt(binaryIndex, 2)] as int)
            }
        }
        newImage
    }
}
