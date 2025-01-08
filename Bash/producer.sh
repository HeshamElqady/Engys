#!/bin/bash
users=("Goofy" "Mickey" "Donald" "Minnie" "Daisy")
counter=1
end_time=$((SECONDS + 300))
while [ $SECONDS -lt $end_time ]; do
  echo "$counter $(date '+%Y-%m-%d %H:%M:%S') ${users[$RANDOM % ${#users[@]}]} $((RANDOM % 101))" >> producer.log
  counter=$((counter + 1))
  sleep $((RANDOM % 6))
done
echo "finished"

