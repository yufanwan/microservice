FROM denverdino/ubuntu-aliyun
MAINTAINER wyf

ENV JENKINS_MASTER=${JENKINS_MASTER:-localhost}
ENV NODE_NAME=${NODE_NAME:-jenkins-slave}
ENV SECRET=${SECRET}

#Install other things typically needed 
RUN apt-get update && apt-get -y install curl lxc iptables apt-transport-https ca-certificates 
#docker.io
RUN curl -sSL https://get.docker.com/ | sh

#other dependency
RUN apt-get -y install git subversion openjdk-7-jre python-pip && rm -rf /var/lib/apt/lists/*

#Install kenkins slave agent		
RUN pip install docker-compose		


#Install kenkins slave agent
ADD slave.jar /jenkins/
ADD jenkins-slave.sh /usr/local/bin/jenkins-slave.sh
RUN chmod +x /usr/local/bin/jenkins-slave.sh

# Install the magic wrapper.
ADD ./wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

# Define additional metadata for our image.
VOLUME /var/lib/docker

ENTRYPOINT ["wrapdocker"]

CMD ["/usr/local/bin/jenkins-slave.sh"]
