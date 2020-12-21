package aoc.y2020.day21

class Day21 {
    static part1(String inputString) {
        List<Food> foods = inputString.tokenize('\n').collect { new Food(it) }
        Map<String, List<String>> allergensToPotentials = findAllergens(foods)
        def allergenWords = allergensToPotentials.values().flatten().toSet()
        def result = foods*.ingredients.flatten()
        result.removeAll(allergenWords)
        return result.size()
    }

    static part2(String inputString) {
        List<Food> foods = inputString.tokenize('\n').collect { new Food(it) }
        Map<String, List<String>> allergensToPotentials = findAllergens(foods)
        boolean finished = false
        while (!finished) {
            for (i in 0..<allergensToPotentials.size()) {
                if (allergensToPotentials.values()[i].size() == 1) {
                    for (j in 0..<allergensToPotentials.size()) {
                        if (i != j) {
                            allergensToPotentials.values()[j].removeAll(allergensToPotentials.values()[i])
                        }
                    }
                }
            }
            finished = allergensToPotentials.values().every { it.size() == 1 }
        }
        return allergensToPotentials.keySet().sort().collect{allergensToPotentials.get(it)[0]}.join(',')
    }

    private static Map<String, List<String>> findAllergens(List<Food> foods) {
        Set<String> allergens = new HashSet<>(foods*.allergens.flatten())
        Map<String, List<String>> allergensToPotentials = [:]
        for (i in 0..<allergens.size()) {
            String allergen = allergens[i]
            def potentials = []
            for (j in 0..<foods.size()) {
                if (foods[j].allergens.contains(allergen)) {
                    if (potentials) {
                        potentials = potentials.intersect(foods[j].ingredients)
                    } else {
                        potentials.addAll(foods[j].ingredients)
                    }
                }
            }
            allergensToPotentials.put(allergen, potentials)
        }
        allergensToPotentials
    }

    static class Food {
        List<String> ingredients
        List<String> allergens

        Food(String input) {
            input -= '('
            input -= ')'
            def split = input.split('contains')
            ingredients = split[0].tokenize()
            allergens = split[1].tokenize(', ')
        }
    }
}
