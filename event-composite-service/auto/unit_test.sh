#!/bin/bash
echo `hostname`/event-composite-service/
docker run -d --rm -v `hostname`:`pwd`/event-composite-service/:/app gradle/java gradlew clean test