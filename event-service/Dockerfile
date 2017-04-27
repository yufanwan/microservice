FROM centos/java:8

WORKDIR /app
ADD ./build/libs/event-service-0.0.1.jar /app

RUN ln -s event-service-0.0.1.jar event-service-latest.jar

CMD java -jar /app/event-service-latest.jar
