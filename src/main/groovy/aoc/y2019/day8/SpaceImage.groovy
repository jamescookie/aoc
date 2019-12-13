package aoc.y2019.day8

class SpaceImage {
    static def findLayers = { width, height, input ->
        input.collect().collate(width * height)
    }
    static def countNumbers = { layer ->
        ('0'..'9').collect { num -> layer.count { it == num } }
    }
    static def findColours = { layers ->
        def result = layers[0].clone()
        for (int i = 0; i < result.size(); i++) {
            result[i] = layers.collect { it[i] }.find { it != '2' }
        }
        result
    }
}
