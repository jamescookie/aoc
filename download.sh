#!/bin/bash

set -euo pipefail

if [[ $# -eq 0 ]] ; then
    echo 'Please provide a day'
    exit 0
fi

YEAR=2020
DAY=$1

echo "Downloading $YEAR day $DAY"

rm -rf src/main/groovy/aoc/y$YEAR/day$DAY
mkdir -p src/main/groovy/aoc/y$YEAR/day$DAY
rm -rf src/test/groovy/aoc/y$YEAR/day$DAY
mkdir -p src/test/groovy/aoc/y$YEAR/day$DAY

cp src/test/DaySpec.txt src/test/groovy/aoc/y$YEAR/day$DAY/Day${DAY}Spec.groovy
curl https://adventofcode.com/$YEAR/day/$DAY/input -o src/test/groovy/aoc/y$YEAR/day$DAY/input.txt
