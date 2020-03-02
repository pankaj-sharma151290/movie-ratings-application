FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
ENV SERVER_PORT=8180
ENV EUREKA_HOST=localhost
ENV EUREKA_PORT=8761
ENV CONFIG_SERVER_HOST=localhost
ENV CONFIG_SERVER_PORT=8888
ENV REDIS_HOST=localhost
ENV REDIS_PORT=6379
COPY /target/movie-info-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar"]
