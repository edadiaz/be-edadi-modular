FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 5001
CMD ["java", "-jar", "app.jar"]