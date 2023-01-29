FROM openjdk:17
ARG JAR_FILE=target/mqtt-poc-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} mqtt-poc-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "mqtt-poc-0.0.1-SNAPSHOT.jar"]