FROM centos/java:8

WORKDIR /app
ADD ./build/libs/review-service-0.0.1.jar /app

RUN ln -s review-service-0.0.1.jar review-service-latest.jar

CMD java -jar /app/review-service-latest.jar
