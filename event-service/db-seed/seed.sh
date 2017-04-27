#!/usr/bin/env bash

SEED_FILE_NAME='events-with-id.json'

CONTAINER_ID=$(docker ps | awk '/_mongo/ {print $1}')
echo $CONTAINER_ID

docker cp $SEED_FILE_NAME $CONTAINER_ID:/data
docker exec $CONTAINER_ID mongoimport --db test --collection event --type json --file /data/$SEED_FILE_NAME
