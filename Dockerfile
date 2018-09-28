FROM openjdk:8
ADD target/ejournal.jar ejournal.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ejournal.jar"]