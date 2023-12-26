FROM openjdk:17-slim

WORKDIR /app

COPY /target/*.jar api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]