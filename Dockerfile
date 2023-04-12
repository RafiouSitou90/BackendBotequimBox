# Build stage
FROM maven:3.8.2-openjdk-17-slim
COPY . .
RUN mvn clean package -DskipTests

# Package stage
FROM amazoncorretto:17.0.6-alpine
COPY /target/*.jar BackendBotequimBox.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "BackendBotequimBox.jar"]
