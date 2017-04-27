#!/bin/bash
java -jar /jenkins/slave.jar -jnlpUrl http://$JENKINS_MASTER/computer/$NODE_NAME/slave-agent.jnlp -secret $SECRET

