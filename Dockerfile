FROM openjdk:8-alpine
EXPOSE 8080
ADD /target/scrum-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev", "-jar", "app.jar"]