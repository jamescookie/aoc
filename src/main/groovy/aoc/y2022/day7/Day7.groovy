package aoc.y2022.day7

class Day7 {
    static String ROOT = '/'

    static part1(String inputString) {
        Map<String, Map> fileSystem = createFileSystem(inputString.split('\n'), ROOT)
        List sizes = []
        calcSize(fileSystem.get(ROOT), sizes)
        return sizes.findAll { it <= 100000 }.sum()
    }

    static part2(String inputString) {
        Map<String, Map> fileSystem = createFileSystem(inputString.split('\n'), ROOT)
        List sizes = []
        def total = calcSize(fileSystem.get(ROOT), sizes)
        def currentSpace = 70000000 - total
        def requiredSpace = 30000000 - currentSpace
        return sizes.sort().find { it >= requiredSpace }
    }

    static calcSize(Map fileSystem, List sizes) {
        Long size = 0
        fileSystem.forEach { k, v ->
            if (v instanceof Map) {
                size += calcSize(v, sizes)
            } else {
                size += Long.parseLong(v.toString())
            }
        }
        sizes << size
        return size
    }

    static Map<String, Map> createFileSystem(String[] input, String ROOT) {
        Map<String, Map> fileSystem = [(ROOT): [:]]
        List<String> path = [ROOT]
        for (i in 0..<input.size()) {
            def next = input[i]
            if (next.startsWith('$')) {
                next = next.substring(2)
                if (next.startsWith('cd')) {
                    next = next.substring(3)
                    if (next == ROOT) {
                        path = [ROOT]
                    } else if (next == '..') {
                        path.removeLast()
                    } else {
                        path << next
                    }
                }
            } else {
                def currentDir = navigate(fileSystem, path)
                if (next.startsWith('dir')) {
                    next = next.substring(4)
                    currentDir.putIfAbsent(next, [:])
                } else {
                    def fileAndSize = next.split()
                    currentDir.putIfAbsent(fileAndSize[1], fileAndSize[0])
                }
            }
        }
        fileSystem
    }

    static Map<String, Object> navigate(Map<String, Map> fileSystem, List<String> path) {
        for (i in 0..<path.size()) {
            fileSystem.putIfAbsent(path[i], [:])
            fileSystem = fileSystem.get(path[i])
        }
        return fileSystem
    }
}
