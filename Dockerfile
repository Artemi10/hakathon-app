FROM openjdk:17-alpine
EXPOSE 8080
ADD build/libs/bhcrud-0.0.1-SNAPSHOT.jar bhcrud-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/bhcrud-0.0.1-SNAPSHOT.jar"]
