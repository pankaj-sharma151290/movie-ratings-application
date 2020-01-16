FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
ENV EUREKA_SERVICE_URL=http://localhost:8761/eureka/
COPY /target/movie-info-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar --eureka.client.serviceUrl.defaultZone=${EUREKA_SERVICE_URL}"]
