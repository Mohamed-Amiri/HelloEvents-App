# استخدم صورة Java لبناء وتشغيل تطبيق Spring Boot
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/hello-event-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
