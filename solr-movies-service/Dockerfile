FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
ENV MOVIE_INFO_SERVICE_NAME=movie-info-service
ENV MOVIE_RATINGS_SERVICE_NAME=movie-ratings-service
ENV EUREKA_SERVICE_URL=http://localhost:8761/eureka/
COPY /target/movies-catalog-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar --eureka.client.serviceUrl.defaultZone=${EUREKA_SERVICE_URL} --movie.info.service=${MOVIE_INFO_SERVICE_NAME} --movie.ratings.service=${MOVIE_RATINGS_SERVICE_NAME} "]
