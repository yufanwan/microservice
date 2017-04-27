#WFROM krallin/ubuntu-tini
FROM denverdino/ubuntu-aliyun

ARG DOCKER_VERSION="17.03.1-ce"
ARG DOCKER_VERSION_COMPOSE_VERSION="1.13.0-rc1"
ENV JENKINS_MASTER=${JENKINS_MASTER:-localhost}

#Install other things typically needed 
#RUN apt-get update && apt-get -y install \
#		curl \
#		git \
#		openssh-client \
#		make \
#		iptables \
#		less \
#		groff \
#		aufs-tools \
#		openjdk-7-jre \
#    && rm -rf /var/lib/apt/lists/*
RUN apt-get update && apt-get -y install curl openjdk-7-jre && rm -rf /var/lib/apt/lists/*
		
		

#Install docker 
RUN curl -fL "https://get.docker.com/builds/Linux/x86_64/docker-${DOCKER_VERSION}.tgz" \
     | tar -zvx --transform='s,docker/,usr/local/bin/,' \
     && chmod +x /usr/local/bin/docker* \
     && curl -fL "https://github.com/docker/compose/releases/downloads/${DOCKER_COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m)" > /usr/local/bin/docker-compose \
     && chmod +x /usr/local/bin/docker-compose

VOLUME /var/lib/docker
EXPOSE 2357

#Install kenkins slave agent
ADD slave.jar /jenkins/
ADD entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh
ADD jenkins-slave.sh /usr/local/bin/jenkins-slave.sh
RUN chmod +x /usr/local/bin/jenkins-slave.sh

ENTRYPOINT ["tini","-s","--","/usr/local/bin/entrypoint.sh"]

ENV DIND true

CMD ["/usr/local/bin/jenkins-slave.sh"]

#https://github.com/docker/compose/releases/download/1.13.0-rc1/docker-compose-Linux-x86_64