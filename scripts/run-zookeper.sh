#!/usr/bin/env bash
cd ../kafka/

bin/zookeeper-server-start.sh \
   ../scripts/config/zookeeper.properties

