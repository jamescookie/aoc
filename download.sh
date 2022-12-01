#!/bin/bash

set -euo pipefail

if [[ $# -eq 0 ]] ; then
    echo 'Please provide a day'
    exit 0
fi

if [[ "${AOC_SESSION}" == "" ]] ; then
    echo 'need to "export AOC_SESSION=??" - get the session from the cookie on the site'
    exit 0
fi

YEAR=2022
DAY=$1

echo "Downloading $YEAR day $DAY"

rm -rf src/main/groovy/aoc/y$YEAR/day$DAY
mkdir -p src/main/groovy/aoc/y$YEAR/day$DAY
rm -rf src/test/groovy/aoc/y$YEAR/day$DAY
mkdir -p src/test/groovy/aoc/y$YEAR/day$DAY

cp src/test/DaySpec.txt src/test/groovy/aoc/y$YEAR/day$DAY/Day${DAY}Spec.groovy
sed -i '' "s/%DAY%/$DAY/g" src/test/groovy/aoc/y$YEAR/day$DAY/Day${DAY}Spec.groovy
sed -i '' "s/%YEAR%/$YEAR/g" src/test/groovy/aoc/y$YEAR/day$DAY/Day${DAY}Spec.groovy
cp src/main/Day.txt src/main/groovy/aoc/y$YEAR/day$DAY/Day${DAY}.groovy
sed -i '' "s/%DAY%/$DAY/g" src/main/groovy/aoc/y$YEAR/day$DAY/Day${DAY}.groovy
sed -i '' "s/%YEAR%/$YEAR/g" src/main/groovy/aoc/y$YEAR/day$DAY/Day${DAY}.groovy
curl -b session=$AOC_SESSION https://adventofcode.com/$YEAR/day/$DAY/input -o src/test/groovy/aoc/y$YEAR/day$DAY/input.txt
curl -b session=$AOC_SESSION https://adventofcode.com/$YEAR/day/$DAY -o src/main/groovy/aoc/y$YEAR/day$DAY/README.html
perl -i -p0e 's/.*?(\<article)/$1/se' src/main/groovy/aoc/y$YEAR/day$DAY/README.html
perl -i -p0e 's/(\/article\>).*/$1/se' src/main/groovy/aoc/y$YEAR/day$DAY/README.html
