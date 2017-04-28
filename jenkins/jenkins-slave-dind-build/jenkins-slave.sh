#!/bin/bash
sudo service docker start
java -jar /jenkins/slave.jar -jnlpUrl http://$JENKINS_MASTER/computer/$NODE_NAME/slave-agent.jnlp -secret $SECRET
