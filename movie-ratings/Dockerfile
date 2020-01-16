FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
ENV MONGODB_URI=mongodb://localhost:27017/test
ENV EUREKA_SERVICE_URL=http://localhost:8761/eureka/
COPY /target/movie-ratings-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar --spring.data.mongodb.uri=${MONGODB_URI} --eureka.client.serviceUrl.defaultZone=${EUREKA_SERVICE_URL} "]
