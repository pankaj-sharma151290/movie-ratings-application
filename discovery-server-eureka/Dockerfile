FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
ENV SERVER_PORT=8761
COPY /target/discovery-server-eureka-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar"]
