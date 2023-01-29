FROM openjdk:17
ARG JAR_FILE=target/mqqt-poc-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} mqqt-poc-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "mqqt-poc-0.0.1-SNAPSHOT.jar"]