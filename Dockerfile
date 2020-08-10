FROM openjdk:8-jre-alpine
ARG JAR_FILE=target/mancala-app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /opt/service/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/service/application.jar"]