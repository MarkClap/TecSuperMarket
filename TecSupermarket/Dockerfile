From eclipse-temurin:17-jdk
ARG JAR_FILE=target/TecSupermarket-0.0.1.jar
COPY ${JAR_FILE} app_tecsuper.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_tecsuper.jar"]
