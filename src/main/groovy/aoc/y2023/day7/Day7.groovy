package aoc.y2023.day7

class Day7 {
    static part1(String inputString) {
        def input = inputString.tokenize('\n').collect { new HandAndBid(it) }.sort()
        long result = 0
        for (i in 0..<input.size()) {
            result += (i+1) * input[i].bid
        }

        return result
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static class HandAndBid implements Comparable<HandAndBid> {
        Hand hand
        int bid

        HandAndBid(String input) {
            def tokenize = input.tokenize()
            hand = new Hand(tokenize[0])
            bid = tokenize[1] as int
        }

        @Override
        int compareTo(HandAndBid o) {
            return this.hand <=> o.hand
        }
    }

    static class Hand implements Comparable<Hand> {
        public static Map<Integer[], Integer> RANK = [
                [1, 5]: 7,
                [2, 4]: 6,
                [2, 3]: 5,
                [3, 3]: 4,
                [3, 2]: 3,
                [4, 2]: 2,
                [5, 1]: 1,
        ]
        List<Card> cards
        int rank = 0

        Hand(String h) {
            cards = h.toCharArray().collect { new Card(it) }
            def by = cards.groupBy { it.number }
            rank = RANK.get([by.size(), by.values().sort { it.size() }.last().size()])
        }

        @Override
        int compareTo(Hand o) {
            if (this.rank == o.rank) {
                for (i in 0..<this.cards.size()) {
                    if (this.cards[i] != o.cards[i]) {
                        return this.cards[i] <=> o.cards[i]
                    }
                }
                return 0
            } else {
                return this.rank <=> o.rank
            }
        }
    }

    static class Card implements Comparable<Card> {
        public static Map<Character, Integer> RANK = [
                ('T' as char): 10,
                ('J' as char): 11,
                ('Q' as char): 12,
                ('K' as char): 13,
                ('A' as char): 14
        ]
        int number

        Card(char what) {
            if (what > '0' && what <= '9') {
                number = Character.getNumericValue(what)
            } else {
                number = RANK[what]
            }
        }

        @Override
        int compareTo(Card o) {
            return this.number <=> o.number
        }

        @Override
        boolean equals(Object o) {
            return o instanceof Card && this.number == ((Card) o).number
        }

        @Override
        String toString() {
            "" + number
        }
    }
}
