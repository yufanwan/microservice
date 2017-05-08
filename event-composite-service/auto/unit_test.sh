#!/bin/bash
# test in dood
#docker run --rm -w /mnt -v /opt/jenkins_slave_home/workspace/composite-service/event-composite-service:/mnt:rw gradle/java
<<<<<<< HEAD
docker run --rm -w /mnt -v /opt/jenkins_slave_home/workspace/composite-service/event-composite-service:/mnt:rw gradle/java gradle clean test
=======
docker run --rm -w /mnt -v /opt/jenkins_slave_home/workspace/composite-service/event-composite-service:/mnt:rw gradle/java gradle clean test
>>>>>>> 366f18282f7b97ca570c275682ebc184cf068ce2
