FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
ENV SERVER_PORT=8280
ENV MONGO_DB_HOST=localhost
ENV MONGO_DB_PORT=27017
ENV EUREKA_HOST=localhost
ENV EUREKA_PORT=8761
ENV CONFIG_SERVER_HOST=localhost
ENV CONFIG_SERVER_PORT=8888
COPY /target/movie-ratings-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar"]
