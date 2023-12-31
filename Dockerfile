FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

# Ejecutar el comando gradle usando sudo para permisos
RUN cd /app && sudo ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8000

COPY --from=build /build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]