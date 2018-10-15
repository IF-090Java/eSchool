FROM openjdk:8-jdk-alpine
ADD target/eschool.jar eschool.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "eschool.jar"]