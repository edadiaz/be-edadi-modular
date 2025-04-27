FROM openjdk:17-jdk-slim
WORKDIR /app
COPY  app/target/app-1.0-SNAPSHOT.jar app.jar
EXPOSE 5000
CMD ["java", "-jar", "app.jar"]