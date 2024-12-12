#!/bin/bash

for x in ../tests/*.outhyp; do
    rm $x
done
for x in ../tests/*.diff; do
    rm $x
done