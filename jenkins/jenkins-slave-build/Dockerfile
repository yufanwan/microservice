#WFROM krallin/ubuntu-tini
FROM aliyun/ubuntu  # contain jdk

ENV JENKINS_MASTER=${JENKINS_MASTER:-localhost}
ENV NODE_NAME=${NODE_NAME:-jenkins-slave}
ENV SECRET=${SECRET}


#Install kenkins slave agent
ADD slave.jar /jenkins/
ADD jenkins-slave.sh /usr/local/bin/jenkins-slave.sh
RUN chmod +x /usr/local/bin/jenkins-slave.sh

CMD ["/usr/local/bin/jenkins-slave.sh"]