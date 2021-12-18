package aoc.y2021.day18

class Day18 {
    static part1(String inputString) {
        return inputString.trim().split('\n')
                .collect { new SnailFish(it) }
                .inject { a, b -> a.add(b) }
                .magnitude()
    }

    static part2(String inputString) {
        Integer[] input = inputString.tokenize().collect { it as int }
        return null
    }

    static class SnailFish {
        SnailFish left
        SnailFish right
        Integer number = null
        boolean exploded = false

        SnailFish(SnailFish left, SnailFish right) {
            this.left = left
            this.right = right
        }

        SnailFish(String input) {
            if (input ==~ /\d+.*/) {
                number = Eval.me(input) as int
            } else {
                input = input[1..-2]
                String inner = findInner(input)
                left = new SnailFish(inner)
                right = new SnailFish(input.substring(inner.size() + 1))
            }
        }

        SnailFish add(SnailFish other) {
            def fish = reduce(new SnailFish(this, other))
            println "finished: " + fish
            return fish
        }

        static SnailFish reduce(SnailFish snailFish) {
            boolean explode = true
            boolean split = true
            while (explode || split) {
                (explode, snailFish) = tryExploding(snailFish)
                (split, snailFish) = trySplitting(snailFish)
            }
            return snailFish
        }

        static def tryExploding(SnailFish snailFish) {
            boolean foundExplosion = false
            def exploded = snailFish.explode(0)
            if (exploded) {
                def leftReplaced = snailFish.toString().replaceAll(/(.*)(\d+)(.*?)#/, '$1$2+' + exploded.left + '$3#')
                def rightReplaced = leftReplaced.replaceAll(/#(.*?)(\d+)(.*)/, '#$1$2+' + exploded.right + '$3').replace('#', '0')
                snailFish = new SnailFish(rightReplaced)
                println(snailFish)
                foundExplosion = true
            }
            return [foundExplosion, snailFish]
        }

        static def trySplitting(SnailFish snailFish) {
            boolean foundSplit = false
            def asString = snailFish.toString()
            def matcher = asString =~ /(\d\d+)/
            if (matcher.find()) {
                String found = matcher[0][0]
                int total = found as int
                int left = total / 2
                int right = total - left
                String afterSplit = asString.replace(found, "[$left,$right]")
                snailFish = new SnailFish(afterSplit)
                println(snailFish)
                foundSplit = true
            }
            return [foundSplit, snailFish]
        }

        SnailFish explode(int count) {
            if (number != null) {
                return null
            } else {
                if (count >= 4) {
                    if (this.left.number == null) {
                        count++
                        return left.explode(count)
                    } else if (this.right.number == null) {
                        count++
                        return right.explode(count)
                    } else {
                        exploded = true
                        return this
                    }
                } else {
                    count++
                    def exploded = left.explode(count)
                    if (exploded) {
                        return exploded
                    } else {
                        return right.explode(count)
                    }
                }
            }
        }

        SnailFish absorb(SnailFish snailFish, boolean left) {
            if (this.number) {
                this.number += snailFish.number
                snailFish.number = 0
            } else {
                if (left) {
                    this.left.absorb(snailFish, left)
                } else {
                    this.right.absorb(snailFish, left)
                }
            }
            return snailFish
        }

        protected static String findInner(String input) {
            String inner = "["
            if (input[0] == '[') {
                int count = 1
                int i = 1

                while (count != 0) {
                    Character current = input[i++] as Character
                    inner += current
                    if (current == '[') {
                        count++
                    } else if (current == ']') {
                        count--
                    }
                }
            } else {
                inner = input.substring(0, input.indexOf(','))
            }
            inner
        }

        long magnitude() {
            if (number != null) {
                return number
            } else {
                return (left.magnitude() * 3) + (right.magnitude() * 2)
            }
        }

        String toString() {
            if (exploded) {
                return "#"
            } else if (number != null) {
                return "" + number
            } else {
                return "[$left,$right]"
            }
        }
    }
}
