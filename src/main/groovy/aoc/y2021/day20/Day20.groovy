package aoc.y2021.day20

import aoc.Point

class Day20 {
    static part1(String inputString) {
        String[] input = inputString.split('\n\n')
        String algorithm = input[0].replaceAll(/\./, '0').replaceAll('#', '1')
        List<List<Integer>> image = input[1].tokenize().collect { row -> (row as List).collect { it == '#' ? 1 : 0 } }

        List<List<Integer>> newImage = enhance(enhance(image, algorithm), algorithm)

        return newImage.flatten().findAll {it}.size()
    }

    protected static List<List<Integer>> enhance(List<List<Integer>> image, String algorithm) {
        def blankRow = image[0].collect { 0 }
        image.push(new ArrayList<>(blankRow))
        image.push(new ArrayList<>(blankRow))
        image << new ArrayList<>(blankRow)
        image << new ArrayList<>(blankRow)
        image.each { row -> row.push(0); row.push(0); row.add(0); row.add(0) }

        List<List<Integer>> newImage = new ArrayList<>()
        for (int x = 1; x < image.size() - 1; x++) {
            List<Integer> newRow = new ArrayList<>()
            newImage << newRow
            for (int y = 1; y < image[0].size() - 1; y++) {
                Point p = new Point(x, y)
                def neighbours = Point.neighboursWithDiagonals(image, p)
                neighbours.add(p)
                def collect = neighbours.sort().collect { image[it.x][it.y] }.join()
                newRow << (algorithm[Integer.parseInt(collect, 2)] as int)
            }
        }
        newImage
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }
}
