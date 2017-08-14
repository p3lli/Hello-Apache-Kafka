#!/usr/bin/env bash
cd ../kafka/

bin/zookeeper-server-start.sh \
   config/zookeeper.properties

