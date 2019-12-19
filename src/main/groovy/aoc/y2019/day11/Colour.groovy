package aoc.y2019.day11

enum Colour {
    BLACK(0, ' '),
    WHITE(1, '*')

    int value
    String display

    Colour(value, display) {
        this.value = value
        this.display = display
    }

    static Colour fromValue(int c) {
        for (Colour colour  : Colour.values()) {
            if (colour.value == c) {
                return colour
            }
        }
        return null
    }
}