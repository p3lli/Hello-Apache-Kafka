#!/usr/bin/env bash
cd ../kafka

bin/kafka-server-start.sh \
  ../scripts/config/server-1.properties

