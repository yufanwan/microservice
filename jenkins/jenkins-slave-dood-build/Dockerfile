#Build base jenkins slave image command:  docker build -t jenkins-slave-dood:latest .
FROM denverdino/ubuntu-aliyun   

ENV JENKINS_MASTER=${JENKINS_MASTER:-localhost}
ENV NODE_NAME=${NODE_NAME:-jenkins-slave}
ENV SECRET=${SECRET}

#Install other things typically needed 
RUN apt-get update && apt-get -y install curl git subversion openjdk-7-jre 

#Install kenkins slave agent
ADD slave.jar /jenkins/
ADD jenkins-slave.sh /usr/local/bin/jenkins-slave.sh
RUN chmod +x /usr/local/bin/jenkins-slave.sh

CMD ["/usr/local/bin/jenkins-slave.sh"]