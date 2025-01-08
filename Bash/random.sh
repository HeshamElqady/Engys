#!/bin/bash

for i in {1..100}; do
echo "$i $((EPOCHSECONDS)) $((RANDOM%6))" >> random.log
sleep 1
done
echo "finished"
