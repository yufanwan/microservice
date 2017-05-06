#!/bin/bash
docker run -dit -w /mnt -v /opt/jenkins_slave_home/workspace/composite-service/event-composite-service:/mnt:rw gradle/java
#docker run -dit -w /mnt -v /opt/jenkins_slave_home/workspace/composite-service/event-composite-service:/mnt:rw gradle/java gradle clean test