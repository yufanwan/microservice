#Build base jenkins slave image command:  docker build -t jenkins-slave-dood:latest .
FROM centos:7
ENV JENKINS_MASTER=${JENKINS_MASTER:-localhost}
ENV NODE_NAME=${NODE_NAME:-jenkins-slave}
ENV SECRET=${SECRET}

#Install other things typically needed
RUN yum update -y && yum -y install curl git subversion  java-1.8.0-openjdk.x86_64 \
        && yum -y clean all \
        && yum -y autoremove \
        && rm -rf /var/cache/yum/*

#Install kenkins slave agent
ADD slave.jar /jenkins/
ADD jenkins-slave.sh /usr/local/bin/jenkins-slave.sh
RUN chmod +x /usr/local/bin/jenkins-slave.sh

CMD ["/usr/local/bin/jenkins-slave.sh"]
