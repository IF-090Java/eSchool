FROM openjdk:8
ADD target/eschool.jar eschool.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "eschool.jar"]