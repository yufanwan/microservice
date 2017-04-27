FROM centos/java:8

WORKDIR /app
ADD ./build/libs/recommendation-service-0.0.1.jar /app

RUN ln -s recommendation-service-0.0.1.jar recommendation-service-latest.jar

CMD java -jar /app/recommendation-service-latest.jar
