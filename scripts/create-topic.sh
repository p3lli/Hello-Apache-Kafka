#!/usr/bin/env bash
cd ../kafka

## Create topics
bin/kafka-topics.sh --create \
    --replication-factor 1 \
    --partitions $2\
    --topic $1 \
    --zookeeper  localhost:2181


## List created topics
bin/kafka-topics.sh --list \
    --zookeeper localhost:2181

