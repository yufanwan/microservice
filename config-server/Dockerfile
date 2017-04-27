FROM centos/java:8

#RUN mkdir /app
WORKDIR /app
ADD ./build/libs/config-server-0.0.1.jar /app

RUN ln -s config-server-0.0.1.jar config-server-latest.jar

CMD java -jar /app/config-server-latest.jar
