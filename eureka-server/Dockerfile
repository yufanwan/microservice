FROM centos/java:8

WORKDIR /app
ADD ./build/libs/eureka-server-0.0.1.jar /app

RUN ln -s eureka-server-0.0.1.jar eureka-server-latest.jar

CMD java -jar /app/eureka-server-latest.jar
