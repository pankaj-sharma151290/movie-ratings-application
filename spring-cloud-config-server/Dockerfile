FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
ENV SERVER_PORT=8888
COPY /target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar"]
