#!/bin/bash
echo `pwd`/event-composite-service/
docker run -d --rm -v `pwd`/event-composite-service/:/app gradle/jdk gradlew clean test