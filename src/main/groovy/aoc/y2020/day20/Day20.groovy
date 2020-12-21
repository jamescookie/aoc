package aoc.y2020.day20

class Day20 {
    static part1(String inputString) {
        Tile[] tiles = inputString.split('\n\n').collect {new Tile(it)}
        for (i in 0..<tiles.size()) {
            tiles[i].edgesContained = tiles.findAll{it != tiles[i]}*.edges.flatten().unique().intersect(tiles[i].edges).size()
        }
        long result = 1
        tiles.sort {it.edgesContained}[0..3]*.id.each {
            result *= it
        }
        return result
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect {Integer.parseInt(it)}
        return null
    }

    static class Tile {
        int id
        String[] tile
        List<Integer> edges = []
        int edgesContained

        Tile(String input) {
            input = input.replaceAll('\\.', '0').replaceAll('#', '1')
            def split = input.split('\n')
            id = Integer.parseInt(split[0] - "Tile " - ":")
            tile = split[1..-1]
            edges << Integer.parseInt(tile[0], 2)
            edges << Integer.parseInt(tile[0].reverse(), 2)
            edges << Integer.parseInt(tile*.getAt(0).join(''), 2)
            edges << Integer.parseInt(tile*.getAt(0).join('').reverse(), 2)
            edges << Integer.parseInt(tile[-1], 2)
            edges << Integer.parseInt(tile[-1].reverse(), 2)
            edges << Integer.parseInt(tile*.getAt(-1).join(''), 2)
            edges << Integer.parseInt(tile*.getAt(-1).join('').reverse(), 2)
        }
    }
}
