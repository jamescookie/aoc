package aoc.y2023.day7

class Day7 {
    static part1(String inputString) {
        return inputString.tokenize('\n').collect { new HandAndBid(it, false) }.sort()
                .withIndex(1).collect { it, i -> i * it.bid }.sum()
    }

    static part2(String inputString) {
        return inputString.tokenize('\n').collect { new HandAndBid(it, true) }.sort()
                .withIndex(1).collect { it, i -> i * it.bid }.sum()
    }

    static class HandAndBid implements Comparable<HandAndBid> {
        Hand hand
        int bid

        HandAndBid(String input, boolean joker) {
            def tokenize = input.tokenize()
            hand = new Hand(tokenize[0], joker)
            bid = tokenize[1] as int
        }

        @Override
        int compareTo(HandAndBid o) {
            return this.hand <=> o.hand
        }
    }

    static class Hand implements Comparable<Hand> {
        public static Map<List<Integer>, Integer> RANK = [
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

        Hand(String h, boolean joker) {
            cards = h.toCharArray().collect { new Card(it, joker) }
            if (joker && cards.any { it.isJoker() }) {
                def newCards = new ArrayList<>(cards)
                def jokers = newCards.findAll { it.isJoker() }.size()
                if (jokers == newCards.size()) {
                    rank = 7
                } else {
                    newCards.removeAll { it.isJoker() }
                    def fake = newCards.groupBy { it.number }.values().sort { it.size() }.last()[0].number
                    for (i in 0..<jokers) {
                        newCards.add(new Card(fake))
                    }
                    rank = findRank(newCards)
                }
            } else {
                rank = findRank(cards)
            }
        }

        protected static int findRank(List<Card> cards) {
            def grouped = cards.groupBy { it.number }
            RANK.get([grouped.size(), grouped.values().sort { it.size() }.last().size()])
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
        boolean joker = false

        Card(int what) {
            number = what
        }

        Card(char what, boolean joker) {
            if (what > ('0' as char) && what <= ('9' as char)) {
                number = Character.getNumericValue(what)
            } else {
                number = RANK[what]
                if (joker && number == 11) {
                    number = 1
                    this.joker = true
                }
            }
        }

        @Override
        int compareTo(Card o) {
            return this.number <=> o.number
        }
    }
}
