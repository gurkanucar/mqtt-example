# Build stage
FROM maven:3.6.3-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn package

# Package stage
FROM openjdk:17
ARG JAR_FILE=target/mqqt-poc-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} mqqt-poc-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "mqqt-poc-0.0.1-SNAPSHOT.jar"]
