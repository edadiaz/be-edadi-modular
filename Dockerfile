FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/app-1.0-SNAPSHOT.jar app.jar
EXPOSE 5001
CMD ["java", "-jar", "app.jar"]